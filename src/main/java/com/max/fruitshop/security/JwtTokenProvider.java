package com.max.fruitshop.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.max.fruitshop.domain.Role;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
public class JwtTokenProvider {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/v1/users/sign-up";

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds = 3600000; // 1h

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(User user) {
        List<String> roles = user.getAuthorities().stream().map((a) -> a.getAuthority()).collect(Collectors.toList());

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + this.validityInMilliseconds))
                .withArrayClaim("roles", roles.toArray(new String[0]))
                .sign(Algorithm.HMAC512(this.secretKey));

        return token;
    }

    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = null;

        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            DecodedJWT jwt = JWT.require(Algorithm.HMAC512(this.secretKey))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""));

            String user = jwt.getSubject();
            Claim claim = jwt.getClaim("roles");

            List<Role> roles = Arrays.asList(claim.asArray(String.class))
                    .stream().map(r -> new Role(r)).collect(Collectors.toList());

            if (user != null) {
                authenticationToken = new UsernamePasswordAuthenticationToken(user, null, roles);
            }
        }

        return authenticationToken;
    }

}

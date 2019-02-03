package com.max.fruitshop.repositories;

import com.max.fruitshop.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface VendorRepository extends JpaRepository<Vendor, Long> {



}

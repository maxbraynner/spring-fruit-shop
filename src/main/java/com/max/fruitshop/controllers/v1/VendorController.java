package com.max.fruitshop.controllers.v1;

import com.max.fruitshop.api.v1.model.VendorDTO;
import com.max.fruitshop.api.v1.model.VendorListDTO;
import com.max.fruitshop.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/{id}")
    public VendorDTO getVendorById(@PathVariable Long id){
        return vendorService.getVendorById(id);
    }

    @PostMapping
    public ResponseEntity<VendorDTO> createVendor(@Valid @RequestBody VendorDTO vendorDTO) {
        return new ResponseEntity<>(vendorService.createVendor(vendorDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public VendorListDTO getAllVendors() {
        List<VendorDTO> vendors = vendorService.getAllVendors();
        return new VendorListDTO(vendors);
    }

    @PutMapping("/{id}")
    public VendorDTO updateVendor(@PathVariable Long id, @Valid @RequestBody VendorDTO vendorDTO) {
        return vendorService.updateVendor(id, vendorDTO);
    }

    @DeleteMapping("/{id}")
    public VendorDTO deleteVendor(@PathVariable Long id) {
        return vendorService.deleteVendor(id);
    }

}

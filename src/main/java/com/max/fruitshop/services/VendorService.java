package com.max.fruitshop.services;

import com.max.fruitshop.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    VendorDTO createVendor(VendorDTO vendorDTO);

    VendorDTO getVendorById(Long id);

    List<VendorDTO> getAllVendors();

    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);

    VendorDTO deleteVendor(Long ind);

}

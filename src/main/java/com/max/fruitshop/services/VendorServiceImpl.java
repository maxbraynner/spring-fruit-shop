package com.max.fruitshop.services;

import com.max.fruitshop.api.v1.mapper.VendorMapper;
import com.max.fruitshop.api.v1.model.VendorDTO;
import com.max.fruitshop.domain.Vendor;
import com.max.fruitshop.exceptions.NotFoundException;
import com.max.fruitshop.repositories.VendorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private VendorMapper vendorMapper;
    private VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id).map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(() -> new NotFoundException("Vendor not founded by id: " + id));
    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorRepository.save(vendorMapper.vendorDtoToVendor(vendorDTO));
        return vendorMapper.vendorToVendorDTO(vendor);
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendorMapper::vendorToVendorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            BeanUtils.copyProperties(vendorDTO, vendor, "id");

            vendorRepository.save(vendor);

            return vendorDTO;
        }).orElseThrow(() -> new NotFoundException("Vendor not founded by id: " + id));
    }

    @Override
    public VendorDTO deleteVendor(Long id) {
        return vendorRepository.findById(id).map(vendor -> {
            vendorRepository.delete(vendor);
            return vendorMapper.vendorToVendorDTO(vendor);
        }).orElseThrow(()-> new NotFoundException("Vendor not founded by id: " + id));
    }
}

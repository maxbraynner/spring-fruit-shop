package com.max.fruitshop.services;

import com.max.fruitshop.api.v1.mapper.VendorMapper;
import com.max.fruitshop.api.v1.model.VendorDTO;
import com.max.fruitshop.domain.Vendor;
import com.max.fruitshop.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    private static final String NAME = "Max";
    private static final long ID = 1L;

    @Mock
    private VendorRepository vendorRepository;

    private VendorMapper vendorMapper = VendorMapper.INSTANCE;

    private VendorServiceImpl vendorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(vendorMapper, vendorRepository);
    }

    @Test
    public void getVendorById() {
        Vendor vendor = getVendor();

        when(vendorRepository.findById(ID)).thenReturn(Optional.ofNullable(vendor));

        VendorDTO vendorById = vendorService.getVendorById(ID);

        assertEquals(NAME, vendorById.getName());
    }

    @Test
    public void createVendor() {
        Vendor vendorToSave = new Vendor();
        vendorToSave.setName(NAME);

        Vendor vendor = getVendor();

        when(vendorRepository.save(vendorToSave)).thenReturn(vendor);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        vendorDTO = vendorService.createVendor(vendorDTO);

        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    public void getAllVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName(NAME+1);

        Vendor vendor2 = new Vendor();
        vendor2.setName(NAME+2);

        List<Vendor> vendors = Arrays.asList(vendor1, vendor2);

        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> allVendors = vendorService.getAllVendors();

        assertEquals(2, allVendors.size());
    }

    @Test
    public void updateVendor() {
        Vendor vendor = getVendor();

        when(vendorRepository.findById(ID)).thenReturn(Optional.ofNullable(vendor));
        when(vendorRepository.save(vendor)).thenReturn(vendor);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        VendorDTO updatedVendor = vendorService.updateVendor(ID, vendorDTO);

        assertEquals(NAME, updatedVendor.getName());
    }

    @Test
    public void deleteVendor() {
        Vendor vendor = getVendor();

        when(vendorRepository.findById(ID)).thenReturn(Optional.ofNullable(vendor));

        VendorDTO deletedVendor = vendorService.deleteVendor(ID);

        assertEquals(NAME, deletedVendor.getName());
    }

    private Vendor getVendor() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        vendor.setId(ID);

        return vendor;
    }
}
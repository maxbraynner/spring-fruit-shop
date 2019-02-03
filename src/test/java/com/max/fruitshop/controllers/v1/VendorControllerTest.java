package com.max.fruitshop.controllers.v1;

import com.max.fruitshop.api.v1.model.VendorDTO;
import com.max.fruitshop.controllers.RestExceptionHandler;
import com.max.fruitshop.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest extends AbstractRestControllerTest {

    private static final String NAME = "Max";
    private static final Long ID = 1L;

    @Mock
    private VendorService vendorService;

    @InjectMocks
    private VendorController vendorController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc =  MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    public void getVendorById() throws Exception {
        VendorDTO vendorDTO = getVendorDTO();

        when(vendorService.getVendorById(ID)).thenReturn(vendorDTO);

        mockMvc.perform(get(VendorController.BASE_URL + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void createVendor() throws Exception {
        VendorDTO vendorDTO = getVendorDTO();

        when(vendorService.createVendor(vendorDTO)).thenReturn(vendorDTO);

        mockMvc.perform(post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void getAllVendors() throws Exception {
        VendorDTO vendor1 = getVendorDTO();
        VendorDTO vendor2 = getVendorDTO();

        List<VendorDTO> vendors = Arrays.asList(vendor1, vendor2);

        when(vendorService.getAllVendors()).thenReturn(vendors);

        mockMvc.perform(get(VendorController.BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void updateVendor() throws Exception {
        VendorDTO vendorDTO = getVendorDTO();

        when(vendorService.updateVendor(ID, vendorDTO)).thenReturn(vendorDTO);

        mockMvc.perform(put(VendorController.BASE_URL + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void deleteVendor() throws Exception {
        VendorDTO vendorDTO = getVendorDTO();

        when(vendorService.deleteVendor(ID)).thenReturn(vendorDTO);

        mockMvc.perform(delete(VendorController.BASE_URL + "/" + ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    private VendorDTO getVendorDTO() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        return vendorDTO;
    }
}
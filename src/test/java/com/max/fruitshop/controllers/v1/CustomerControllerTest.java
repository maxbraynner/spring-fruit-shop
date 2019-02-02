package com.max.fruitshop.controllers.v1;

import com.max.fruitshop.api.v1.model.CustomerDTO;
import com.max.fruitshop.controllers.RestExceptionHandler;
import com.max.fruitshop.exceptions.NotFoundException;
import com.max.fruitshop.services.CustomerService;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestControllerTest {

    private static final long ID = 1L;
    private static final String FIRST_NAME = "Max";
    private static final String LAST_NAME = "Braynner";

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    public void getById() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);

        when(customerService.getCustomerById(ID)).thenReturn(customerDTO);

        mockMvc.perform(get(CustomerController.BASE_URL + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
    }

    @Test
    public void getAll() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Max1");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("Max2");

        List<CustomerDTO> customerDTOS = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customerDTOS);

        mockMvc.perform(get(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void create() throws Exception {
        CustomerDTO customerDTO = getCustomerDTO();

        CustomerDTO customerResp = getCustomerDTO();
        customerResp.setCustomerUrl(CustomerController.BASE_URL);

        when(customerService.createCustomer(customerDTO)).thenReturn(customerResp);

        mockMvc.perform(post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL)));
    }
    @Test
    public void createBadRequest() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);

        mockMvc.perform(post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void update() throws Exception {
        CustomerDTO customerDTO = getCustomerDTO();

        when(customerService.updateCustomer(ID, customerDTO)).thenReturn(customerDTO);

        mockMvc.perform(put(CustomerController.BASE_URL+"/"+ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateBadRequest() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(LAST_NAME);

        when(customerService.updateCustomer(ID, customerDTO)).thenReturn(customerDTO);

        mockMvc.perform(put(CustomerController.BASE_URL+"/"+ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void patchCustomer() throws Exception {
        CustomerDTO customerDTO = getCustomerDTO();

        when(customerService.patchCustomer(ID, customerDTO)).thenReturn(customerDTO);

        mockMvc.perform(patch(CustomerController.BASE_URL + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCustomer() throws Exception {
        CustomerDTO customerDTO = getCustomerDTO();

        when(customerService.deleteCustomer(ID)).thenReturn(customerDTO);

        mockMvc.perform(delete(CustomerController.BASE_URL+"/"+ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
    }

    @Test
    public void deleteCustomerNotFound() throws Exception {
        when(customerService.deleteCustomer(ID)).thenThrow(new NotFoundException());

        mockMvc.perform(delete(CustomerController.BASE_URL+"/"+ID))
                .andExpect(status().isNotFound());
    }

    private CustomerDTO getCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);
        return customerDTO;
    }

}
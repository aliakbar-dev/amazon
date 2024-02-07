package com.example.amazon.integrationTest;

import com.example.amazon.dto.CustomerRequestDTO;
import com.example.amazon.model.Customer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.example.amazon.controller.CustomerController.REQUEST_PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void idIsValidAndAvailable_callGet_successWithStatusOK() throws Exception {
        final MvcResult result = mockMvc.perform(get(REQUEST_PATH + "/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();

        final String contentAsString = result.getResponse().getContentAsString();
        final JsonObject jsonObject = JsonParser.parseString(contentAsString).getAsJsonObject();
        final Customer customer = new Gson().fromJson(contentAsString, Customer.class);
        assertThat(jsonObject.isJsonObject()).isTrue();
        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isEqualTo("1");
    }

    @Test
    void idIsValidAndNotAvailable_callGet_failWithStatusBadRequest() throws Exception {
        final MvcResult result = mockMvc.perform(get(REQUEST_PATH + "/10"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();

        final String content = result.getResponse().getContentAsString();
        assertThat(content).isEqualTo("could not found customer With id = 10");
    }

    @Test
    void emailIsNull_callPost_failWithStatusBadRequest() throws Exception {
        final CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO("test", null, "lastName", "test@test.com");
        final MvcResult result = mockMvc.perform(post(REQUEST_PATH).contentType(APPLICATION_JSON).content(new Gson().toJson(customerRequestDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();

        final String content = result.getResponse().getContentAsString();
        assertThat(content).isEqualTo("email must not be null");
    }

    @Test
    void emailIsInvalid_callPost_failWithStatusBadRequest() throws Exception {
        final CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO("test", "email", "lastName", "test@test.com");
        final MvcResult result = mockMvc.perform(post(REQUEST_PATH).contentType(APPLICATION_JSON).content(new Gson().toJson(customerRequestDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();

        final String content = result.getResponse().getContentAsString();
        assertThat(content).isEqualTo("must be a well-formed email address");
    }

}

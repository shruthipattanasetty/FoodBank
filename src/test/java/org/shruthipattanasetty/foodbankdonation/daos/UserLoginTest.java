package org.shruthipattanasetty.foodbankdonation.daos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserLoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSuccessfulLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/user/login-th")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "suja@gmail.com")
                        .param("password", "suja"))
                .andExpect(status().isOk());

    }

    @Test
    public void testFailedLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/user/login-th")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "testuser")
                        .param("password", "wrongpass"))
                .andExpect(status().is2xxSuccessful());

    }
}


package com.example.restauthbasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPublicEndpoint() throws Exception {
        mockMvc.perform(get("/api/public/hello"))
               .andExpect(status().isOk()); // Public endpoint should be accessible without authentication
    }

    @Test
    void testPrivateEndpointWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/api/private/hello"))
               .andExpect(status().isUnauthorized()); // Should return 401 Unauthorized
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testPrivateEndpointWithAuthentication() throws Exception {
        mockMvc.perform(get("/api/private/hello"))
               .andExpect(status().isOk()); // Should succeed with authentication
    }

}

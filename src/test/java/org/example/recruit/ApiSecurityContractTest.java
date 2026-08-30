package org.example.recruit;

import org.example.recruit.config.WebConfig;
import org.example.recruit.config.FileUploadConfig;
import org.example.recruit.controller.AdminController;
import org.example.recruit.controller.SpecialtyController;
import org.example.recruit.handler.GlobalExceptionHandler;
import org.example.recruit.interceptor.JwtInterceptor;
import org.example.recruit.utils.JwtUtils;
import org.example.recruit.service.AdminService;
import org.example.recruit.service.SpecialtyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {AdminController.class, SpecialtyController.class})
@ContextConfiguration(classes = {
        AdminController.class,
        SpecialtyController.class,
        WebConfig.class,
        FileUploadConfig.class,
        JwtInterceptor.class,
        JwtUtils.class,
        GlobalExceptionHandler.class
})
@Import({WebConfig.class, FileUploadConfig.class, JwtInterceptor.class, JwtUtils.class, GlobalExceptionHandler.class})
@TestPropertySource(properties =
        "jwt.secret=test-secret-must-be-at-least-sixty-four-characters-long-for-hs512-signing")
class ApiSecurityContractTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AdminService adminService;

    @MockitoBean
    private SpecialtyService specialtyService;

    @Test
    void adminLoginIsPublic() throws Exception {
        mockMvc.perform(post("/api/admin/login")
                        .contentType("application/json")
                        .content("{\"username\":\"missing\",\"password\":\"wrong\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void specialtyListIsPublic() throws Exception {
        when(specialtyService.getAllSpecialtiesWithCollege()).thenReturn(List.of());

        mockMvc.perform(get("/api/specialty/list"))
                .andExpect(status().isOk());
    }

    @Test
    void specialtyDeletionRequiresAuthentication() throws Exception {
        mockMvc.perform(delete("/api/specialty/1"))
                .andExpect(status().isUnauthorized());
    }
}

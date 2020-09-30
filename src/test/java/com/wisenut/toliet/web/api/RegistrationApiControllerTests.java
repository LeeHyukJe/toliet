package com.wisenut.toliet.web.api;

import com.wisenut.config.security.SecurityConfiguration;
import com.wisenut.domain.application.UserService;
import com.wisenut.domain.model.user.EmailAddressExistsException;
import com.wisenut.domain.model.user.UsernameExistsException;
import com.wisenut.utils.JsonUtils;
import com.wisenut.web.apis.RegistrationApiController;
import com.wisenut.web.payload.RegistrationPayload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SecurityConfiguration.class, RegistrationApiController.class})
@WebMvcTest
public class RegistrationApiControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService serviceMock;

    @Test
    public void register_blankPayload_shouldFailandReturn400() throws Exception{
        mvc.perform(post("/api/registrations"))
                .andExpect(status().is(400));
    }

    @Test
    public void register_existUsername_shouldFailAndReturn400() throws Exception{
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("exist");
        payload.setEmailAddress("test@taskagile.com");
        payload.setPassword("MyPassword");

        doThrow(UsernameExistsException.class)
                .when(serviceMock)
                .register(payload.toCommand());

        mvc.perform(post("/api/registrations")
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtils.toJson(payload)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("유저아이디가 이미 존재합니다."));

    }

    @Test
    public void register_existedEmailAddress_shouldFailAndReturn400() throws Exception {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("test");
        payload.setEmailAddress("exist@taskagile.com");
        payload.setPassword("MyPassword!");

        doThrow(EmailAddressExistsException.class)
                .when(serviceMock)
                .register(payload.toCommand());

        mvc.perform(
                post("/api/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(payload)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Email이 이미 존재합니다."));
    }

    @Test
    public void register_validPayload_shouldSucceedAndReturn201() throws Exception {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("sunny");
        payload.setEmailAddress("sunny@taskagile.com");
        payload.setPassword("MyPassword!");

        doNothing().when(serviceMock)
                .register(payload.toCommand());

        mvc.perform(
                post("/api/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(payload)))
                .andExpect(status().is(201));
    }

}

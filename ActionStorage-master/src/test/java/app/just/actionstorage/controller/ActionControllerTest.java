package app.just.actionstorage.controller;

import static app.just.actionstorage.common.TestConstants.Model.CreateNewActionRequestDtoAttributes.CREATE_NEW_ACTION_REQUEST_DTO_BUILDER1;
import static app.just.actionstorage.common.TestConstants.Model.CreateNewActionRequestDtoAttributes.CREATE_NEW_ACTION_REQUEST_DTO_BUILDER2;
import static app.just.actionstorage.common.TestConstants.Path.ACTION_CONTROLLER_POST;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import app.just.actionstorage.repository.AbstractTest;
import app.just.common.dto.CreateNewActionRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ActionControllerTest extends AbstractTest {
  @Autowired
  private MockMvc mockMvc;

  private static final CreateNewActionRequestDto ACTION_REQUEST_DTO_1 =
      CREATE_NEW_ACTION_REQUEST_DTO_BUILDER1;
  private static final CreateNewActionRequestDto ACTION_REQUEST_DTO_2 =
      CREATE_NEW_ACTION_REQUEST_DTO_BUILDER2;

  @Test
  public void whenPostListEmployees_ThenStatus200() throws Exception {
    this.mockMvc.perform(post(ACTION_CONTROLLER_POST)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(List.of(ACTION_REQUEST_DTO_1, ACTION_REQUEST_DTO_2))))
        .andDo(print())
        .andExpect(status().isOk()).andExpect(content().string(containsString("test_user_first")));
  }

  @Test
  public void whenPostIncorrectParameter_ThenStatus400() throws Exception {
    this.mockMvc.perform(post(ACTION_CONTROLLER_POST)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(ACTION_REQUEST_DTO_1)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  public static String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      return mapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

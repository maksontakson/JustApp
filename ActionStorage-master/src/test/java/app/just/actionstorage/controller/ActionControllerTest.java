package app.just.actionstorage.controller;

import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.VALID_ACTION_ENTITY_1_BUILDER;
import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.VALID_ACTION_ENTITY_2_BUILDER;
import static app.just.actionstorage.common.TestConstants.Model.CreateNewActionRequestDtoAttributes.ACTION_REQUEST_DTO_1;
import static app.just.actionstorage.common.TestConstants.Model.CreateNewActionRequestDtoAttributes.ACTION_REQUEST_DTO_2;
import static app.just.actionstorage.common.TestConstants.Path.ACTION_CONTROLLER_POST;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import app.just.actionstorage.repository.ActionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ActionControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private ActionRepository actionRepository;

  @Test
  public void whenPostListEmployees_ThenStatus200() throws Exception {
    Mockito.when(actionRepository.saveAll(any())).thenReturn(List.of(VALID_ACTION_ENTITY_1_BUILDER,
        VALID_ACTION_ENTITY_2_BUILDER));
    this.mockMvc.perform(post(ACTION_CONTROLLER_POST)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(List.of(ACTION_REQUEST_DTO_1, ACTION_REQUEST_DTO_2))))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value("3273f55f-5669-43f9-b280-1bfb87e19597"))
        .andExpect(jsonPath("$[0].username").value("test_user_first"))
        .andExpect(jsonPath("$[0].type").value("FINISH_TRANSACTION"))
        .andExpect(jsonPath("$[0].source.type").value("PHONE"))
        .andExpect(jsonPath("$[0].source.active").value("true"))
        .andExpect(jsonPath("$[1].id").value("5c6fa687-6115-4c0c-99a8-f67f4f2b8bb0"))
        .andExpect(jsonPath("$[1].username").value("test_second_user"))
        .andExpect(jsonPath("$[1].type").value("START_TRANSACTION"))
        .andExpect(jsonPath("$[1].source.type").value("EMAIL"))
        .andExpect(jsonPath("$[1].source.active").value("true"));
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

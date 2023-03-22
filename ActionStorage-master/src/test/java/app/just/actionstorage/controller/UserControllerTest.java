package app.just.actionstorage.controller;

import static app.just.actionstorage.common.TestConstants.Model.UserEntityAttributes.USER_ENTITY_1;
import static app.just.actionstorage.common.TestConstants.Model.UserEntityAttributes.USER_ENTITY_2;
import static app.just.actionstorage.common.TestConstants.Model.UserEntityAttributes.USER_ENTITY_3;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import app.just.actionstorage.common.TestConstants;
import app.just.actionstorage.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private UserRepository userRepository;

  @Test
  public void givenEmployeesInDb_whenGetEmployees_ThenStatus200() throws Exception {
    when(userRepository.findAll()).thenReturn(List.of(USER_ENTITY_1, USER_ENTITY_2, USER_ENTITY_3));
    this.mockMvc.perform(get(TestConstants.Path.USER_CONTROLLER_POST)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].username").value("test_user_first"))
        .andExpect(jsonPath("$[0].email").value("test1@gmail.com"))
        .andExpect(jsonPath("$[0].description").value("just first user"))
        .andExpect(jsonPath("$[1].username").value("test_second_user"))
        .andExpect(jsonPath("$[1].email").value("test2@test.com"))
        .andExpect(jsonPath("$[1].description").value("justUser"))
        .andExpect(jsonPath("$[2].username").value("third_user"))
        .andExpect(jsonPath("$[2].email").value("3test@test.test"))
        .andExpect(jsonPath("$[2].description").value("3 user"));
  }

  @Test
  public void givenEmptyEmpTable_whenGetEmployees_ThenStatus200() throws Exception {
    when(userRepository.findAll()).thenReturn(Collections.emptyList());
    this.mockMvc.perform(get(TestConstants.Path.USER_CONTROLLER_POST)).andDo(print())
        .andExpect(status().isOk()).andExpect(content().string("[]"));
  }
}

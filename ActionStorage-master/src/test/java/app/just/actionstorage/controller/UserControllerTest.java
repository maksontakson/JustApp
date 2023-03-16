package app.just.actionstorage.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import app.just.actionstorage.common.TestConstants;
import app.just.actionstorage.repository.AbstractTest;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
public class UserControllerTest extends AbstractTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  @DataSet(value = "addusers.yml")
  @DBUnit(caseSensitiveTableNames = true)
  public void givenEmployeesInDb_whenGetEmployees_ThenStatus200() throws Exception {
    this.mockMvc.perform(get(TestConstants.Path.USER_CONTROLLER_POST)).andDo(print())
        .andExpect(status().isOk()).andExpect(content()
            .string(containsString("[{\"username\":\"test_user_first\",\"email\":\"" +
                "test1@gmail.com\",\"description\":\"just first user\"},{\"username\":\"" +
                "test_second_user\",\"email\":\"test2@test.com\",\"description\":\"justUser\"}," +
                "{\"username\":\"third_user\",\"email\":\"3test@test.test\",\"description\"" +
                ":\"3 user\"}]")));
  }

  @Test
  @DataSet(value = "nousers.yml")
  @DBUnit(caseSensitiveTableNames = true)
  public void givenEmptyEmpTable_whenGetEmployees_ThenStatus200() throws Exception {
    this.mockMvc.perform(get(TestConstants.Path.USER_CONTROLLER_POST)).andDo(print())
        .andExpect(status().isOk()).andExpect(content().string("[]"));
  }
}

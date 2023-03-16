package app.just.actionstorage.repository;

import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.ACTION_ENTITY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import app.just.actionstorage.entity.ActionEntity;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DBRider
public class ActionRepositoryIntegrationTest extends AbstractTest {
  @Autowired
  private ActionRepository actionRepository;

  @Test
  @DataSet("source.yml")
  @DBUnit(caseSensitiveTableNames = true)
  @ExpectedDataSet("action.yml")
  public void whenFindAll_thenReturnUserEntityList() {
    List<ActionEntity> all = actionRepository.saveAll(List.of(ACTION_ENTITY));
    System.out.println(all);
    assertThat(1).isEqualTo(all.size());
  }
}

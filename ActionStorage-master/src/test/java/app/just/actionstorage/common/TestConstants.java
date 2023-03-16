package app.just.actionstorage.common;

import app.just.actionstorage.entity.ActionEntity;
import app.just.actionstorage.entity.SourceEntity;
import app.just.common.dto.ActionType;
import app.just.common.dto.SourceType;
import java.time.Instant;

public final class TestConstants {

  private TestConstants() {
  }

  public static final class Model {
    public static final String USER_NAME1 = "test_user_first";
    public static final ActionType VALID_ACTION_TYPE_START_TRANSACTION =
        ActionType.START_TRANSACTION;
    public static final Instant DATE = Instant.now();

    private Model() {
    }

    public static final class ActionEntityAttributes {

      public static final String ID = "3273f55f-5669-43f9-b280-1bfb87e19597";
      public static final SourceEntity ACTIVE_EMAIL_SOURCE_ENTITY = SourceEntity.builder()
          .type(SourceType.EMAIL)
          .active(true)
          .build();

      public static final ActionEntity ACTION_ENTITY = ActionEntity.builder()
          .id(ID)
          .username(USER_NAME1)
          .date(DATE)
          .type(TestConstants.Model.VALID_ACTION_TYPE_START_TRANSACTION)
          .source(ACTIVE_EMAIL_SOURCE_ENTITY)
          .build();

      private ActionEntityAttributes() {
      }
    }
  }
}

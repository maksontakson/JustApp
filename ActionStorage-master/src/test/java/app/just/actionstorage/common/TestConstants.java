package app.just.actionstorage.common;

import app.just.actionstorage.entity.SourceEntity;
import app.just.common.dto.ActionType;
import app.just.common.dto.SourceDto;
import app.just.common.dto.SourceType;
import java.time.Instant;
import java.util.UUID;

public final class TestConstants {

  private TestConstants() {
  }

  public static final class Model {
    public static final String USER_NAME1 = "test_user_first";

    private Model() {
    }

    public static final class UserEntityAttributes {

      public static final String EMAIL = "test@gmail.com";
      public static final String DESCRIPTION = "testDescription";

      private UserEntityAttributes() {
      }
    }
  }
}

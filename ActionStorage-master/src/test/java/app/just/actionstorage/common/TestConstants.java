package app.just.actionstorage.common;

import app.just.actionstorage.entity.ActionEntity;
import app.just.actionstorage.entity.SourceEntity;
import app.just.common.dto.ActionType;
import app.just.common.dto.CreateNewActionRequestDto;
import app.just.common.dto.SourceDto;
import app.just.common.dto.SourceType;
import java.time.Instant;
import java.util.UUID;

public final class TestConstants {

  private TestConstants() {
  }

  public static final class Model {
    public static final String USER_NAME1 = "test_user_first";
    public static final String USER_NAME2 = "test_second_user";
    public static final ActionType VALID_ACTION_TYPE_FINISH_TRANSACTION =
        ActionType.FINISH_TRANSACTION;
    public static final ActionType VALID_ACTION_TYPE_START_TRANSACTION =
        ActionType.START_TRANSACTION;
    public static final ActionType INVALID_ACTION_TYPE = ActionType.CLEAN;
    public static final Instant DATE = Instant.now();

    private Model() {
    }

    public static final class CreateNewActionRequestDtoAttributes {

      public static final SourceDto ACTIVE_EMAIL_SOURCE_DTO = SourceDto.builder()
          .type(SourceType.EMAIL)
          .active(true)
          .build();
      public static final SourceDto ACTIVE_PHONE_SOURCE_DTO = SourceDto.builder()
          .type(SourceType.PHONE)
          .active(true)
          .build();

      public static final CreateNewActionRequestDto ACTION_REQUEST_VALID_DTO_1_BUILDER =
          CreateNewActionRequestDto.builder()
              .username(USER_NAME1)
              .date(DATE)
              .type(VALID_ACTION_TYPE_FINISH_TRANSACTION)
              .source(ACTIVE_EMAIL_SOURCE_DTO)
              .build();
      public static final CreateNewActionRequestDto ACTION_REQUEST_VALID_DTO_2_BUILDER =
          CreateNewActionRequestDto.builder()
              .username(USER_NAME1)
              .date(DATE)
              .type(VALID_ACTION_TYPE_START_TRANSACTION)
              .source(ACTIVE_PHONE_SOURCE_DTO)
              .build();
      public static final CreateNewActionRequestDto ACTION_REQUEST_INVALID_DTO_BUILDER =
          CreateNewActionRequestDto.builder()
              .username(USER_NAME2)
              .date(DATE)
              .type(INVALID_ACTION_TYPE)
              .source(ACTIVE_PHONE_SOURCE_DTO)
              .build();


      private CreateNewActionRequestDtoAttributes() {
      }
    }

    public static final class ActionEntityAttributes {

      public static final String ID = UUID.randomUUID().toString();
      public static final SourceEntity ACTIVE_EMAIL_SOURCE_ENTITY = SourceEntity.builder()
          .type(SourceType.EMAIL)
          .active(true)
          .build();
      public static final SourceEntity ACTIVE_PHONE_SOURCE_ENTITY = SourceEntity.builder()
          .type(SourceType.PHONE)
          .active(true)
          .build();

      public static final ActionEntity VALID_ACTION_ENTITY_1_BUILDER = ActionEntity.builder()
          .id(ID)
          .username(USER_NAME1)
          .date(DATE)
          .type(VALID_ACTION_TYPE_FINISH_TRANSACTION)
          .source(ACTIVE_EMAIL_SOURCE_ENTITY)
          .build();
      public static final ActionEntity VALID_ACTION_ENTITY_2_BUILDER = ActionEntity.builder()
          .id(ID)
          .username(USER_NAME1)
          .date(DATE)
          .type(VALID_ACTION_TYPE_START_TRANSACTION)
          .source(ACTIVE_PHONE_SOURCE_ENTITY)
          .build();
      public static final ActionEntity INVALID_ACTION_ENTITY_1_BUILDER = ActionEntity.builder()
          .id(ID)
          .username(USER_NAME2)
          .date(DATE)
          .type(INVALID_ACTION_TYPE)
          .source(ACTIVE_PHONE_SOURCE_ENTITY)
          .build();

      private ActionEntityAttributes() {
      }
    }
  }
}

package app.just.actionstorage.common;

import app.just.actionstorage.entity.ActionEntity;
import app.just.actionstorage.entity.SourceEntity;
import app.just.actionstorage.entity.UserEntity;
import app.just.common.dto.ActionType;
import app.just.common.dto.CreateNewActionRequestDto;
import app.just.common.dto.SourceDto;
import app.just.common.dto.SourceType;
import java.time.Instant;

public final class TestConstants {

  private TestConstants() {
  }

  public static final class Path {

    public static final String ACTION_CONTROLLER_POST = "/actions/save";
    public static final String USER_CONTROLLER_POST = "/users";

    private Path() {
    }
  }

  public static final class Model {
    public static final String USER_NAME1 = "test_user_first";
    public static final String USER_NAME2 = "test_second_user";
    public static final String USER_NAME3 = "third_user";
    public static final ActionType VALID_ACTION_TYPE_FINISH_TRANSACTION =
        ActionType.FINISH_TRANSACTION;
    public static final ActionType VALID_ACTION_TYPE_START_TRANSACTION =
        ActionType.START_TRANSACTION;
    public static final ActionType INVALID_ACTION_TYPE = ActionType.CLEAN;
    public static final Instant DATE = Instant.now();

    private Model() {
    }

    public static final class UserEntityAttributes {

      public static final String EMAIL1 = "test1@gmail.com";
      public static final String DESCRIPTION1 = "just first user";
      public static final String EMAIL2 = "test2@test.com";
      public static final String DESCRIPTION2 = "justUser";
      public static final String EMAIL3 = "3test@test.test";
      public static final String DESCRIPTION3 = "3 user";
      public static final UserEntity USER_ENTITY1 = UserEntity.builder()
          .username(USER_NAME1)
          .email(EMAIL1)
          .description(DESCRIPTION1)
          .build();
      public static final UserEntity USER_ENTITY2 = UserEntity.builder()
          .username(USER_NAME2)
          .email(EMAIL2)
          .description(DESCRIPTION2)
          .build();
      public static final UserEntity USER_ENTITY3 = UserEntity.builder()
          .username(USER_NAME3)
          .email(EMAIL3)
          .description(DESCRIPTION3)
          .build();

      private UserEntityAttributes() {
      }
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

      public static final CreateNewActionRequestDto ACTION_REQUEST_VALID_DTO_1 =
          CreateNewActionRequestDto.builder()
              .username(USER_NAME1)
              .type(VALID_ACTION_TYPE_START_TRANSACTION)
              .date(DATE)
              .source(ACTIVE_EMAIL_SOURCE_DTO)
              .build();

      public static final CreateNewActionRequestDto ACTION_REQUEST_VALID_DTO_2 =
          CreateNewActionRequestDto.builder()
              .source(ACTIVE_PHONE_SOURCE_DTO)
              .build();

      public static final CreateNewActionRequestDto ACTION_REQUEST_INVALID_DTO =
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
      public static final String ID = "3273f55f-5669-43f9-b280-1bfb87e19597";
      public static final String ID2 = "5c6fa687-6115-4c0c-99a8-f67f4f2b8bb0";
      public static final SourceEntity ACTIVE_EMAIL_SOURCE_ENTITY = SourceEntity.builder()
          .type(SourceType.EMAIL)
          .active(true)
          .build();
      public static final SourceEntity ACTIVE_PHONE_SOURCE_ENTITY = SourceEntity.builder()
          .type(SourceType.PHONE)
          .active(true)
          .build();
      public static final ActionEntity VALID_ACTION_ENTITY_1= ActionEntity.builder()
          .id(ID)
          .username(USER_NAME1)
          .date(DATE)
          .type(VALID_ACTION_TYPE_FINISH_TRANSACTION)
          .source(ACTIVE_PHONE_SOURCE_ENTITY)
          .build();
      public static final ActionEntity VALID_ACTION_ENTITY_2= ActionEntity.builder()
          .id(ID2)
          .username(USER_NAME2)
          .date(DATE)
          .type(VALID_ACTION_TYPE_START_TRANSACTION)
          .source(ACTIVE_EMAIL_SOURCE_ENTITY)
          .build();
      public static final ActionEntity INVALID_ACTION_ENTITY_1= ActionEntity.builder()
          .id(ID2)
          .username(USER_NAME2)
          .date(DATE)
          .type(INVALID_ACTION_TYPE)
          .source(ACTIVE_EMAIL_SOURCE_ENTITY)
          .build();

      private ActionEntityAttributes() {
      }
    }
    public static final class UserEntityAttributes {
      public static final String EMAIL = "test@gmail.com";
      public static final String DESCRIPTION = "testDescription";
      public static final UserEntity USER_ENTITY = UserEntity.builder()
          .username(USER_NAME1)
          .email(EMAIL)
          .description(DESCRIPTION)
          .build();

      private UserEntityAttributes() {
      }
    }
  }
}

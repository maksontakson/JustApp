package app.just.actionstorage.common;

import app.just.actionstorage.entity.UserEntity;

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

package app.just.actionstorage.service;

import app.just.actionstorage.entity.UserEntity;
import app.just.actionstorage.mapper.UserEntityMapperImpl;
import app.just.actionstorage.repository.UserRepository;
import app.just.actionstorage.serivce.UserService;
import app.just.common.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static app.just.actionstorage.common.TestConstants.Model.UserEntityAttributes.USER_ENTITY_BUILDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {
  private static final UserEntity USER_ENTITY = USER_ENTITY_BUILDER;
  @InjectMocks
  private UserService userService;
  @Mock
  private UserRepository userRepository;
  @Mock
  private UserEntityMapperImpl userEntityMapper;

  @Test
  void whenFindAll_ThenReturnListOfMappedDto() {
    when(userRepository.findAll()).thenReturn(List.of(USER_ENTITY));
    when(userEntityMapper.toDto(USER_ENTITY)).thenCallRealMethod();
    List<UserDto> all = userService.findAll();

    assertThat(1).isEqualTo(all.size());
  }

  @Test
  void whenFindAllReturnNull_ThenNullPointerExceptionIsThrown() {
    when(userRepository.findAll()).thenReturn(null);
    assertThatThrownBy(() -> userService.findAll()).isInstanceOf(NullPointerException.class);
  }
}

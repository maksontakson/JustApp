package app.just.actionstorage.service;

import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.ACTIVE_EMAIL_SOURCE_ENTITY;
import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.ACTIVE_PHONE_SOURCE_ENTITY;
import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.INVALID_ACTION_ENTITY_1;
import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.VALID_ACTION_ENTITY_1;
import static app.just.actionstorage.common.TestConstants.Model.ActionEntityAttributes.VALID_ACTION_ENTITY_2;
import static app.just.actionstorage.common.TestConstants.Model.CreateNewActionRequestDtoAttributes.ACTION_REQUEST_INVALID_DTO;
import static app.just.actionstorage.common.TestConstants.Model.CreateNewActionRequestDtoAttributes.ACTION_REQUEST_VALID_DTO_1;
import static app.just.actionstorage.common.TestConstants.Model.CreateNewActionRequestDtoAttributes.ACTION_REQUEST_VALID_DTO_2;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.just.actionstorage.entity.ActionEntity;
import app.just.actionstorage.entity.SourceEntity;
import app.just.actionstorage.mapper.ActionEntityMapper;
import app.just.actionstorage.repository.ActionRepository;
import app.just.actionstorage.repository.SourceRepository;
import app.just.actionstorage.serivce.ActionService;
import app.just.common.dto.ActionDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ActionServiceUnitTest {
  @InjectMocks
  private ActionService actionService;
  @Mock
  private ActionEntityMapper actionEntityMapper;
  @Mock
  private ActionRepository actionRepository;
  @Mock
  private SourceRepository sourceRepository;

  @Test
  void givenTestListsOfEntities_WhenProceedNewActionsWithValidActionRequestDtos_ThenReturnActionDtoList() {
    List<ActionEntity> actionEntityList = new ArrayList<>();
    addActionEntities(actionEntityList, VALID_ACTION_ENTITY_1, VALID_ACTION_ENTITY_2);
    List<SourceEntity> sourceEntityList = new ArrayList<>();
    addSourceEntities(sourceEntityList, ACTIVE_EMAIL_SOURCE_ENTITY, ACTIVE_PHONE_SOURCE_ENTITY);

    when(actionEntityMapper.toNewEntity(ACTION_REQUEST_VALID_DTO_1)).thenReturn(
        VALID_ACTION_ENTITY_1);
    when(actionEntityMapper.toNewEntity(ACTION_REQUEST_VALID_DTO_2)).thenReturn(
        VALID_ACTION_ENTITY_2);
    when(actionRepository.saveAll(any())).thenReturn(actionEntityList);

    List<ActionDto> actionDtoList =
        actionService.proceedNewActions(List.of(ACTION_REQUEST_VALID_DTO_1,
            ACTION_REQUEST_VALID_DTO_2));

    verify(sourceRepository, only()).saveAll(sourceEntityList);
    verify(actionRepository, only()).saveAll(actionEntityList);
    verify(actionEntityMapper, times(1)).toDto(VALID_ACTION_ENTITY_1);
    verify(actionEntityMapper, times(1)).toDto(VALID_ACTION_ENTITY_2);
    assertThat(2).isEqualTo(actionDtoList.size());
  }

  @Test
  void givenTestListsOfEntities_WhenProceedNewActionsWithInvalidActionRequestDtos_ThenReturnEmptyList() {
    List<ActionEntity> actionEntityList = new ArrayList<>();
    addActionEntities(actionEntityList, VALID_ACTION_ENTITY_1, INVALID_ACTION_ENTITY_1);
    List<SourceEntity> sourceEntityList = new ArrayList<>();
    addSourceEntities(sourceEntityList, ACTIVE_EMAIL_SOURCE_ENTITY, ACTIVE_PHONE_SOURCE_ENTITY);

    when(actionEntityMapper.toNewEntity(ACTION_REQUEST_VALID_DTO_1)).thenReturn(
        VALID_ACTION_ENTITY_1);
    when(actionEntityMapper.toNewEntity(ACTION_REQUEST_INVALID_DTO)).thenReturn(
        INVALID_ACTION_ENTITY_1);

    List<ActionDto> actionDtoList =
        actionService.proceedNewActions(List.of(ACTION_REQUEST_VALID_DTO_1, ACTION_REQUEST_INVALID_DTO));

    Mockito.verify(actionEntityMapper, Mockito.times(2)).toNewEntity(any());
    verify(sourceRepository, never()).saveAll(sourceEntityList);
    verify(actionRepository, never()).saveAll(actionEntityList);
    verify(actionEntityMapper, never()).toDto(VALID_ACTION_ENTITY_1);
    verify(actionEntityMapper, never()).toDto(INVALID_ACTION_ENTITY_1);
    assertThat(actionDtoList.size()).isZero();
  }

  @Test
  void givenTestListsOfEntity_WhenProceedNewActionsWithInvalidActionRequestSingleDto_ThenReturnEmptyList() {
    List<ActionEntity> actionEntityList = new ArrayList<>();
    addActionEntities(actionEntityList, VALID_ACTION_ENTITY_1);
    List<SourceEntity> sourceEntityList = new ArrayList<>();
    addSourceEntities(sourceEntityList, ACTIVE_EMAIL_SOURCE_ENTITY);

    when(actionEntityMapper.toNewEntity(ACTION_REQUEST_VALID_DTO_1)).thenReturn(
        VALID_ACTION_ENTITY_1);

    List<ActionDto> actionDtoList =
        actionService.proceedNewActions(List.of(ACTION_REQUEST_VALID_DTO_1));

    verify(sourceRepository, never()).saveAll(sourceEntityList);
    verify(actionRepository, never()).saveAll(actionEntityList);
    verify(actionEntityMapper, never()).toDto(VALID_ACTION_ENTITY_1);
    assertThat(actionDtoList.size()).isZero();
  }

  private static void addActionEntities(List<ActionEntity> entityList, ActionEntity ... actionEntities) {
    entityList.addAll(Arrays.asList(actionEntities));
  }
  private static void addSourceEntities(List<SourceEntity> entityList, SourceEntity ... actionEntities) {
    entityList.addAll(Arrays.asList(actionEntities));
  }
}

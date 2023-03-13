package app.just.actionproducer.service;

import app.just.actionproducer.client.ActionClient;
import app.just.common.dto.ActionDto;
import app.just.common.dto.ActionType;
import app.just.common.dto.CreateNewActionRequestDto;
import app.just.common.dto.SourceDto;
import app.just.common.dto.SourceType;
import app.just.common.dto.UserDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ActionService {
    public static final int MAX_COUNT_ACTIONS = 150;

    public final ActionClient actionClient;

    public ActionService(ActionClient actionClient) {
        this.actionClient = actionClient;
    }

    public List<ActionDto> saveActionRequest(List<CreateNewActionRequestDto> requests) {
        return actionClient.save(requests);
    }

    public List<CreateNewActionRequestDto> generateActionByUser(UserDto user) {
        return IntStream.range(0, new Random().nextInt(MAX_COUNT_ACTIONS))
                .boxed()
                .map(index -> generateRandomActionByUser(user))
                .collect(Collectors.toList());
    }

    private CreateNewActionRequestDto generateRandomActionByUser(UserDto user) {
        return CreateNewActionRequestDto.builder()
                .type(generateActionType())
                .date(Instant.now())
                .username(user.getUsername())
                .source(generateSourceDto())
                .build();
    }

    private SourceDto generateSourceDto() {
        return SourceDto.builder()
                .type(generateRandomSourceType())
                .active(generateRandomActiveStatus())
                .build();
    }

    private boolean generateRandomActiveStatus() {
        return Math.random() < 0.5;
    }

    private SourceType generateRandomSourceType() {
        final SourceType[] values = SourceType.values();
        int index = new Random().nextInt(values.length);
        return values[index];
    }

    private ActionType generateActionType() {
        final ActionType[] values = ActionType.values();
        int index = new Random().nextInt(values.length);
        return values[index];
    }
}

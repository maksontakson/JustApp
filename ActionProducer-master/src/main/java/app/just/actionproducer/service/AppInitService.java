package app.just.actionproducer.service;

import app.just.common.dto.ActionDto;
import app.just.common.dto.CreateNewActionRequestDto;
import app.just.common.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AppInitService {

    public final UserService userService;
    public final ActionService actionService;

    public AppInitService(UserService userService, ActionService actionService) {
        this.userService = userService;
        this.actionService = actionService;
    }


    @PostConstruct
    public void initRun() {
        generateActionsForUsers();
    }

    public void generateActionsForUsers() {
        List<UserDto> users = userService.findUsers();

        List<CreateNewActionRequestDto> actionsRequest = users.stream()
                .map(actionService::generateActionByUser)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<ActionDto> actions = actionService.saveActionRequest(actionsRequest);

        Map<String, List<ActionDto>> actionsByUsers = actions.stream()
                .collect(Collectors.groupingBy(ActionDto::getUsername));

        for (UserDto user : users) {
            List<ActionDto> actionsByUser = actionsByUsers.get(user.getUsername());
            if (actionsByUser != null && !actionsByUser.isEmpty()) {
                userService.notifyUserAboutSuccessActions(user, actionsByUser);
            }
        }
    }
}

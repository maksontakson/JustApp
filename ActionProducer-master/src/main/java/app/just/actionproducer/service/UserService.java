package app.just.actionproducer.service;

import app.just.actionproducer.client.UserClient;
import app.just.common.dto.ActionDto;
import app.just.common.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public List<UserDto> findUsers() {
        return userClient.getUsers();
    }

    public void notifyUserAboutSuccessActions(UserDto user, List<ActionDto> actions) {
        //TODO implement in future
        System.out.println(user + " got actions: " + actions);
    }
}

package app.just.actionproducer.client;

import app.just.common.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "userClient", url = "http://localhost:8002")
public interface UserClient {

    @GetMapping("/users")
    List<UserDto> getUsers();
}

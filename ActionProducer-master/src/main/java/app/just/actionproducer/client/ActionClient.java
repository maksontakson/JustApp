package app.just.actionproducer.client;

import app.just.common.dto.ActionDto;
import app.just.common.dto.CreateNewActionRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "actionClient", url = "http://localhost:8002")
public interface ActionClient {

    @PostMapping("actions/save")
    List<ActionDto> save(List<CreateNewActionRequestDto> newActions);

}

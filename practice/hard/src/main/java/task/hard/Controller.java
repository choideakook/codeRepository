package task.hard;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final Repository repository;

    @PostMapping("/chat/writeMessage")
    public CreateMessageResponse saveMessage(@RequestBody @Valid Message message) {
        Message saveMessage = repository.save(message);
        return new CreateMessageResponse("성공", saveMessage.getUuid());
    }

    @Data
    static class CreateMessageResponse{
        private String msg;
        private UUID uuid;
        public CreateMessageResponse(String msg, UUID uuid) {
            this.msg = msg;
            this.uuid = uuid;
        }
    }

}

package task.hard2;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.UUID;

@org.springframework.stereotype.Controller
public class Controller {

    private final Repository repository;

    @Autowired
    public Controller(Repository repository) {
        this.repository = repository;
    }

    @PostMapping("/chat/writeMessage")
    public CreateMessageResponse saveMessage(
            @RequestBody @Valid Message message
    ){
        Long messageId = repository.save(message);
        Message findMessage = repository.findById(messageId).get();
        return new CreateMessageResponse(messageId, "성공",findMessage.getUuid());
    }

    @Data
    static class CreateMessageResponse {
        private Long resultCode;
        private String msg;
        private UUID uuid;

        public CreateMessageResponse(Long resultCode, String msg, UUID uuid) {
            this.resultCode = resultCode;
            this.msg = msg;
            this.uuid = uuid;
        }
    }
}

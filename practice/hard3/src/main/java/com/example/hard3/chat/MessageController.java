package com.example.hard3.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    @PostMapping("/chat/writeMessage")
    public CreateMessageResponse saveMessage(
            @RequestBody @Valid CreateMessageRequest request
    ){
        Message message = new Message(request.getAuthorName(), request.content);
        UUID uuid = service.save(message);
        return new CreateMessageResponse("S-1","성공", uuid);
    }

    @Data
    static class CreateMessageRequest{
        private String authorName;
        private String content;
    }

    @Data
    static class CreateMessageResponse{
        private String resultCode;
        private String msg;
        private UUID uuid;

        public CreateMessageResponse(String resultCode, String msg, UUID uuid) {
            this.resultCode = resultCode;
            this.msg = msg;
            this.uuid = uuid;
        }
    }

    @GetMapping("/chat/message")
    public Result findMessages() {
        List<Message> findMessages = service.findAll();
        List<MessageDto> collect = findMessages.stream()
                .map(m -> new MessageDto(
                        m.getUuid(),
                        m.getCreateDate(),
                        m.getAuthorName(),
                        m.getContent()
                ))
                .collect(Collectors.toList());
        return new Result("S-1","성공", collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private String resultCode;
        private String msg;
        private List<T> messages;
    }

    @Data
    @AllArgsConstructor
    static class MessageDto{
        private UUID uuid;
        private LocalDateTime createDate;
        private String authorName;
        private String content;
    }


}

package xyz.lunfee.laserclient.controller;

//import com.mycompany.bitcoinclient.model.ChatComment;
//import com.mycompany.bitcoinclient.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class LaserController {

//    private final SimpMessagingTemplate simpMessagingTemplate;


    @GetMapping("/")
    public String getLaser() {
        return "laser";
    }
    //收到信息自动响应
//    @MessageMapping("/chat")
//    public void addChatComment(@Payload Comment comment) {
//        //ChatComment的静态构造方法（通过 @Value(staticConstructor = "of") 注解）
//        ChatComment chatComment = ChatComment.of(comment.getFromUser(), comment.getMessage(), LocalDateTime.now());
//        if (comment.getToUser().isEmpty()) {
//            //封装@SendTo注解
//            //需要配置Websocket
//            simpMessagingTemplate.convertAndSend("/topic/comments", chatComment);
//        } else {
//            simpMessagingTemplate.convertAndSendToUser(comment.getToUser(), "/topic/comments", chatComment);
//        }
//    }
}

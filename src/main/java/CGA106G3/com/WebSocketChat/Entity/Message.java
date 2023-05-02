package CGA106G3.com.WebSocketChat.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    private Integer senderId;
    private String senderName;
    private Integer receiverId;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm", timezone = "GMT+8")
    private String date;
    private Status status;

}

package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatDto implements Dto {
    private final String message;
    private final String nameSender;
    private final long userRecipientId;
    private final long idDiscussion;

    public ChatDto(String message, String nameSender, long userRecipientId) {
        this.message = message;
        this.nameSender = nameSender;
        this.userRecipientId = userRecipientId;
        this.idDiscussion = -1;
    }

    public ChatDto(String nameSender, String message) {
        this.nameSender = nameSender;
        this.message = message;
        this.userRecipientId = -1;
        this.idDiscussion = -1;
    }

    public ChatDto(String message, long userRecipientId) {
        this.message = message;
        this.userRecipientId = userRecipientId;
        this.nameSender = null;
        this.idDiscussion = -1;
    }

    public ChatDto(String message) {
        this.message = message;
        this.userRecipientId = -1;
        this.nameSender = null;
        this.idDiscussion = -1;
    }

    @Override
    public String toString() {
        return "ChatDto{" +
                "message='" + message + '\'' +
                ", nameSender='" + nameSender + '\'' +
                ", userRecipientId=" + userRecipientId +
                '}';
    }
}

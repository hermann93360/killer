package transfert;

import dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import static transfert.Transfer.Type.NOTIFICATION;
import static transfert.Transfer.Type.REQUEST;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Transfer implements Serializable {

    public enum Type{
        NOTIFICATION,
        REQUEST,
        RESPONSE
    }

    private String path;
    private Dto dto;
    private Type type;
    private long idClient;

    public Transfer(String path, Dto dto, Type type) {
        this.path = path;
        this.dto = dto;
        this.type = type;
    }

    public boolean isRequest(){
        return type.equals(REQUEST);
    }

    public boolean isNotification(){
        return type.equals(NOTIFICATION);
    }

}

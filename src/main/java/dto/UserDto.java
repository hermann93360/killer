package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto implements Dto {
    private String name;
    private long identifier;
    private long idRoom;
    private String pathPlayerImg;

    public UserDto(String name, long identifier, long idRoom) {
        this.name = name;
        this.identifier = identifier;
        this.idRoom = idRoom;
    }

    public UserDto(String name, long identifier) {
        this.name = name;
        this.identifier = identifier;
    }

    public UserDto(String name) {
        this.name = name;
    }

    public UserDto(long id){
        this.identifier = id;
    }



    @Override
    public String toString() {
        return "ConnectionDto{" +
                "name='" + name + '\'' +
                ", identifier=" + identifier +
                '}';
    }
}

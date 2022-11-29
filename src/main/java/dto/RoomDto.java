package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RoomDto implements Dto{
    private long id;
    private String name;
    private int numberOfUser;
    private List<String> pathAllIconsOfUser;

    public RoomDto(long id, String name, int numberOfUser) {
        this.id = id;
        this.name = name;
        this.numberOfUser = numberOfUser;
    }

    public RoomDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoomDto(long id) {
        this.id = id;
    }

    public RoomDto(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfUser=" + numberOfUser +
                ", pathAllIconsOfUser=" + pathAllIconsOfUser +
                '}';
    }
}

package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Stack;

@AllArgsConstructor
@Getter
public class DiscussionDto implements Dto {
    private final long id;
    private Stack<ChatDto> listChat;
    private final String name1;
    private final String name2;

    @Override
    public String toString() {
        return "DiscussionDto{" +
                "listChat=" + listChat +
                ", name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                '}';
    }
}

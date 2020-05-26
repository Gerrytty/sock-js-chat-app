package app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {

    private String from;
    private String room;

    @Override
    public boolean equals(Object o) {

        if(o instanceof ChatDto) {
            return ((ChatDto) o).from.equals(from) && ((ChatDto) o).room.equals(room);
        }

        else return false;

    }

    @Override
    public int hashCode() {
        String s = from.hashCode() + " " + room.hashCode();
        return s.hashCode();
    }

}

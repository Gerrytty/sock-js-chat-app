package app.dto;

import app.models.ChatUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {

    private Long roomId;
    private String roomName;

    // TODO

//    public static UserDto from(ChatUser chatUser) {
//        return UserDto.builder()
//                .userId(chatUser.getChat_user_id())
//                .username(chatUser.getUsername())
//                .build();
//    }
//
//    public static List<UserDto> from(List<ChatUser> chatUsers) {
//        return chatUsers.stream().map(UserDto::from).collect(Collectors.toList());
//    }

}

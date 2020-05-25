package app.services;

import app.dto.UserDto;
import app.models.ChatUser;
import app.models.UsersInRoom;
import app.repositories.ChatUsersRepository;
import app.repositories.UsersInRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl {

    private final ChatUsersRepository chatUsersRepository;
    private final UsersInRoomRepository usersInRoomRepository;

    @Autowired
    public UsersServiceImpl(ChatUsersRepository chatUsersRepository, UsersInRoomRepository usersInRoomRepository) {
        this.chatUsersRepository = chatUsersRepository;
        this.usersInRoomRepository = usersInRoomRepository;
    }

    public List<UserDto> getAllUsers() {
        return UserDto.from(chatUsersRepository.findAll());
    }

    public void addToRoom(String userName, Long roomId) {

        Optional<ChatUser> optionalChatUser =  chatUsersRepository.findByUsername(userName);

        optionalChatUser.ifPresent(chatUser -> usersInRoomRepository.save(UsersInRoom.builder()
                .roomId(roomId)
                .userId(chatUser.getChat_user_id()).build()));

    }

}

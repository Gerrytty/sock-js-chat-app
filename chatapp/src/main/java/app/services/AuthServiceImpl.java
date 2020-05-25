package app.services;

import app.dto.AuthDto;
import app.models.ChatUser;
import app.repositories.ChatUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    private final ChatUsersRepository chatUsersRepository;

    @Autowired
    public AuthServiceImpl(ChatUsersRepository chatUsersRepository) {
        this.chatUsersRepository = chatUsersRepository;
    }

    // TODO add password hashing
    public void register(AuthDto authDto) {

        ChatUser chatUser = ChatUser.builder()
                .username(authDto.getUsername())
                .password(authDto.getPassword())
                .build();

        chatUsersRepository.save(chatUser);

    }

    // TODO add password hashing
    public boolean auth(AuthDto authDto) {
        return chatUsersRepository.findByUsername(authDto.getUsername())
                .filter(chatUser -> authDto.getPassword().equals(chatUser.getPassword())).isPresent();
    }

}

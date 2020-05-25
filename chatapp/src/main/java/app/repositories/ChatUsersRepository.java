package app.repositories;

import app.models.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatUsersRepository extends JpaRepository<ChatUser, Long> {

    Optional<ChatUser> findByUsername(String username);

}

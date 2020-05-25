package app.repositories;

import app.models.UsersInRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersInRoomRepository extends JpaRepository<UsersInRoom, Long> {
}

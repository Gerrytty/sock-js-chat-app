package app.repositories;

import app.models.UsersInRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersInRoomRepository extends JpaRepository<UsersInRoom, Long> {
}

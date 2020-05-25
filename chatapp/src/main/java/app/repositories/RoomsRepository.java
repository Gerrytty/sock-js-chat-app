package app.repositories;

import app.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepository extends JpaRepository<Room, Long> {

    @Query(value = "select r.* from room r inner join users_in_room on r.room_id = users_in_room.room_id " +
            "where user_id = ?1",
    nativeQuery = true)
    List<Room> getAllByUserId(Long userId);

}

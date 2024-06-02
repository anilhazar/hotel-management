package repository;


import room.Room;
import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    void save(Room room);
    void update(Room room);
    Optional<Room> findById(Long id);
    List<Room> findAll();
}

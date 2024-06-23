package repository;

import entity.room.SpecialRoom;

import java.util.List;
import java.util.Optional;

public interface SpecialRoomRepository {
    void save(SpecialRoom specialRoom);
    void update(SpecialRoom specialRoom);
    Optional<SpecialRoom> findById(Long id);
    List<SpecialRoom> findAll();

}

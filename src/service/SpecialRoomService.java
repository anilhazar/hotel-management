package service;

import entity.room.SpecialRoom;

import java.util.List;

public interface SpecialRoomService {
    void createSpecialRoom(SpecialRoom specialRoom);

    SpecialRoom findSpecialRoomById(Long specialRoomId);

    List<SpecialRoom> findAllSpecialRooms();

    void updateSpecialRoom(SpecialRoom specialRoom);

}


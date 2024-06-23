package service;

import entity.room.Room;

import java.util.List;

public interface RoomService {
    void createRoom(Room room);
    Room findRoomById(Long roomId);
    List<Room> findAllRooms();
    void updateRoom(Room room);
}

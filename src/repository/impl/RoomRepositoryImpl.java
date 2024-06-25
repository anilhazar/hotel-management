package repository.impl;

import database.ConnectionManager;
import entity.room.Room;
import entity.room.enums.Status;
import entity.room.enums.Type;
import repository.RoomRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepositoryImpl implements RoomRepository {

    public RoomRepositoryImpl() {
    }

    @Override
    public void save(Room room) {
        String query = "INSERT INTO room (capacity, base_price, status, type) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, room.getCapacity());
            pstmt.setInt(2, room.getBasePrice());
            pstmt.setString(3, room.getStatus().name());
            pstmt.setString(4, room.getType().name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Room room) {
        String query = "UPDATE room SET capacity = ?, base_price = ?, status = ?, type = ? WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, room.getCapacity());
            pstmt.setInt(2, room.getBasePrice());
            pstmt.setString(3, room.getStatus().name());
            pstmt.setString(4, room.getType().name());
            pstmt.setLong(5, room.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Room> findById(Long id) {
        String query = "SELECT * FROM room WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Room(
                        rs.getLong("id"),
                        rs.getInt("capacity"),
                        Status.valueOf(rs.getString("status")),
                        Type.valueOf(rs.getString("type"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM room";
        try (Connection connection = ConnectionManager.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                rooms.add(new Room(
                        rs.getLong("id"),
                        rs.getInt("capacity"),
                        Status.valueOf(rs.getString("status")),
                        Type.valueOf(rs.getString("type"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
        return rooms;
    }

}


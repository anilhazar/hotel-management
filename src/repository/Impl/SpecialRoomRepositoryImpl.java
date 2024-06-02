package repository.Impl;

import database.ConnectionManager;
import repository.SpecialRoomRepository;
import room.SpecialRoom;
import room.enums.SpecialFeature;
import room.enums.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SpecialRoomRepositoryImpl implements SpecialRoomRepository {
    @Override
    public void save(SpecialRoom specialRoom) {
        String query = "INSERT INTO room (capacity, base_price, status, type) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, specialRoom.getCapacity());
            pstmt.setInt(2, specialRoom.getBasePrice());
            pstmt.setString(3, specialRoom.getStatus().name());
            pstmt.setString(4, specialRoom.getType().name());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                Long roomId = rs.getLong(1);
                for (SpecialFeature feature : specialRoom.getSpecialFeatures()) {
                    String featureSql = "INSERT INTO special_feature (room_id, feature_name, feature_price) VALUES (?, ?, ?)";
                    try (PreparedStatement featureStmt = connection.prepareStatement(featureSql)) {
                        featureStmt.setLong(1, roomId);
                        featureStmt.setString(2, feature.getName());
                        featureStmt.setInt(3, feature.getPrice());
                        featureStmt.executeUpdate();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(SpecialRoom specialRoom) {
        String query = "UPDATE room SET capacity = ?, base_price = ?, status = ?, type = ? WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, specialRoom.getCapacity());
            pstmt.setInt(2, specialRoom.getBasePrice());
            pstmt.setString(3, specialRoom.getStatus().name());
            pstmt.setString(4, specialRoom.getType().name());
            pstmt.setLong(5, specialRoom.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Optional<SpecialRoom> findById(Long id) {
        String query = "SELECT * FROM room WHERE id = ? AND type = 'SPECIAL'";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                SpecialRoom specialRoom = new SpecialRoom(
                        rs.getLong("id"),
                        rs.getInt("capacity"),
                        rs.getInt("base_price"),
                        Type.SPECIAL
                );
                String featureSql = "SELECT * FROM special_feature WHERE room_id = ?";
                try (PreparedStatement featureStmt = connection.prepareStatement(featureSql)) {
                    featureStmt.setLong(1, id);
                    ResultSet featureRs = featureStmt.executeQuery();
                    List<SpecialFeature> specialFeatures = new ArrayList<>();
                    while (featureRs.next()) {
                        specialFeatures.add(SpecialFeature.valueOf(featureRs.getString("feature_name")));
                    }
                    specialRoom.setSpecialFeatures(specialFeatures);
                }
                return Optional.of(specialRoom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<SpecialRoom> findAll() {

            List<SpecialRoom> specialRooms = new ArrayList<>();
            String query = "SELECT r.id, r.capacity, r.base_price, sf.feature_name " +
                    "FROM room r " +
                    "JOIN special_feature sf ON r.id = sf.room_id " +
                    "WHERE r.type = 'SPECIAL'";
            try (Connection connection = ConnectionManager.getConnection();
                 Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Long roomId = rs.getLong("id");
                    SpecialRoom specialRoom = specialRooms.stream()
                            .filter(room -> Objects.equals(room.getId(), roomId))
                            .findFirst()
                            .orElseGet(() -> {
                                SpecialRoom currentRoom = null;
                                try {
                                    currentRoom = new SpecialRoom(
                                            roomId,
                                            rs.getInt("capacity"),
                                            rs.getInt("base_price"),
                                            Type.SPECIAL
                                    );
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                specialRooms.add(currentRoom);
                                return currentRoom;
                            });
                    specialRoom.addSpecialFeatureByName(rs.getString("feature_name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return List.of();
            }
            return specialRooms;
        }

    }

package repository.impl;

import database.ConnectionManager;
import entity.reservation.Reservation;
import repository.ReservationRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ReservationRepositoryImpl implements ReservationRepository {
    public ReservationRepositoryImpl() {
    }

    @Override
    public void save(Reservation reservation)  {
        String sql = "INSERT INTO reservation (customer_id, room_id, checkInDate, checkOutDate, extended_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
             pstmt.setLong(1, reservation.getCustomerId());
             pstmt.setLong(2, reservation.getRoomId());
             pstmt.setDate(3, reservation.getCheckInDate());
             pstmt.setDate(4, reservation.getCheckOutDate());
             pstmt.setDouble(5, reservation.getExtendedPrice());
             pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(Reservation reservation) {
        String sql = "UPDATE reservation SET customer_id = ?, room_id = ?, checkInDate = ?, checkOutDate = ?, extended_price = ? WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
             pstmt.setLong(1, reservation.getCustomerId());
             pstmt.setLong(2, reservation.getRoomId());
             pstmt.setDate(3, reservation.getCheckInDate());
             pstmt.setDate(4, reservation.getCheckOutDate());
             pstmt.setDouble(5, reservation.getExtendedPrice());
             pstmt.setLong(6, reservation.getId());
             pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        String sql = "SELECT * FROM reservation WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Reservation(
                        rs.getLong("customer_id"),
                        rs.getDate("checkInDate"),
                        rs.getDate("checkOutDate"),
                        rs.getDouble("extended_price")
                ));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation";
        try (Connection connection = ConnectionManager.getConnection();
                Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getLong("customer_id"),
                        rs.getDate("checkInDate"),
                        rs.getDate("checkOutDate"),
                        rs.getDouble("extended_price")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservations;
    }
}

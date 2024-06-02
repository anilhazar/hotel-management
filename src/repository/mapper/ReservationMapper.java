package repository.mapper;
import reservation.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;

public  class ReservationMapper {
        public static Reservation map(ResultSet rs) throws SQLException {
            Reservation reservation = new Reservation(
                    rs.getLong("customer_id"),
                    rs.getLong("room_id"),
                    rs.getDate("checkInDate"),
                    rs.getDate("checkOutDate"),
                    rs.getDouble("extended_price")
            );
            reservation.setId(rs.getLong("id"));
            return reservation;
        }
    }


package repository.mapper;

import room.Room;
import room.enums.Status;
import room.enums.Type;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper {
    public static Room map(ResultSet rs) throws SQLException {
        Room room = new Room(
                rs.getInt("capacity"),
                rs.getInt("base_price"),
                Type.valueOf(rs.getString("type"))
        );
        room.setId(rs.getLong("id"));
        room.setStatus(Status.valueOf(rs.getString("status")));
        return room;
    }

}

package reservation;

public enum RoomType {
    NORMAL("room"),
    SPECIAL("special_room");

    private final String tableName;

    RoomType(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}

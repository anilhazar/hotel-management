package entity.room.enums;

public enum Type {
    NORMAL("room"),
    SPECIAL("special_room");

    private final String tableName;

    Type(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}

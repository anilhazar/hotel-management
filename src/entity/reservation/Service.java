package entity.reservation;

public enum Service {

    ROOM_SERVICE("Room Service", 50.0),
    SPA("Spa", 100.0),
    LAUNDRY("Laundry", 30.0),
    CONCIERGE("Concierge Services", 20.0),
    FITNESS_CENTER("Fitness Center", 40.0);

    private final String name;
    private final double price;

    Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}

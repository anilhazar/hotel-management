package entity.invoice;

public class Invoice {
    private Long id;
    private Long reservationId;
    private double totalPrice;

    public Invoice(Long reservationId, double totalPrice) {
        this.reservationId = reservationId;
        this.totalPrice = totalPrice;
    }

    public Invoice(Long id, Long reservationId, Double totalPrice) {
        this.id = id;
        this.reservationId = reservationId;
        this.totalPrice = totalPrice;
    }

    public Invoice(Long reservationId) {
        this.reservationId = reservationId;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

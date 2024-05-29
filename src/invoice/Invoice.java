package invoice;

public class Invoice {
    private Long id;
    private Long reservationId;
    private double totalPrice;

    public Invoice(Long reservationId, double totalPrice) {
        this.reservationId = reservationId;
        this.totalPrice = totalPrice;
    }
}

package entity.invoice;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Double.compare(getTotalPrice(), invoice.getTotalPrice()) == 0 && Objects.equals(getId(), invoice.getId()) && Objects.equals(getReservationId(), invoice.getReservationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getReservationId(), getTotalPrice());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Invoice{");
        sb.append("id=").append(id);
        sb.append(", reservationId=").append(reservationId);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append('}');
        return sb.toString();
    }
}

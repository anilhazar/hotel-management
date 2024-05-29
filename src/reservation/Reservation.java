package reservation;

import java.util.Date;

public class Reservation {
    private Long id;
    private Long customerId;
    private Long roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private double extendedPrice;

    public Reservation(long id, long customerId, long roomId, Date checkInDate, Date checkOutDate, double extendedPrice) {
        this.id = id;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.extendedPrice = extendedPrice;
    }

}

package service;

import entity.reservation.Reservation;
import entity.reservation.Service;

public interface ManagerService extends RoomService, ReservationService, InvoiceService {
    Double calculateExtendedPrice(Reservation reservation);

    Long calculateDurationInDays(Reservation reservation);

    void handleServiceRequest(Long reservationId, Service service);
}


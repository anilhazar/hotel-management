package service;

import entity.reservation.Reservation;

public interface ManagerService extends RoomService, ReservationService, InvoiceService {
    Double calculateExtendedPrice(Reservation reservation);

    Long calculateDurationInDays(Reservation reservation);
}


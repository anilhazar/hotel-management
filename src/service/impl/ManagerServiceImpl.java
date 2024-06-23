package service.impl;

import entity.invoice.Invoice;
import entity.reservation.Reservation;
import entity.reservation.Service;
import entity.room.Room;
import entity.room.SpecialRoom;
import entity.room.enums.SpecialFeature;
import repository.InvoiceRepository;
import repository.ReservationRepository;
import repository.RoomRepository;
import service.ManagerService;

import java.util.List;
import java.util.Optional;

public class ManagerServiceImpl implements ManagerService {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final InvoiceRepository invoiceRepository;

    public ManagerServiceImpl(RoomRepository roomRepository, ReservationRepository reservationRepository, InvoiceRepository invoiceRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void createRoom(Room room) {
        if (room == null) throw new IllegalArgumentException("Room cannot be null");
        try {
            roomRepository.save(room);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create room", e);
        }
    }

    @Override
    public Room findRoomById(Long roomId) {
        try {
            return roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Failed to find Room"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to find room", e);
        }
    }

    @Override
    public List<Room> findAllRooms() {
        try {
            return roomRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to find all rooms", e);
        }
    }

    @Override
    public void updateRoom(Room room) {
        if (room == null) throw new IllegalArgumentException("Room cannot be null");

        roomRepository.update(room);
    }

    @Override
    public void createReservation(Reservation reservation) {
        if (reservation == null) throw new IllegalArgumentException("Reservation cannot be null");

        reservationRepository.save(reservation);

    }

    @Override
    public Reservation findReservationById(Long reservationId) {
        try {
            return reservationRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("Failed to find Reservation"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to find reservation", e);
        }
    }

    @Override
    public List<Reservation> findAllReservations() {
        try {
            return reservationRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to find all reservations", e);
        }
    }

    @Override
    public void updateReservation(Reservation reservation) {
        if (reservation == null) throw new IllegalArgumentException("Reservation cannot be null");
        try {
            reservationRepository.update(reservation);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update reservation", e);
        }
    }

    @Override
    public Double calculateTotalPrice(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        return calculateExtendedPrice(reservation) * calculateDurationInDays(reservation);
    }

    @Override
    public Double calculateExtendedPrice(Reservation reservation) {
        Room room = roomRepository.findById(reservation.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        double roomPrice = room.getBasePrice();
        double servicePrice = reservation.getServices().stream()
                .mapToDouble(Service::getPrice)
                .sum();

        if (room instanceof SpecialRoom specialRoom) {
            double specialFeaturePrice = specialRoom.getSpecialFeatures().stream()
                    .mapToDouble(SpecialFeature::getPrice)
                    .sum();
            return roomPrice + servicePrice + specialFeaturePrice;
        }

        return roomPrice + servicePrice;
    }

    @Override
    public Long calculateDurationInDays(Reservation reservation) {
        return (reservation.getCheckOutDate().getTime() - reservation.getCheckInDate().getTime()) / (1000 * 60 * 60 * 24);
    }

    @Override
    public void createInvoice(Invoice invoice) {
        if (invoice == null) throw new IllegalArgumentException("Invoice cannot be null");
        try {
            invoiceRepository.save(invoice);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create invoice", e);
        }
    }

    @Override
    public Optional<Invoice> findInvoiceByReservationId(Long reservationId) {
        try {
            return invoiceRepository.findByReservationId(reservationId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find invoice", e);
        }
    }

    @Override
    public List<Invoice> findAllInvoices() {
        try {
            return invoiceRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to find all invoices", e);
        }
    }
}

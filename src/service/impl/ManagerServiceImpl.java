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
    private final SpecialRoomRepository specialRoomRepository;

    public ManagerServiceImpl() {
        this.roomRepository = new RoomRepositoryImpl();
        this.reservationRepository = new ReservationRepositoryImpl();
        this.invoiceRepository = new InvoiceRepositoryImpl();
        this.specialRoomRepository = new SpecialRoomRepositoryImpl();
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
    public void createSpecialRoom(SpecialRoom specialRoom) {
        if (specialRoom == null) throw new IllegalArgumentException("Special Room cannot be null");
        try {
            specialRoomRepository.save(specialRoom);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create special room", e);
        }
    }

    @Override
    public SpecialRoom findSpecialRoomById(Long specialRoomId) {
        return specialRoomRepository.findById(specialRoomId).orElseThrow(() -> new RuntimeException("Failed to find Special Room"));

    }

    @Override
    public List<SpecialRoom> findAllSpecialRooms() {

        List<SpecialRoom> specialRooms = specialRoomRepository.findAll();
        if (specialRooms == null) {
            throw new RuntimeException("No rooms found");
        }
        return specialRooms;

    }

    @Override
    public void updateSpecialRoom(SpecialRoom specialRoom) {
        if (specialRoom == null) throw new IllegalArgumentException("Special Room cannot be null");

        specialRoomRepository.update(specialRoom);
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
    public void handleServiceRequest(Long reservationId, Service service) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        List<Service> services = reservation.getServices();

        if (services.size() >= 3) {
            System.out.println("Cannot add service. The room already has 3 or more services.");
        } else {
            services.add(service);
            reservationRepository.update(reservation);
            System.out.println("Service added successfully.");
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
        double servicePrice = 0.0;
        for (Service service : reservation.getServices()) {
            servicePrice += service.getPrice();
        }

        if (room instanceof SpecialRoom) {
            SpecialRoom specialRoom = (SpecialRoom) room;
            double specialFeaturePrice = 0.0;
            for (SpecialFeature feature : specialRoom.getSpecialFeatures()) {
                specialFeaturePrice += feature.getPrice();
            }
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

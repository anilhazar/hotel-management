package service.impl;

import entity.reservation.Reservation;
import entity.reservation.Service;
import entity.room.Room;
import entity.room.SpecialFeature;
import entity.room.SpecialRoom;
import repository.ReservationRepository;
import repository.RoomRepository;
import repository.impl.ReservationRepositoryImpl;
import repository.impl.RoomRepositoryImpl;
import service.CustomerService;
import service.ManagerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final ManagerService managerService;

    public CustomerServiceImpl() {
        this.reservationRepository = new ReservationRepositoryImpl();
        this.roomRepository = new RoomRepositoryImpl();
        this.managerService = new ManagerServiceImpl();
    }

    @Override
    public void createReservation(Reservation reservation) {
        try {
            reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create reservation", e);
        }
    }

    @Override
    public void addServices(Long reservationId, List<Service> services) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.getServices().addAll(services);
        reservation.setExtendedPrice(calculateExtendedPrice(reservation));

        try {
            reservationRepository.update(reservation);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add services to reservation", e);
        }
    }

    @Override
    public void addSpecialFeatures(Long reservationId, List<SpecialFeature> specialFeatures) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Room room = roomRepository.findById(reservation.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (room instanceof SpecialRoom) {
            SpecialRoom specialRoom = (SpecialRoom) room;
            specialRoom.getSpecialFeatures().addAll(specialFeatures);
            roomRepository.update(specialRoom);
            reservation.setExtendedPrice(calculateExtendedPrice(reservation));

            try {
                reservationRepository.update(reservation);
            } catch (Exception e) {
                throw new RuntimeException("Failed to add special features to reservation", e);
            }
        } else {
            throw new RuntimeException("Room is not a special room");
        }
    }

    @Override
    public void requestService(Long reservationId, Service service) {
        managerService.handleServiceRequest(reservationId, service);
    }

    private double calculateExtendedPrice(Reservation reservation) {
        double basePrice = reservation.getExtendedPrice();
        double additionalPrice = 0.0;

        for (Service service : reservation.getServices()) {
            additionalPrice += service.getPrice();
        }

        return basePrice + additionalPrice;
    }

}


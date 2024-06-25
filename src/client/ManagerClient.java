package client;

import entity.invoice.Invoice;
import entity.reservation.Reservation;
import entity.reservation.Service;
import entity.room.Room;
import entity.room.SpecialFeature;
import entity.room.SpecialRoom;
import service.ManagerService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerClient {
    private final Scanner scanner;
    private final ManagerService managerService;

    public ManagerClient(Scanner scanner, ManagerService managerService) {
        this.scanner = scanner;
        this.managerService = managerService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("Manager Menu:");
            System.out.println("1. Create Room");
            System.out.println("2. Create Special Room");
            System.out.println("3. View Room");
            System.out.println("4. View All Rooms");
            System.out.println("5. Update Room");
            System.out.println("6. Create Reservation");
            System.out.println("7. View Reservation");
            System.out.println("8. View All Reservations");
            System.out.println("9. Update Reservation");
            System.out.println("10. Calculate Total Price");
            System.out.println("11. Create Invoice");
            System.out.println("12. View Invoice by Reservation ID");
            System.out.println("13. View All Invoices");
            System.out.println("14. Handle Service Request");
            System.out.println("15. View Special Room");
            System.out.println("16. View All Special Rooms");
            System.out.println("17. Update Special Room");
            System.out.println("18. Add Special Feature to Room");
            System.out.println("19. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createRoom();
                    break;
                case 2:
                    createSpecialRoom();
                    break;
                case 3:
                    viewRoom();
                    break;
                case 4:
                    viewAllRooms();
                    break;
                case 5:
                    updateRoom();
                    break;
                case 6:
                    createReservation();
                    break;
                case 7:
                    viewReservation();
                    break;
                case 8:
                    viewAllReservations();
                    break;
                case 9:
                    updateReservation();
                    break;
                case 10:
                    calculateTotalPrice();
                    break;
                case 11:
                    createInvoice();
                    break;
                case 12:
                    viewInvoiceByReservationId();
                    break;
                case 13:
                    viewAllInvoices();
                    break;
                case 14:
                    handleServiceRequest();
                    break;
                case 15:
                    viewSpecialRoom();
                    break;
                case 16:
                    viewAllSpecialRooms();
                    break;
                case 17:
                    updateSpecialRoom();
                    break;
                case 18:
                    addSpecialFeatureToRoom();
                    break;
                case 19:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createRoom() {
        System.out.print("Enter room capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        Room room = new Room(capacity);
        managerService.createRoom(room);
        System.out.println("Room created successfully.");
    }

    private void createSpecialRoom() {
        System.out.print("Enter room capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        List<SpecialFeature> specialFeatures = selectSpecialFeatures();
        SpecialRoom specialRoom = new SpecialRoom(capacity, specialFeatures);
        managerService.createSpecialRoom(specialRoom);
        System.out.println("Special room created successfully.");
    }

    private List<SpecialFeature> selectSpecialFeatures() {
        List<SpecialFeature> selectedFeatures = new ArrayList<>();
        while (true) {
            System.out.print("Enter feature name (enter 'done' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                break;
            }
            System.out.print("Enter feature price: ");
            int price = scanner.nextInt();
            scanner.nextLine();

            selectedFeatures.add(new SpecialFeature(name, price));
        }
        return selectedFeatures;
    }

    private void viewRoom() {
        System.out.print("Enter room ID: ");
        long roomId = scanner.nextLong();
        scanner.nextLine();

        Room room = managerService.findRoomById(roomId);
        if (room != null) {
            System.out.println(room);
        } else {
            System.out.println("Room not found.");
        }
    }

    private void viewAllRooms() {
        managerService.findAllRooms().forEach(System.out::println);
    }

    private void updateRoom() {
        System.out.print("Enter room ID: ");
        long roomId = scanner.nextLong();
        scanner.nextLine();

        Room room = managerService.findRoomById(roomId);
        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.print("Enter new capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        room.setCapacity(capacity);
        managerService.updateRoom(room);
        System.out.println("Room updated successfully.");
    }

    private void createReservation() {
        System.out.print("Enter room ID: ");
        long roomId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        String checkInDate = scanner.nextLine();
        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        String checkOutDate = scanner.nextLine();

        Reservation reservation = new Reservation(roomId, checkInDate, checkOutDate);
        managerService.createReservation(reservation);
        System.out.println("Reservation created successfully.");
    }

    private void viewReservation() {
        System.out.print("Enter reservation ID: ");
        long reservationId = scanner.nextLong();
        scanner.nextLine();

        Reservation reservation = managerService.findReservationById(reservationId);
        if (reservation != null) {
            System.out.println(reservation);
        } else {
            System.out.println("Reservation not found.");
        }
    }

    private void viewAllReservations() {
        managerService.findAllReservations().forEach(System.out::println);
    }

    private void updateReservation() {
        System.out.print("Enter reservation ID: ");
        long reservationId = scanner.nextLong();
        scanner.nextLine();

        Reservation reservation = managerService.findReservationById(reservationId);
        if (reservation == null) {
            System.out.println("Reservation not found.");
            return;
        }

        System.out.print("Enter new check-in date (yyyy-mm-dd): ");
        String checkInDate = scanner.nextLine();
        System.out.print("Enter new check-out date (yyyy-mm-dd): ");
        String checkOutDate = scanner.nextLine();

        reservation.setCheckInDate(Date.valueOf(checkInDate));
        reservation.setCheckOutDate(Date.valueOf(checkOutDate));
        managerService.updateReservation(reservation);
        System.out.println("Reservation updated successfully.");
    }

    private void calculateTotalPrice() {
        System.out.print("Enter reservation ID: ");
        long reservationId = scanner.nextLong();
        scanner.nextLine();

        double totalPrice = managerService.calculateTotalPrice(reservationId);
        System.out.println("Total price: " + totalPrice);
    }

    private void createInvoice() {
        System.out.print("Enter reservation ID: ");
        long reservationId = scanner.nextLong();
        scanner.nextLine();

        double totalPrice = managerService.calculateTotalPrice(reservationId);
        Invoice invoice = new Invoice(reservationId, totalPrice);
        managerService.createInvoice(invoice);
        System.out.println("Invoice created successfully.");
    }

    private void viewInvoiceByReservationId() {
        System.out.print("Enter reservation ID: ");
        long reservationId = scanner.nextLong();
        scanner.nextLine();

        Invoice invoice = managerService.findInvoiceByReservationId(reservationId);
        if (invoice != null) {
            System.out.println(invoice);
        } else {
            System.out.println("Invoice not found.");
        }
    }

    private void viewAllInvoices() {
        managerService.findAllInvoices().forEach(System.out::println);
    }

    private void handleServiceRequest() {
        System.out.print("Enter reservation ID: ");
        long reservationId = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Enter service name: ");
        String serviceName = scanner.nextLine();
        System.out.print("Enter service price: ");
        double servicePrice = scanner.nextDouble();
        scanner.nextLine();

        Service service = new Service(serviceName, servicePrice);
        managerService.handleServiceRequest(reservationId, service);
    }

    private void viewSpecialRoom() {
        System.out.print("Enter special room ID: ");
        long specialRoomId = scanner.nextLong();
        scanner.nextLine();

        SpecialRoom specialRoom = managerService.findSpecialRoomById(specialRoomId);
        if (specialRoom != null) {
            System.out.println(specialRoom);
        } else {
            System.out.println("Special room not found.");
        }
    }

    private void viewAllSpecialRooms() {
        managerService.findAllSpecialRooms().forEach(System.out::println);
    }

    private void updateSpecialRoom() {
        System.out.print("Enter special room ID: ");
        long specialRoomId = scanner.nextLong();
        scanner.nextLine();

        SpecialRoom specialRoom = managerService.findSpecialRoomById(specialRoomId);
        if (specialRoom == null) {
            System.out.println("Special room not found.");
            return;
        }

        System.out.print("Enter new capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        specialRoom.setCapacity(capacity);
        managerService.updateSpecialRoom(specialRoom);
        System.out.println("Special room updated successfully.");
    }

    private void addSpecialFeatureToRoom() {
        System.out.print("Enter special room ID: ");
        long specialRoomId = scanner.nextLong();
        scanner.nextLine();

        SpecialRoom specialRoom = managerService.findSpecialRoomById(specialRoomId);
        if (specialRoom == null) {
            System.out.println("Special room not found.");
            return;
        }

        List<SpecialFeature> currentFeatures = specialRoom.getSpecialFeatures();
        List<SpecialFeature> selectedFeatures = selectSpecialFeatures();

        currentFeatures.addAll(selectedFeatures);
        specialRoom.setSpecialFeatures(currentFeatures);

        managerService.updateSpecialRoom(specialRoom);
        System.out.println("Special features added to room successfully.");
    }
}

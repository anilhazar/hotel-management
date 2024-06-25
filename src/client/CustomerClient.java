package client;

import entity.reservation.Reservation;
import entity.reservation.Service;
import entity.room.SpecialFeature;
import service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerClient {
    private final Scanner scanner;
    private final CustomerService customerService;

    public CustomerClient(Scanner scanner, CustomerService customerService) {
        this.scanner = scanner;
        this.customerService = customerService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("Customer Menu:");
            System.out.println("1. Create Reservation");
            System.out.println("2. Add Services to Reservation");
            System.out.println("3. Add Special Features to Reservation");
            System.out.println("4. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createReservation();
                    break;
                case 2:
                    addServices();
                    break;
                case 3:
                    addSpecialFeatures();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createReservation() {
        System.out.print("Enter room ID: ");
        Long roomId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        String checkInDate = scanner.nextLine();
        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        String checkOutDate = scanner.nextLine();

        Reservation reservation = new Reservation(roomId, checkInDate, checkOutDate);
        customerService.createReservation(reservation);
        System.out.println("Reservation created successfully.");
    }

    private void addServices() {
        System.out.print("Enter reservation ID: ");
        long reservationId = scanner.nextLong();
        scanner.nextLine();

        List<Service> services = new ArrayList<>();
        System.out.print("Enter the number of services: ");
        int numServices = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numServices; i++) {
            System.out.print("Enter service name: ");
            String serviceName = scanner.nextLine();
            System.out.print("Enter service price: ");
            double servicePrice = scanner.nextDouble();
            scanner.nextLine();
            services.add(new Service(serviceName, servicePrice));
        }

        customerService.addServices(reservationId, services);
        System.out.println("Services added successfully.");
    }

    private void addSpecialFeatures() {
        System.out.print("Enter reservation ID: ");
        long reservationId = scanner.nextLong();
        scanner.nextLine();

        List<SpecialFeature> specialFeatures = new ArrayList<>();
        System.out.print("Enter the number of special features: ");
        int numFeatures = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numFeatures; i++) {
            System.out.print("Enter special feature name: ");
            String featureName = scanner.nextLine();
            System.out.print("Enter special feature price: ");
            int featurePrice = scanner.nextInt();

            SpecialFeature specialFeature = new SpecialFeature(featureName, featurePrice);
            specialFeatures.add(specialFeature);
        }

        customerService.addSpecialFeatures(reservationId, specialFeatures);
        System.out.println("Special features added successfully.");
    }

    private void requestService() {
        System.out.print("Enter reservation ID: ");
        long reservationId = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Enter service name: ");
        String serviceName = scanner.nextLine();
        System.out.print("Enter service price: ");
        double servicePrice = scanner.nextDouble();
        scanner.nextLine();

        Service service = new Service(serviceName, servicePrice);
        customerService.requestService(reservationId, service);
    }

}

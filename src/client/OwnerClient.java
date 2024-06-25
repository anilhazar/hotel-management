package client;

import entity.invoice.Invoice;
import entity.person.Person;
import service.OwnerService;

import java.util.List;
import java.util.Scanner;

public class OwnerClient {
    private final Scanner scanner;
    private final OwnerService ownerService;

    public OwnerClient(Scanner scanner, OwnerService ownerService) {
        this.scanner = scanner;
        this.ownerService = ownerService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("Owner Menu:");
            System.out.println("1. List All Reservations");
            System.out.println("2. List All Customers");
            System.out.println("3. List All Invoices");
            System.out.println("4. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listAllReservations();
                    break;
                case 2:
                    listAllCustomers();
                    break;
                case 3:
                    listAllInvoices();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void listAllReservations() {
        var reservations = ownerService.listAllReservations();
        reservations.forEach(System.out::println);
    }

    private void listAllCustomers() {
        List<Person> customers = ownerService.listAllCustomers();
        customers.forEach(System.out::println);
    }

    private void listAllInvoices() {
        List<Invoice> invoices = ownerService.listAllInvoices();
        invoices.forEach(System.out::println);
    }
}

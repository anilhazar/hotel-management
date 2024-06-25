import client.CustomerClient;
import client.ManagerClient;
import client.OwnerClient;
import entity.person.Person;
import entity.person.Role;
import service.AuthenticationService;
import service.CustomerService;
import service.ManagerService;
import service.OwnerService;
import service.impl.AuthenticationServiceImpl;
import service.impl.CustomerServiceImpl;
import service.impl.ManagerServiceImpl;
import service.impl.OwnerServiceImpl;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AuthenticationService authenticationService = new AuthenticationServiceImpl();
    private static final CustomerService customerService = new CustomerServiceImpl();
    private static final ManagerService managerService = new ManagerServiceImpl();
    private static final OwnerService ownerService = new OwnerServiceImpl();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome! Please select an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void register() {
        System.out.println("Enter your name");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.println("Enter your age: ");
        int age = scanner.nextInt();
        System.out.println("Select your role:");
        System.out.println("1. Customer");
        System.out.println("2. Manager");
        System.out.println("3. Owner");
        int roleChoice = scanner.nextInt();
        scanner.nextLine();
        Role role;
        switch (roleChoice) {
            case 1:
                role = Role.CUSTOMER;
                break;
            case 2:
                role = Role.MANAGER;
                break;
            case 3:
                role = Role.OWNER;
                break;
            default:
                System.out.println("Invalid role. Registration failed.");
                return;
        }
        Person user = new Person(name, password, email, age, role);
        authenticationService.register(user);
        System.out.println("Registration successful.");
    }

    private static void login() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Person user = authenticationService.login(email, password);
        if (user == null) {
            System.out.println("Invalid email or password.");
            return;
        }

        System.out.println("Login successful.");
        switch (user.getRole()) {
            case CUSTOMER:
                CustomerClient customerClient = new CustomerClient(scanner, customerService);
                customerClient.showMenu();
                break;
            case MANAGER:
                ManagerClient managerClient = new ManagerClient(scanner, managerService);
                managerClient.showMenu();
                break;
            case OWNER:
                OwnerClient ownerClient = new OwnerClient(scanner, ownerService);
                ownerClient.showMenu();
                break;
            default:
                System.out.println("Invalid role.");
        }
    }
}

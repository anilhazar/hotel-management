package service;

import common.Person;
import entity.customer.Customer;
import entity.invoice.Invoice;
import entity.reservation.Reservation;

import java.util.List;

public interface OwnerService {
    List<Reservation> listAllReservations();
    List<Person> listAllCustomers();
    List<Invoice> listAllInvoices();
}

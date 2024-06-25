package repository.impl;

import database.ConnectionManager;
import entity.invoice.Invoice;
import repository.InvoiceRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvoiceRepositoryImpl implements InvoiceRepository {

    public InvoiceRepositoryImpl() {
    }

    @Override
    public void save(Invoice invoice) {
        String query = "INSERT INTO invoice (reservation_id, total_price) VALUES (?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, invoice.getReservationId());
            pstmt.setDouble(2, invoice.getTotalPrice());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                invoice.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Invoice invoice) {
        String query = "UPDATE invoice SET reservation_id = ?, total_price = ? WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, invoice.getReservationId());
            pstmt.setDouble(2, invoice.getTotalPrice());
            pstmt.setLong(3, invoice.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        String query = "SELECT * FROM invoice WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Invoice(
                        rs.getLong("reservation_id"),
                        rs.getDouble("total_price")
                ) {{
                    setId(rs.getLong("id"));
                }});
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }


    @Override
    public Optional<Invoice> findByReservationId(Long reservationId) {
        String query = "SELECT * FROM invoices WHERE reservation_id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, reservationId);
            ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Invoice invoice = extractInvoice(resultSet);
                    return Optional.of(invoice);
                }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find invoice by reservation id", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Invoice> findAll() {
        List<Invoice> invoices = new ArrayList<>();
        String query = "SELECT * FROM invoice";
        try (Connection connection = ConnectionManager.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                invoices.add(new Invoice(
                        rs.getLong("reservation_id"),
                        rs.getDouble("total_price")
                ) {{
                    setId(rs.getLong("id"));
                }});
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return invoices;
    }

    private Invoice extractInvoice(ResultSet rs) {
        try {
            return new Invoice(
                    rs.getLong("id"),
                    rs.getLong("reservation_id"),
                    rs.getDouble("total_price")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Failed to extract invoice from ResultSet", e);
        }
    }

}

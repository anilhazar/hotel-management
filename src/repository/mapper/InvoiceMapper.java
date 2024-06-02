package repository.mapper;

import invoice.Invoice;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceMapper {
    public static Invoice map(ResultSet rs) throws SQLException {
        Invoice invoice = new Invoice(
                rs.getLong("reservation_id"),
                rs.getDouble("total_price")
        );
        invoice.setId(rs.getLong("id"));
        return invoice;
    }
}

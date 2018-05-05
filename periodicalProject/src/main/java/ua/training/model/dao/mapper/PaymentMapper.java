package ua.training.model.dao.mapper;

import ua.training.model.entities.Payment;
import ua.training.util.constant.TableColumns;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper implements GeneralMapper<Payment> {
    @Override
    public Payment getFromResultSet(ResultSet rs) throws SQLException {
        return new Payment.PaymentBuilder()
                .buildId(rs.getInt(TableColumns.PAYMENT_ID))
                .buildIdUser(rs.getInt(TableColumns.PAYMENT_USER))
                .buildPrice(rs.getInt(TableColumns.PAYMENT_PRICE))
                .buildLazy();
    }
}

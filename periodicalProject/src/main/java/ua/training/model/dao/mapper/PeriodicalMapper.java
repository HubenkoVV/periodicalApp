package ua.training.model.dao.mapper;

import ua.training.model.entities.Periodical;
import ua.training.model.entities.lazyload.LazyPeriodical;
import ua.training.util.constant.TableColumns;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeriodicalMapper implements GeneralMapper<Periodical> {
    @Override
    public Periodical getFromResultSet(ResultSet rs) throws SQLException {
        return new LazyPeriodical.PeriodicalBuilder()
                .buildId(rs.getInt(TableColumns.PERIODICAL_ID))
                .buildName(rs.getString(TableColumns.PERIODICAL_NAME))
                .buildPrice(rs.getInt(TableColumns.PERIODICAL_PRICE))
                .buildShortDescription(rs.getString(TableColumns.PERIODICAL_DESCRIPTION))
                .build();
    }
}

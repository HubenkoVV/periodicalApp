package ua.training.model.entities.lazyload;

import ua.training.dao.PeriodicalDao;
import ua.training.dao.factory.AbstractDaoFactory;
import ua.training.model.entities.Payment;
import ua.training.model.entities.Periodical;
import ua.training.model.entities.User;

import java.util.List;

public class LazyUser extends User {
    @Override
    public List<Payment> getPayments() {
        return super.getPayments();
    }

    @Override
    public List<Periodical> getPeriodicals() {
        if (super.getPeriodicals() == null) {
            AbstractDaoFactory daoFactory = AbstractDaoFactory.getInstance();
            PeriodicalDao periodicalDao = daoFactory.createPeriodicalDao();
            return periodicalDao.findByUser(getId());
        }
        return super.getPeriodicals();
    }
}

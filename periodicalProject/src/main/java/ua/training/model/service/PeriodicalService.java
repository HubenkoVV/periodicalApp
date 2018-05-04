package ua.training.model.service;

import ua.training.model.dao.PeriodicalDao;
import ua.training.model.dao.factory.AbstractDaoFactory;
import ua.training.model.dao.factory.DaoFactory;
import ua.training.model.entities.Periodical;
import ua.training.model.service.exception.IncorrectDataException;
import ua.training.util.constant.Exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeriodicalService {
    private AbstractDaoFactory daoFactory = AbstractDaoFactory.getInstance();

    public Periodical createPeriodical(Periodical periodical) throws IncorrectDataException {
        PeriodicalDao periodicalDao = daoFactory.createPeriodicalDao();
        try{
            periodicalDao.setAutoCommit(false);
            int id = periodicalDao.create(periodical);
            if (id == 0) {
                throw new SQLIntegrityConstraintViolationException();
            }
            periodical.setId(id);
        } catch (SQLIntegrityConstraintViolationException e) {
            periodicalDao.rollback();
            throw new IncorrectDataException(Exceptions.INCORRECT_DATA);
        } finally {
            periodicalDao.close();
        }
        return periodical;
    }

    public Periodical getById(int id) {
        try(PeriodicalDao periodicalDao = daoFactory.createPeriodicalDao()) {
            return periodicalDao.findById(id);
        }
    }

    public List<Periodical> getAll() {
        try(PeriodicalDao periodicalDao = daoFactory.createPeriodicalDao()) {
            return periodicalDao.findAll();
        }
    }

    public Map<Integer, List<Periodical>> getPeriodicalsOnPages(int periodicalsOnPage) {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            Map<Integer, List<Periodical>> result = new HashMap<>();
            List<Periodical> pageOfPeriodicals = periodicalDao.findFixedNumberOfPeriodicals(periodicalsOnPage, 0);

            for (int pageNumber = 1; !pageOfPeriodicals.isEmpty(); pageNumber++) {
                result.put(pageNumber, pageOfPeriodicals);
                pageOfPeriodicals = periodicalDao.findFixedNumberOfPeriodicals(periodicalsOnPage, periodicalsOnPage*pageNumber);
            }
            periodicalDao.close();
            return result;
        }
    }

    public Map<Integer, List<Periodical>> searchPeriodicals(String name, int periodicalsOnPage) {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            Map<Integer, List<Periodical>> result = new HashMap<>();
            List<Periodical> pageOfPeriodicals = periodicalDao.searchPeriodicals(name, periodicalsOnPage, 0);

            for (int pageNumber = 1; !pageOfPeriodicals.isEmpty(); pageNumber++) {
                result.put(pageNumber, pageOfPeriodicals);
                pageOfPeriodicals = periodicalDao.searchPeriodicals(name, periodicalsOnPage, periodicalsOnPage*pageNumber);
            }
            periodicalDao.close();
            return result;
        }
    }


}

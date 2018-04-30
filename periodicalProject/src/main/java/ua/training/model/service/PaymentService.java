package ua.training.model.service;

import ua.training.model.dao.PaymentDao;
import ua.training.model.dao.factory.AbstractDaoFactory;
import ua.training.model.entities.Payment;
import ua.training.model.entities.User;
import ua.training.model.service.exception.IncorrectDataException;
import ua.training.model.service.exception.NotEnoughMoneyException;
import ua.training.util.constant.Exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class PaymentService {
    private AbstractDaoFactory daoFactory = AbstractDaoFactory.getInstance();

    public Payment createPayment(Payment payment, User user) throws IncorrectDataException, NotEnoughMoneyException {

        checkUserBalance(payment.getPrice(), user.getMoney());

        PaymentDao paymentDao = daoFactory.createPaymentDao();
        try{
            paymentDao.setAutoCommit(false);
            int id = paymentDao.create(payment);
            if (id == 0) {
                throw new SQLIntegrityConstraintViolationException();
            }
            payment.setId(id);
        } catch (SQLIntegrityConstraintViolationException e) {
            paymentDao.rollback();
            throw new IncorrectDataException(Exceptions.NOT_ENOUGH_MONEY);
        }
        return payment;
    }

    public Payment getById(int id) {
        try(PaymentDao paymentDao = daoFactory.createPaymentDao()) {
            return paymentDao.findById(id);
        }
    }

    public List<Payment> getAll() {
        try(PaymentDao paymentDao = daoFactory.createPaymentDao()) {
            return paymentDao.findAll();
        }
    }

    private void checkUserBalance(int price, int balance) throws NotEnoughMoneyException {
        if(price > balance)
            throw new NotEnoughMoneyException(Exceptions.NOT_ENOUGH_MONEY);
    }
}

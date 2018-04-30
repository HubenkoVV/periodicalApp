package ua.training.model.service;

import ua.training.model.dao.UserDao;
import ua.training.model.dao.factory.AbstractDaoFactory;
import ua.training.model.entities.User;
import ua.training.model.service.exception.IncorrectDataException;
import ua.training.util.constant.Exceptions;
import ua.training.util.constant.RegexForUser;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private AbstractDaoFactory daoFactory = AbstractDaoFactory.getInstance();

    public User createUser(User user, String password, String repeatPassword) throws IncorrectDataException {
        checkRegistrationData(user, password, repeatPassword);
        UserDao userDao = daoFactory.createUserDao();
        try{
            userDao.setAutoCommit(false);
            int id = userDao.create(user);
            if (id == 0) {
                throw new SQLIntegrityConstraintViolationException();
            }
            user.setId(id);
        } catch (SQLIntegrityConstraintViolationException e) {
            userDao.rollback();
            throw new IncorrectDataException(Exceptions.NOT_UNIQUE_LOGIN);
        }
        return user;
    }

    public User getByLoginAndPassword(String login, String password) throws IncorrectDataException {
        try(UserDao userDao = daoFactory.createUserDao()) {
            userDao.setAutoCommit(false);
            User user = userDao.findByLogin(login);
            if (user == null) {
                throw new IncorrectDataException(Exceptions.WRONG_LOGIN);
            }
            checkPassword(password, user.getPassword());
            return user;
        }
    }

    public User updateAccount(User user, String money) throws IncorrectDataException {
        try(UserDao userDao = daoFactory.createUserDao()) {
            if(!isDataCorrect(money, RegexForUser.MONEY))
                throw new IncorrectDataException(Exceptions.WRONG_MONEY);
            user.setMoney(Integer.parseInt(money));
            userDao.setAutoCommit(false);
            userDao.updateMoney(user);
            return user;
        }
    }

    public User getById(int id) {
        try(UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findById(id);
        }
    }

    public List<User> getAll() {
        try(UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        }
    }

    private void checkRegistrationData(User user, String password, String repeatPassword) throws IncorrectDataException {
        if (!isDataCorrect(user.getLogin(), RegexForUser.LOGIN)) {
            throw new IncorrectDataException(Exceptions.INCORRECT_LOGIN);
        }
        if (!isDataCorrect(password, RegexForUser.PASSWORD)) {
            throw new IncorrectDataException(Exceptions.INCORRECT_PASSWORD);
        }
        if (!user.getPhone().equals("") && !isDataCorrect(user.getPhone(),RegexForUser.PHONE)) {
            throw new IncorrectDataException(Exceptions.INCORRECT_PHONE);
        }
        if(!password.equals(repeatPassword)){
            throw new IncorrectDataException(Exceptions.WRONG_PASSWORD);
        }
    }

    private void checkPassword(String password, int userPass) throws IncorrectDataException {
        if(password.hashCode() != userPass){
            throw new IncorrectDataException(Exceptions.WRONG_PASSWORD);
        }
    }

    private boolean isDataCorrect(String login, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(login);
        return m.matches();
    }

}

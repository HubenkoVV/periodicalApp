package ua.training.model.entities;

import java.util.List;

public class User {
    private int id;
    private String name;
    private String surname;
    private String login;
    private int password;
    private String phone;
    private UserRole role;
    private int money;

    private List<Payment> payments;
    private List<Periodical> periodicals;

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    void setLogin(String login) {
        this.login = login;
    }

    public int getPassword() {
        return password;
    }

    void setPassword(int password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRole getRole() {
        return role;
    }

    void setRole(UserRole role) {
        this.role = role;
    }

    public int getMoney() {
        return money;
    }

    void setMoney(int money) {
        this.money = money;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Periodical> getPeriodicals() {
        return periodicals;
    }

    void setPeriodicals(List<Periodical> periodicals) {
        this.periodicals = periodicals;
    }

    public static final class UserBuilder{
        private int id;
        private String name;
        private String surname;
        private String login;
        private int password;
        private String phone;
        private UserRole role;
        private int money;
        private List<Payment> payments;
        private List<Periodical> periodicals;

        public UserBuilder buildId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder buildName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder buildSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder buildLogin(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder buildPassword(int password) {
            this.password = password;
            return this;
        }

        public UserBuilder buildPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder buildPayments(List<Payment> payments) {
            this.payments = payments;
            return this;
        }

        public UserBuilder buildRole(UserRole role) {
            this.role = role;
            return this;
        }

        public UserBuilder buildMoney(int money) {
            this.money = money;
            return this;
        }

        public UserBuilder buildPeriodicals(List<Periodical> periodicals) {
            this.periodicals = periodicals;
            return this;
        }

        public User build(){
            User user = new User();
            user.setId(id);
            user.setLogin(login);
            user.setName(name);
            user.setPassword(password);
            user.setPhone(phone);
            user.setSurname(surname);
            user.setPayments(payments);
            user.setRole(role);
            user.setMoney(money);
            user.setPeriodicals(periodicals);
            return user;
        }
    }
}

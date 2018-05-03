package ua.training.model.entities;

import ua.training.model.entities.lazyload.LazyUser;

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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    protected void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    protected void setLogin(String login) {
        this.login = login;
    }

    public int getPassword() {
        return password;
    }

    protected void setPassword(int password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    protected void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRole getRole() {
        return role;
    }

    protected void setRole(UserRole role) {
        this.role = role;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public List<Periodical> getPeriodicals() {
        return periodicals;
    }

    void setPeriodicals(List<Periodical> periodicals) {
        this.periodicals = periodicals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id
                && password == user.password
                && money == user.money
                && name.equals(user.name)
                && surname.equals(user.surname)
                && login.equals(user.login)
                && (phone != null ? phone.equals(user.phone) : user.phone == null)
                && role == user.role
                && (payments != null ? payments.equals(user.payments) : user.payments == null)
                && (periodicals != null ? periodicals.equals(user.periodicals) : user.periodicals == null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + role.hashCode();
        result = 31 * result + money;
        result = 31 * result + (payments != null ? payments.hashCode() : 0);
        result = 31 * result + (periodicals != null ? periodicals.hashCode() : 0);
        return result;
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

        public UserBuilder buildRole(UserRole role) {
            this.role = role;
            return this;
        }

        public UserBuilder buildMoney(int money) {
            this.money = money;
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
            user.setRole(role);
            user.setMoney(money);
            return user;
        }

        public LazyUser buildLazy(){
            LazyUser user = new LazyUser();
            user.setId(id);
            user.setLogin(login);
            user.setName(name);
            user.setPassword(password);
            user.setPhone(phone);
            user.setSurname(surname);
            user.setRole(role);
            user.setMoney(money);
            return user;
        }
    }
}

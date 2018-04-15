package ua.training.model.entities;

import java.util.List;

public class Periodical {
    private int id;
    private String name;
    private String shortDescription;
    private int price;

    private List<User> users;
    private List<Payment> payments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public static final class PeriodicalBuilder{
        private int id;
        private String name;
        private String shortDescription;
        private int price;
        private List<User> users;
        private List<Payment> payments;

        public PeriodicalBuilder buildId(int id) {
            this.id = id;
            return this;
        }

        public PeriodicalBuilder buildName(String name) {
            this.name = name;
            return this;
        }

        public PeriodicalBuilder buildShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public PeriodicalBuilder buildPrice(int price) {
            this.price = price;
            return this;
        }

        public PeriodicalBuilder buildUsers(List<User> users) {
            this.users = users;
            return this;
        }

        public PeriodicalBuilder buildPayments(List<Payment> payments) {
            this.payments = payments;
            return this;
        }

        public Periodical build(){
            Periodical periodical = new Periodical();
            periodical.setId(id);
            periodical.setName(name);
            periodical.setPayments(payments);
            periodical.setPrice(price);
            periodical.setShortDescription(shortDescription);
            periodical.setUsers(users);
            return periodical;
        }
    }
}

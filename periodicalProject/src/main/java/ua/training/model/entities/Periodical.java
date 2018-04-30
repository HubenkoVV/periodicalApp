package ua.training.model.entities;

import java.util.List;

public class Periodical {
    private int id;
    private String name;
    private String shortDescription;
    private int price;

    private List<User> users;
    private List<Payment> payments;
    private List<Article> articles;

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

    void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getPrice() {
        return price;
    }

    void setPrice(int price) {
        this.price = price;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public static final class PeriodicalBuilder{
        private int id;
        private String name;
        private String shortDescription;
        private int price;

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

        public Periodical build(){
            Periodical periodical = new Periodical();
            periodical.setId(id);
            periodical.setName(name);
            periodical.setPrice(price);
            periodical.setShortDescription(shortDescription);
            return periodical;
        }
    }
}

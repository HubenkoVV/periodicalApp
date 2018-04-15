package ua.training.model.entities;

import java.util.List;

public class Payment {
    private int id;
    private int price;

    private int idUser;
    private List<Periodical> periodicals;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public List<Periodical> getPeriodicals() {
        return periodicals;
    }

    public void setPeriodicals(List<Periodical> periodicals) {
        this.periodicals = periodicals;
    }

    public static final class PaymentBuilder{
        private int id;
        private int price;
        private int idUser;
        private List<Periodical> periodicals;

        public PaymentBuilder buildId(int id) {
            this.id = id;
            return this;
        }

        public PaymentBuilder buildPrice(int price) {
            this.price = price;
            return this;
        }

        public PaymentBuilder buildIdUser(int idUser) {
            this.idUser = idUser;
            return this;
        }

        public PaymentBuilder buildPeriodicals(List<Periodical> periodicals) {
            this.periodicals = periodicals;
            return this;
        }

        public Payment build(){
            Payment payment = new Payment();
            payment.setId(id);
            payment.setIdUser(idUser);
            payment.setPrice(price);
            payment.setPeriodicals(periodicals);
            return payment;
        }
    }
}

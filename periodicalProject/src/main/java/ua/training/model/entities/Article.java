package ua.training.model.entities;

import java.time.LocalDate;
import java.util.Date;

public class Article {
    private int id;
    private String name;
    private String text;
    private int idPeriodical;
    private LocalDate date;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIdPeriodical() {
        return idPeriodical;
    }

    public void setIdPeriodical(int idPeriodical) {
        this.idPeriodical = idPeriodical;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static final class ArticleBuilder{
        private int id;
        private String name;
        private String text;
        private int idPeriodical;
        private LocalDate date;

        public ArticleBuilder buildId(int id) {
            this.id = id;
            return this;
        }

        public ArticleBuilder buildName(String name) {
            this.name = name;
            return this;
        }

        public ArticleBuilder buildText(String text) {
            this.text = text;
            return this;
        }

        public ArticleBuilder buildIdPeriodical(int idPeriodical) {
            this.idPeriodical = idPeriodical;
            return this;
        }

        public ArticleBuilder buildDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Article build(){
            Article article = new Article();
            article.setId(id);
            article.setName(name);
            article.setText(text);
            article.setIdPeriodical(idPeriodical);
            article.setDate(date);
            return article;
        }
    }
}

package ua.training.model.dao;

import ua.training.model.entities.Article;

import java.util.List;

public interface ArticleDao extends GenericDao<Article> {
    List<Article> findByPeriodical(int idPeriodical);
    List<Article> findFixedNumberOfArticles(int limit, int offset);
    List<Article> findByPeriodicalFixedNumberOfArticles(int id, int limit, int offset);
}

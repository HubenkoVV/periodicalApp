package ua.training.model.service;

import org.jetbrains.annotations.NotNull;
import ua.training.model.dao.ArticleDao;
import ua.training.model.dao.factory.AbstractDaoFactory;
import ua.training.model.dao.factory.DaoFactory;
import ua.training.model.entities.Article;
import ua.training.model.service.exception.IncorrectDataException;
import ua.training.util.constant.Exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleService {

    private AbstractDaoFactory daoFactory = AbstractDaoFactory.getInstance();

    public Article createArticle(Article article) throws IncorrectDataException {
        ArticleDao articleDao = daoFactory.createArticleDao();
        try{
            articleDao.setAutoCommit(false);
            article.setText(textareaToHTML(article.getText()));
            int id = articleDao.create(article);
            if (id == 0) {
                throw new SQLIntegrityConstraintViolationException();
            }
            article.setId(id);
        } catch (SQLIntegrityConstraintViolationException e) {
            articleDao.rollback();
            throw new IncorrectDataException(Exceptions.INCORRECT_DATA);
        } finally {
            articleDao.close();
        }
        return article;
    }

    public Article getById(int id) {
        try(ArticleDao articleDao = daoFactory.createArticleDao()) {
            return articleDao.findById(id);
        }
    }

    public Map<Integer, List<Article>> getArticlesOnPagesByPeriodical(int id, int articlesOnPage) {
        try (ArticleDao articleDao = DaoFactory.getInstance().createArticleDao()) {
            Map<Integer, List<Article>> result = new HashMap<>();
            List<Article> pageOfArticles = articleDao
                    .findByPeriodicalFixedNumberOfArticles(id, articlesOnPage, 0);

            for (int pageNumber = 1; !pageOfArticles.isEmpty(); pageNumber++) {
                result.put(pageNumber, pageOfArticles);
                pageOfArticles = articleDao
                        .findByPeriodicalFixedNumberOfArticles(id, articlesOnPage, articlesOnPage*pageNumber);
            }
            return result;
        }
    }

    @NotNull
    private String textareaToHTML(String text){
        String[] paragraphs = text.split("\r\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (String paragraph: paragraphs) {
            stringBuilder.append("<p>").append(paragraph);
        }
        return stringBuilder.toString();
    }
}

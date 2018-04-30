package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entities.Article;
import ua.training.model.service.ArticleService;
import ua.training.model.service.PeriodicalService;
import ua.training.util.constant.Attributes;
import ua.training.util.constant.Commands;
import ua.training.util.constant.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class ArticleListCommand implements Command {

    private static final Logger logger = LogManager.getLogger(PeriodicalListCommand.class);
    private ArticleService articleService;

    ArticleListCommand(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Map<Integer, List<Article>> articlesOnPage = articleService
                .getArticlesOnPages(4);

        String articlesPage = request.getParameter(Attributes.ARTICLES_PAGE);

        if (articlesPage == null || articlesPage.isEmpty()) articlesPage = "1";

        List<Article> articlesList =
                articlesOnPage.getOrDefault(Integer.parseInt(articlesPage), articlesOnPage.get(1));

        request.setAttribute(Attributes.PAGES, articlesOnPage.keySet());
        request.setAttribute(Attributes.CURRENT_PAGE, articlesPage);

        String idPeriodical = request.getParameter(Attributes.ID_PERIODICAL);

        if( idPeriodical != null ) {
            request.setAttribute(Attributes.ARTICLE_LIST, articleService
                    .getArticlesOnPagesByPeriodical(Integer.parseInt(idPeriodical),4));
        } else {
            request.setAttribute(Attributes.ARTICLE_LIST, articlesList);
        }
        request.getSession().setAttribute(Attributes.PAGE, Commands.ARTICLES + "?" + Attributes.ID_PERIODICAL
                + "=" + idPeriodical + "&" + Attributes.ARTICLES_PAGE + "=" + articlesPage);

        logger.info("Show articles");
        return Pages.ARTICLES;
    }
}

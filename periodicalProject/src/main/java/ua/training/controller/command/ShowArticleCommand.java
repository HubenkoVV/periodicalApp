package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.service.ArticleService;
import ua.training.util.constant.Attributes;
import ua.training.util.constant.Commands;
import ua.training.util.constant.Pages;

import javax.servlet.http.HttpServletRequest;

public class ShowArticleCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowArticleCommand.class);
    private ArticleService articleService;

    ShowArticleCommand(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Integer idArticle = (Integer) request.getAttribute(Attributes.ARTICLE_ID);
        request.setAttribute(Attributes.ARTICLE, articleService.getById(idArticle));
        request.getSession().setAttribute(Attributes.PAGE, Commands.SHOW_ARTICLE + "?"+ Attributes.ARTICLE_ID + "=" + idArticle );
        logger.info("Show article with id = " + idArticle);
        return Pages.ARTICLE;
    }
}

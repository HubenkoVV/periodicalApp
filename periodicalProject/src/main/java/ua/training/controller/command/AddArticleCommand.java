package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entities.Article;
import ua.training.model.service.ArticleService;
import ua.training.model.service.exception.IncorrectDataException;
import ua.training.util.constant.Attributes;
import ua.training.util.constant.Commands;
import ua.training.util.constant.Messages;
import ua.training.util.constant.Pages;
import ua.training.util.locale.LocalizeMessage;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class AddArticleCommand implements Command{

    private ArticleService articleService;
    private static final Logger logger = LogManager.getLogger(PeriodicalListCommand.class);

    AddArticleCommand(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Article article;
        request.setAttribute(Attributes.EXCEPTION, null);
        request.setAttribute(Attributes.MESSAGE, null);
        try {
            article = new Article.ArticleBuilder()
                    .buildDate(LocalDate.now())
                    .buildIdPeriodical(Integer.parseInt(request.getParameter(Attributes.ID_PERIODICAL)))
                    .buildName(request.getParameter(Attributes.ARTICLE_NAME))
                    .buildText(request.getParameter(Attributes.ARTICLE_TEXT))
                    .build();
            article = articleService.createArticle(article);
            request.setAttribute(Attributes.ARTICLE, article);
        } catch (IncorrectDataException e) {
            request.setAttribute(Attributes.EXCEPTION, LocalizeMessage.getException(e.getMessage()));
            logger.info("Failed adding article");
            return Pages.ADD_ARTICLE;
        }
        request.setAttribute(Attributes.MESSAGE, LocalizeMessage.getMessage(Messages.ADD_ARTICLE));
        logger.info("Added article");
        request.getSession().setAttribute(Attributes.PAGE, Commands.SHOW_ARTICLE + "?"+ Attributes.ARTICLE_ID + "=" + article.getId() );
        return Pages.ARTICLE;
    }
}

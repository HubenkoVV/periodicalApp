package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entities.Periodical;
import ua.training.model.entities.lazyload.LazyPeriodical;
import ua.training.model.service.PeriodicalService;
import ua.training.model.service.exception.IncorrectDataException;
import ua.training.util.constant.Attributes;
import ua.training.util.constant.Messages;
import ua.training.util.constant.Pages;
import ua.training.util.locale.LocalizeMessage;

import javax.servlet.http.HttpServletRequest;

public class AddPeriodicalCommand implements Command {

    private static final Logger logger = LogManager.getLogger(PeriodicalListCommand.class);
    private PeriodicalService periodicalService;

    AddPeriodicalCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(Attributes.EXCEPTION, null);
        request.setAttribute(Attributes.MESSAGE, null);
        try {
            Periodical periodical = new LazyPeriodical.PeriodicalBuilder()
                    .buildName(request.getParameter(Attributes.PERIODICAL_NAME))
                    .buildPrice(Integer.valueOf(request.getParameter(Attributes.PERIODICAL_PRICE)))
                    .buildShortDescription(request.getParameter(Attributes.PERIODICAL_DESCRIPTION))
                    .build();
            periodicalService.createPeriodical(periodical);
        } catch (IncorrectDataException e) {
            request.setAttribute(Attributes.EXCEPTION, LocalizeMessage.getException(e.getMessage()));
            logger.info("Failed adding periodical");
            return Pages.ADD_PERIODICAL;
        }
        request.setAttribute(Attributes.MESSAGE, LocalizeMessage.getMessage(Messages.ADD_PERIODICAL));
        logger.info("Added periodical");
        return Pages.ADD_PERIODICAL;
    }
}

package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entities.Periodical;
import ua.training.model.entities.User;
import ua.training.model.service.PeriodicalService;
import ua.training.util.constant.Attributes;
import ua.training.util.constant.Commands;
import ua.training.util.constant.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PeriodicalListCommand implements Command {

    private static final Logger logger = LogManager.getLogger(PeriodicalListCommand.class);
    private PeriodicalService periodicalService;

    PeriodicalListCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }
    @Override
    public String execute(HttpServletRequest request) {

        List<Periodical> periodicalsOfUser;

        Map<Integer, List<Periodical>> periodicalsOnPage = periodicalService
                .getPeriodicalsOnPages(4);

        String periodicalPage = request.getParameter(Attributes.PERIODICAL_PAGE);

        if (periodicalPage == null || periodicalPage.isEmpty()) periodicalPage = "1";

        List<Periodical> periodicalList =
                periodicalsOnPage.getOrDefault(Integer.parseInt(periodicalPage), periodicalsOnPage.get(1));

        request.setAttribute(Attributes.PAGES, periodicalsOnPage.keySet());
        request.setAttribute(Attributes.CURRENT_PAGE, periodicalPage);
        request.setAttribute(Attributes.PERIODICAL_LIST, periodicalList);

        User user =  (User) request.getSession().getAttribute(Attributes.USER);

        if (user != null) {
            periodicalsOfUser = user.getPeriodicals();
        } else {
            periodicalsOfUser = new ArrayList<>();
        }
        request.setAttribute(Attributes.PERIODICALS_USER, periodicalsOfUser);
        request.getSession().setAttribute(Attributes.PAGE, Commands.PERIODICALS + "?" + Attributes.PERIODICAL_PAGE
                + "=" + periodicalPage);
        logger.info("Show periodicals");
        return Pages.PERIODICALS;
    }
}

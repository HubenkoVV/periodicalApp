package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entities.Periodical;
import ua.training.model.service.PeriodicalService;
import ua.training.util.constant.Attributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AddToBasketCommand implements Command {

    private static final Logger logger = LogManager.getLogger(PeriodicalListCommand.class);
    private PeriodicalService periodicalService;

    AddToBasketCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String execute(HttpServletRequest request) {
        List<Periodical> periodicalsInBasket = (List<Periodical>) request.getSession().getAttribute(Attributes.PERIODICALS_IN_BASKET);
        if(periodicalsInBasket == null) periodicalsInBasket = new ArrayList<>();
        Integer periodicalId = (Integer) request.getSession().getAttribute(Attributes.ID_PERIODICAL);
        Integer fullPrice = (Integer) request.getSession().getAttribute(Attributes.FULL_PRICE);

        Periodical periodical = periodicalService.getById(periodicalId);
        periodicalsInBasket.add(periodical);
        fullPrice += periodical.getPrice();

        request.getSession().setAttribute(Attributes.FULL_PRICE, fullPrice);
        request.getSession().setAttribute(Attributes.PERIODICALS_IN_BASKET, periodicalsInBasket);
        logger.info("Bought " + periodicalsInBasket.size() + " periodicals");
        return (String) request.getSession().getAttribute(Attributes.PAGE);
    }
}

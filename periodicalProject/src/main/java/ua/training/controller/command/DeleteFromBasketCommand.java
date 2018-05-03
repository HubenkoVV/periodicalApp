package ua.training.controller.command;

import ua.training.model.entities.Periodical;
import ua.training.model.service.PeriodicalService;
import ua.training.util.constant.Attributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class DeleteFromBasketCommand implements Command {
    private PeriodicalService periodicalService;

    DeleteFromBasketCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String execute(HttpServletRequest request) {
        List<Periodical> periodicalsInBasket = (List<Periodical>) request.getSession().getAttribute(Attributes.PERIODICALS_IN_BASKET);
        int periodicalId = Integer.parseInt(request.getParameter(Attributes.ID_PERIODICAL));
        int fullPrice = 0;

        Periodical periodical = periodicalService.getById(periodicalId);
        periodicalsInBasket.remove(periodical);
        for (Periodical p: periodicalsInBasket) {
            fullPrice+= p.getPrice();
        }

        request.getSession().setAttribute(Attributes.FULL_PRICE, fullPrice);
        request.getSession().setAttribute(Attributes.PERIODICALS_IN_BASKET, periodicalsInBasket);
        return (String) request.getSession().getAttribute(Attributes.PAGE);
    }
}

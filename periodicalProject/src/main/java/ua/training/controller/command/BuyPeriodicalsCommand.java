package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entities.Payment;
import ua.training.model.entities.Periodical;
import ua.training.model.entities.User;
import ua.training.model.entities.UserRole;
import ua.training.model.entities.lazyload.LazyPayment;
import ua.training.model.service.PaymentService;
import ua.training.model.service.exception.IncorrectDataException;
import ua.training.model.service.exception.NotEnoughMoneyException;
import ua.training.util.constant.Attributes;
import ua.training.util.constant.Exceptions;
import ua.training.util.locale.LocalizeMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BuyPeriodicalsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(PeriodicalListCommand.class);
    private PaymentService paymentService;

    BuyPeriodicalsCommand(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(Attributes.EXCEPTION,null);

        if(request.getSession().getAttribute(Attributes.USER_ROLE) == UserRole.GUEST){
            request.setAttribute(Attributes.EXCEPTION, LocalizeMessage.getException(Exceptions.NOT_LOGGED_IN));
            return (String) request.getSession().getAttribute(Attributes.PAGE);
        }

        User user = (User) request.getSession().getAttribute(Attributes.USER);
        List<Periodical> periodicals = (List<Periodical>) request.getSession().getAttribute(Attributes.PERIODICALS_IN_BASKET);

        try{
            Payment payment = new LazyPayment.PaymentBuilder()
                    .buildIdUser(user.getId())
                    .buildPeriodicals(periodicals)
                    .buildPrice((Integer) request.getSession().getAttribute(Attributes.FULL_PRICE))
                    .build();
            paymentService.createPayment(payment, user);
        } catch (IncorrectDataException | NotEnoughMoneyException e) {
            request.setAttribute(Attributes.EXCEPTION, e.getMessage());
            return (String) request.getSession().getAttribute(Attributes.PAGE);
        }
        request.setAttribute(Attributes.PERIODICALS_IN_BASKET, null);
        return (String) request.getSession().getAttribute(Attributes.PAGE);
    }
}

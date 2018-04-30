package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entities.User;
import ua.training.model.entities.UserRole;
import ua.training.model.service.UserService;
import ua.training.model.service.exception.IncorrectDataException;
import ua.training.util.constant.Attributes;
import ua.training.util.constant.Messages;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SignInCommand.class);
    private UserService userService;

    RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(Attributes.LOGIN);
        request.setAttribute(Attributes.EXCEPTION, null);
        request.setAttribute(Attributes.MESSAGE, null);
        try {
            User user = userService.createUser(
                    new User.UserBuilder()
                            .buildLogin(request.getParameter(Attributes.LOGIN))
                            .buildName(request.getParameter(Attributes.NAME))
                            .buildSurname(request.getParameter(Attributes.SURNAME))
                            .buildPassword(request.getParameter(Attributes.PASSWORD).hashCode())
                            .buildRole(UserRole.USER)
                            .buildMoney(0)
                            .buildPhone(request.getParameter(Attributes.PHONE))
                            .build(), request.getParameter(Attributes.PASSWORD), request.getParameter(Attributes.PASSWORD_AGAIN));
            request.getSession().setAttribute(Attributes.USER, user);
            request.getSession().setAttribute(Attributes.USER_ROLE, user.getRole());
        } catch (IncorrectDataException e) {
            request.setAttribute(Attributes.EXCEPTION, e.getMessage());
            logger.info("Registration by \'" + email + "\' was failed");
            request.setAttribute(Attributes.OPEN_REGISTRATION, "$(\"#registration_modal\").modal({show: open_registration});");
            return (String) request.getSession().getAttribute(Attributes.PAGE);
        }

        logger.info("Registration by \'" + email + "\'");
        request.setAttribute(Attributes.MESSAGE, Messages.ADD_USER);
        return (String) request.getSession().getAttribute(Attributes.PAGE);
    }
}

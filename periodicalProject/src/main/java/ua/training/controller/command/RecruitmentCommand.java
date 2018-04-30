package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entities.User;
import ua.training.model.service.UserService;
import ua.training.model.service.exception.IncorrectDataException;
import ua.training.util.constant.Attributes;

import javax.servlet.http.HttpServletRequest;

public class RecruitmentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SignInCommand.class);
    private UserService userService;

    RecruitmentCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(Attributes.EXCEPTION, null);
        User user = (User) request.getSession().getAttribute(Attributes.USER);
        String money = request.getParameter(Attributes.MONEY);
        try {
            user = userService.updateAccount(user, money);
            request.getSession().setAttribute(Attributes.USER, user);
        } catch (IncorrectDataException e) {
            logger.info("Update account for \'" + user.getLogin() + "\' was failed");
            request.setAttribute(Attributes.EXCEPTION, e.getMessage());
            return (String) request.getSession().getAttribute(Attributes.PAGE);
        }
        logger.info("Successful updating account for \'" + user.getLogin() + "\'");
        return (String) request.getSession().getAttribute(Attributes.PAGE);
    }
}

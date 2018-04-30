package ua.training.controller.command;

import ua.training.model.service.ArticleService;
import ua.training.model.service.PaymentService;
import ua.training.model.service.PeriodicalService;
import ua.training.model.service.UserService;
import ua.training.util.constant.Commands;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CreatorCommand {

    private Map<String, Command> commands;

    public CreatorCommand() {
        UserService userService = new UserService();
        PeriodicalService periodicalService = new PeriodicalService();
        PaymentService paymentService = new PaymentService();
        ArticleService articleService = new ArticleService();

        commands = new HashMap<>();
        commands.put(Commands.LANGUAGE, new LanguageCommand());
        commands.put(Commands.LOGIN, new SignInCommand(userService));
        commands.put(Commands.REGISTRATION, new RegistrationCommand(userService));
        commands.put(Commands.SIGNOUT, new SignOutCommand());
        commands.put(Commands.ADD_ARTICLE,new AddArticleCommand(articleService));
        commands.put(Commands.ADD_PERIODICAL, new AddPeriodicalCommand(periodicalService));
        commands.put(Commands.ARTICLES, new ArticleListCommand(articleService));
        commands.put(Commands.BUY_PERIODICAL, new BuyPeriodicalsCommand(paymentService));
        commands.put(Commands.PERIODICALS, new PeriodicalListCommand(periodicalService));
        commands.put(Commands.SHOW_ARTICLE,new ShowArticleCommand(articleService));
        commands.put(Commands.UPDATE_ACCOUNT, new RecruitmentCommand(userService));
        commands.put(Commands.TO_BASKET, new AddToBasketCommand(periodicalService));
        commands.put("/", new PeriodicalListCommand(periodicalService));
    }

    public String action(HttpServletRequest request){
        String path = request.getRequestURI();
        String name = path.replaceAll(".*/api/" , "");
        Command command = commands.getOrDefault(name, new PeriodicalListCommand(new PeriodicalService()));
        return command.execute(request);
    }
}

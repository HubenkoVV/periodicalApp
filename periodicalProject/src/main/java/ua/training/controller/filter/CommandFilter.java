package ua.training.controller.filter;

import ua.training.controller.command.Command;
import ua.training.model.entities.UserRole;
import ua.training.util.constant.Attributes;
import ua.training.util.constant.Commands;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandFilter implements Filter {

    Map<UserRole, List<String>> commands;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        commands = new HashMap<>();
        List<String> notUserCommands = new ArrayList<>();
        notUserCommands.add(Commands.ADD_ARTICLE);
        notUserCommands.add(Commands.ADD_PERIODICAL);
        commands.put(UserRole.USER, notUserCommands);
        List<String> notGuestCommands = new ArrayList<>();
        notGuestCommands.add(Commands.UPDATE_ACCOUNT);
        notGuestCommands.add(Commands.SIGNOUT);
        notGuestCommands.add(Commands.ADD_ARTICLE);
        notGuestCommands.add(Commands.ADD_PERIODICAL);
        commands.put(UserRole.GUEST, notGuestCommands);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String command = request.getRequestURI();
        command = command.replaceAll(".*/api/" , "");
        UserRole userRole = (UserRole)(request.getSession().getAttribute(Attributes.USER_ROLE));

        if(commands.get(userRole).contains(command))
            throw new UnsupportedOperationException(command);

        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}

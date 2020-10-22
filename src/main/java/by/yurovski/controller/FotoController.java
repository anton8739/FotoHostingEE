package by.yurovski.controller;

import by.yurovski.command.Command;
import by.yurovski.command.CommandFactory;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.manager.PageContentManager;
import by.yurovski.manager.PathManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/foto")
public class FotoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            String currentCommand = request.getParameter("command");
            if (currentCommand == null || currentCommand.isEmpty()) {
                currentCommand = "foto-page-redirect";
            }
            Command command = CommandFactory.defineCommand(currentCommand);

            String page = command.execute(request);

            if (page != null) {
                getServletContext().getRequestDispatcher(page).forward(request, response);
            }
        }

}

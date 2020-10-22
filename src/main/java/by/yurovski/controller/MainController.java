package by.yurovski.controller;

import by.yurovski.command.Command;
import by.yurovski.command.CommandFactory;
import by.yurovski.command.redirect.FotoPageRedirect;
import by.yurovski.command.redirect.UserAccountRedirectCommand;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.manager.PathManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/controller")
public class MainController  extends HttpServlet {
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
        String page;

        if (currentCommand == null || currentCommand.isEmpty()) {
            currentCommand = "EMPTY";
        }
        Command command = CommandFactory.defineCommand(currentCommand);
        if(currentCommand.equals("like-img") || currentCommand.equals("like-comment")
                || currentCommand.equals("follow-unfollow")
                || currentCommand.equals("forgot-password")
                || currentCommand.equals("edit-foto")
                || currentCommand.equals("edit-user")){
            command.executeJSON(request,response);
        } else {
            if (currentCommand.equals("add-new-comment")){
              //  command.execute(request);
                String fotoName=request.getParameter("foto");
                int userId=Integer.parseInt(request.getSession().getAttribute("id").toString());
                response.sendRedirect("foto?userId="+userId+"&foto="+fotoName);
            }
            page = command.execute(request);
            if (page != null) {
                getServletContext().getRequestDispatcher(page).forward(request, response);
            }
        }

    }
}

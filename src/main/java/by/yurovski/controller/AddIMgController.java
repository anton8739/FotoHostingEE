package by.yurovski.controller;

import by.yurovski.command.Command;
import by.yurovski.command.CommandFactory;
import by.yurovski.command.foto.AddNewImgCommand;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.manager.PathManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/img")
public class AddIMgController extends HttpServlet {
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

            Command command = AddNewImgCommand.getInstance();
            command.execute(request);
            response.sendRedirect("controller?command=user-account-redirect");

  }
}

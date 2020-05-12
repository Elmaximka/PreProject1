package servlet;


import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    UserService userService = UserService.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/admin.jsp").forward(req,resp);
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.cleanUp();
        doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().contains("post")) {
            userService.addUser(new User(req.getParameter("name"), req.getParameter("password"),
                    req.getParameter("gender"), req.getParameter("role")));
        }
        if (req.getPathInfo().contains("delete")) {
            userService.deleteUser(req.getParameter("name"));
        }
        if (req.getPathInfo().contains("change")) {
            User user = userService.getUserByName(req.getParameter("name"));
            if (!req.getParameter("newName").equals("") && !req.getParameter("password").equals("")) {
                user.setName(req.getParameter("newName"));
                user.setPassword(req.getParameter("password"));
            } else if (!req.getParameter("newName").equals("")) {
                user.setName(req.getParameter("newName"));
            } else if (!req.getParameter("password").equals("")) {
                user.setPassword(req.getParameter("newName"));
            }
            user.setGender(req.getParameter("gender"));
            user.setRole(req.getParameter("role"));
            if (userService.getUserByName(req.getParameter("newName")) == null) {
                userService.deleteUser(req.getParameter("name"));
            } else {
                resp.getWriter().println("Unavailiable name, try another name");
            }
            userService.addUser(user);
        }
        doGet(req, resp);
    }
}
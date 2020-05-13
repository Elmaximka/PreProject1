package servlet;

import com.google.gson.Gson;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    Gson gson = new Gson();
    UserService userService = UserService.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/user/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            User user = userService.getUserByName(req.getParameter("name"));
            if(user.getPassword().equals(req.getParameter("password"))) {
                User userSession = (User) session.getAttribute("role");
                if (!(userSession == null)) {
                    session.removeAttribute("role");
                }
                session.setAttribute("role", user.getRole());
            }
            if (user.getRole().equalsIgnoreCase("admin") &&
                    user.getPassword().equals(req.getParameter("password"))) {
                resp.sendRedirect("/admin.jsp");
            } else if (user.getPassword().equals(req.getParameter("password"))) {
                resp.getWriter().write(gson.toJson(user));
                resp.setStatus(200);
            } else {
                resp.getWriter().println("No such User registered or password is incorrect");
            }
        } catch (NoSuchElementException e) {
            resp.getWriter().write("No such User registered");
            resp.setStatus(404);
        }
    }

}

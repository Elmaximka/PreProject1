package servlet;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    FilterConfig fc;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        fc = filterConfig;
    }

    @Override
    public void destroy() {
        fc = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String user = (String) ((HttpServletRequest) request).getSession().getAttribute("role");
        if(user == null){
            request.getServletContext().getRequestDispatcher("/auth").forward(request, response);
        }
        if (user.equalsIgnoreCase("admin")) {
            chain.doFilter(request, response);
        }else {
            request.getServletContext().getRequestDispatcher("/user").forward(request,response);
        }
    }
}
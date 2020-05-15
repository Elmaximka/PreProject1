package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter("/user")
public class UserFilter implements Filter {
    private FilterConfig fc;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        fc = filterConfig;
    }

    @Override
    public void destroy() {
        fc = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String user = (String) ((HttpServletRequest) request).getSession().getAttribute("role");
        if (user == null) {
            request.getServletContext().getRequestDispatcher("/auth").forward(request, response);
        }
        if (user.equalsIgnoreCase("admin") || user.equalsIgnoreCase("user")) {
            chain.doFilter(request, response);
        } else {
            request.getServletContext().getRequestDispatcher("/auth").forward(request, response);
        }
    }
}

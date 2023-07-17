package cinema.filters;

import cinema.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

public class RequestSignInServlet implements Filter {

    private ServletContext servletContext;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        Map<String, String[]> params = httpRequest.getParameterMap();

        if (httpRequest.getMethod().equalsIgnoreCase("POST")) {
            User usr = new User();

            for (Map.Entry<String, String[]> param : params.entrySet()) {
                for (String value : param.getValue()) {
                    if (param.getKey().compareTo("login") == 0) {
                        usr.setEmail(value);
                    } else if (param.getKey().compareTo("password") == 0) {
                        usr.setPassword(value);
                    }
                }
            }

            this.servletContext.setAttribute("newUserLogging", usr);
        }
        else if (httpRequest.getMethod().equalsIgnoreCase("GET")) {
            HttpSession session = httpRequest.getSession(false);
            if (session.getAttribute("user") != null) {
                ServletContext context = httpRequest.getServletContext();
                context.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(servletRequest, servletResponse);
            }

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

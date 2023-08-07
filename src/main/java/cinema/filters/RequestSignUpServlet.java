package cinema.filters;

import cinema.models.User;
import cinema.services.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Map;

public class RequestSignUpServlet implements Filter {

    private ServletContext servletContext;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        Map<String, String[]> params = httpRequest.getParameterMap();

        ApplicationContext appContext = (ApplicationContext) this.servletContext.getAttribute("springContext");
        UserServiceImpl userService = (UserServiceImpl) appContext.getBean(UserServiceImpl.class);

        if (httpRequest.getMethod().equalsIgnoreCase("POST")) {
            User usr = new User();

            for (Map.Entry<String, String[]> param : params.entrySet()) {
                for (String value : param.getValue()) {
                    if (param.getKey().compareTo("email") == 0) {
                        usr.setEmail(value);
                    } else if (param.getKey().compareTo("firstName") == 0) {
                        usr.setFirstName(value);
                    } else if (param.getKey().compareTo("lastName") == 0) {
                        usr.setLastName(value);
                    } else if (param.getKey().compareTo("phoneNumber") == 0) {
                        usr.setPhoneNumber(value);
                    } else if (param.getKey().compareTo("password") == 0) {
                        usr.setPassword(value);
                    }
                }
            }

            this.servletContext.setAttribute("newUser", usr);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

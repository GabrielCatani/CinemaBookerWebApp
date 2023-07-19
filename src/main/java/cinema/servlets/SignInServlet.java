package cinema.servlets;

import cinema.models.User;
import cinema.repositories.UserLoggingRepo;
import cinema.repositories.UserLoggingRepoImpl;
import cinema.repositories.UserRepoImpl;
import cinema.services.UserServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.springframework.context.ApplicationContext;

import java.io.IOException;


//TODO: Authenticates user,
// If valid, redirects to profile.jsp
// else, redirects to login page
@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletContext servletContext = getServletContext();
        ApplicationContext appContext = (ApplicationContext) servletContext.getAttribute("springContext");

        UserServiceImpl userService = (UserServiceImpl)appContext.getBean(UserServiceImpl.class);
        User usr = (User)servletContext.getAttribute("newUserLogging");
        RequestDispatcher rd;

        if (userService.signInUser(usr)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", usr.getEmail());
            session.setMaxInactiveInterval(30*60);

            Cookie usrCookie = new Cookie("user", usr.getEmail());
            response.addCookie(usrCookie);

            String encodedURL = response.encodeURL("/WEB-INF/jsp/profile.jsp");

            rd = servletContext.getRequestDispatcher(encodedURL);
            rd.forward(request, response);
        }
        else {
            rd = servletContext.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp");
            rd.forward(request, response);
        }
    }
}

package cinema.servlets;

import cinema.models.User;
import cinema.models.UserImgInfo;
import cinema.models.UserLoggingInfo;
import cinema.repositories.UserLoggingRepo;
import cinema.repositories.UserLoggingRepoImpl;
import cinema.repositories.UserRepoImpl;
import cinema.services.UserImagesServiceImpl;
import cinema.services.UserLoggingService;
import cinema.services.UserLoggingServiceImpl;
import cinema.services.UserServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.ArrayList;

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
        UserLoggingService userLogService = (UserLoggingService)appContext.getBean(UserLoggingServiceImpl.class);
        UserImagesServiceImpl userImagesService = (UserImagesServiceImpl) appContext.getBean(UserImagesServiceImpl.class);
        User usr = (User)servletContext.getAttribute("newUserLogging");
        RequestDispatcher rd;

        if (usr != null) {
            userLogService.registerUserLogging(usr, request.getRemoteAddr());
            HttpSession session = request.getSession();

            ArrayList<UserLoggingInfo> logList = (ArrayList<UserLoggingInfo>) userLogService.getAllUserLoggings(usr);
            ArrayList<UserImgInfo> imgList = (ArrayList<UserImgInfo>) userImagesService.getAllUserImages(usr);

            session.setAttribute("logList", logList);
            session.setAttribute("userEmail", usr.getEmail());
            session.setAttribute("imgList", imgList);
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

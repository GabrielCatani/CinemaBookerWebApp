package cinema.servlets;

import cinema.models.User;
import cinema.services.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

//TODO: Authenticates user,
// If valid, redirects to profile.html
// else, redirects to login page
@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private UserService userService;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletContext context = getServletContext();

        System.out.println(context.getAttribute("newUser").toString());
    }
}

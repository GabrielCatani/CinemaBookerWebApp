package cinema.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;

@WebServlet("/images")
@MultipartConfig(fileSizeThreshold = 1024*1024*10,
        maxFileSize = 1024*1024*50,
        maxRequestSize = 1024*1024*100)
public class UploadDownloadImagesServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        ApplicationContext appContext = (ApplicationContext) servletContext.getAttribute("springContext");
        Environment env = appContext.getEnvironment();
        String imgStoreDirPath  = env.getProperty("storage.path");

        File imgStoreDir = new File(imgStoreDirPath);
        if (!imgStoreDir.exists()) {
            System.out.println(imgStoreDir.mkdirs());
        }

        System.out.println("Upload images are store in: " + imgStoreDir.getAbsolutePath());

        String fileName = null;
        for (Part part : request.getParts()) {
            fileName = part.getSubmittedFileName();
            part.write(imgStoreDir.getAbsolutePath() + File.separator + fileName);
        }

        request.setAttribute("imgUploaded", fileName + " File uploaded successfully!");
        response.setStatus(HttpServletResponse.SC_OK);
        servletContext.getRequestDispatcher("/WEB-INF/jsp/profile.jsp")
                        .forward(request, response);
    }
}

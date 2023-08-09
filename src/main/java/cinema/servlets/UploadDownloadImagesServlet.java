package cinema.servlets;

import cinema.models.User;
import cinema.models.UserImgInfo;
import cinema.services.UserImagesServiceImpl;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/images/*")
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
            imgStoreDir.mkdirs();
        }

        String fileName = null;
        UserImgInfo userImgInfo = new UserImgInfo();
        for (Part part : request.getParts()) {
            fileName = part.getSubmittedFileName();

            Integer fileIndexer = 1;
            String tmp = null;
            while (new File(imgStoreDir.getAbsolutePath() + File.separator + fileName).exists()) {
                if (fileIndexer != 1) {
                     tmp = fileName.split("\\(", 2)[0];
                     fileName = tmp;
                }
                fileName = fileName.concat("(" + fileIndexer + ")");
                fileIndexer++;
            }
            userImgInfo.setFileSize(part.getSize());
            userImgInfo.setMimeType(part.getContentType());
            part.write(imgStoreDir.getAbsolutePath() + File.separator + fileName);
        }

        if (fileName != null) {
            UserImagesServiceImpl userImagesService = (UserImagesServiceImpl) appContext.getBean(UserImagesServiceImpl.class);

            User user = (User) servletContext.getAttribute("newUserLogging");

            userImgInfo.setUserId(user.getId());
            userImgInfo.setFileName(fileName);

            userImagesService.registerUserImagePath(userImgInfo);

            request.setAttribute("imgUploaded", fileName + " File uploaded successfully!");
            response.setStatus(HttpServletResponse.SC_OK);
        }
        servletContext.getRequestDispatcher("/WEB-INF/jsp/profile.jsp")
                        .forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException{
        String requestPath = request.getPathInfo();
        if (requestPath == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        ServletContext servletContext = getServletContext();
        ApplicationContext appContext = (ApplicationContext) servletContext.getAttribute("springContext");
        Environment env = appContext.getEnvironment();
        String imgStoreDirPath  = env.getProperty("storage.path");

        File usrImg = new File(imgStoreDirPath, requestPath);
        if (!usrImg.exists()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType(getServletContext().getMimeType(usrImg.getName()));
        response.setContentLength((int) usrImg.length());

        try (FileInputStream in = new FileInputStream(usrImg);
        OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}

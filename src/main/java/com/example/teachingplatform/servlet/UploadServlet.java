package com.example.teachingplatform.servlet;

import com.example.teachingplatform.util.AjaxResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Properties;
import java.util.UUID;

/**
 * 文件上传接口
 */
@MultipartConfig
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        String uploadPath = getUploadPathFromProperties(); // 从属性文件中获取上传路径
        // 检查上传路径是否存在，如果不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        // 设置响应头，指定文件名
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + pid + "\"");
        InputStream in = Files.newInputStream(Paths.get(uploadPath + File.separator + pid));
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = in.read(bytes)) != -1){
            resp.getOutputStream().write(bytes, 0, len);
            resp.getOutputStream().flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<Part> parts = request.getParts();
        String uploadPath = getUploadPathFromProperties(); // 从属性文件中获取上传路径
        // 检查上传路径是否存在，如果不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        for (Part part : parts) {
            String fileName = extractFileName(part); // 提取文件名
            if (fileName != null && !fileName.isEmpty()) {
                int i = fileName.lastIndexOf(".");
                String suffix = fileName.substring(i);
                String saveFilename = UUID.randomUUID().toString().replace("-", "") + suffix;
                String filePath = uploadPath + File.separator + saveFilename;
                try (InputStream input = part.getInputStream()) {
                    // 将上传的文件保存到指定位置
                    Files.copy(input, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    e.printStackTrace();
                    AjaxResult.error().message("文件上传失败").write(response);
                    return;
                }
                AjaxResult.ok().data("filename", "/upload?pid=" + saveFilename).write(response);
                System.out.println("文件上传成功：" + saveFilename);
            }
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return null;
    }

    private String getUploadPathFromProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("app-config.properties");
        if (inputStream != null) {
            properties.load(inputStream);
            return properties.getProperty("upload-path");
        } else {
            throw new IOException("无法加载属性文件");
        }
    }


}

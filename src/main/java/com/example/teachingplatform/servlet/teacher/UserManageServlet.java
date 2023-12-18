package com.example.teachingplatform.servlet.teacher;

import com.example.teachingplatform.entity.User;
import com.example.teachingplatform.service.UserService;
import com.example.teachingplatform.service.impl.UserServiceImpl;
import com.example.teachingplatform.util.AjaxResult;
import com.example.teachingplatform.util.RequestUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/manage/user/*")
public class UserManageServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        switch (pathInfo) {
            case "/update-pwd":
                updatePwd(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void updatePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = RequestUtil.parseJson(request, User.class);
        boolean success = userService.update(user);
        AjaxResult.auto(success).write(response);
    }

}

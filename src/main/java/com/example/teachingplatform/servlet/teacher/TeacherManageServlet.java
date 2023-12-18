package com.example.teachingplatform.servlet.teacher;

import com.example.teachingplatform.entity.Teacher;
import com.example.teachingplatform.entity.User;
import com.example.teachingplatform.service.TeacherService;
import com.example.teachingplatform.service.UserService;
import com.example.teachingplatform.service.impl.TeacherServiceImpl;
import com.example.teachingplatform.service.impl.UserServiceImpl;
import com.example.teachingplatform.util.AjaxResult;
import com.example.teachingplatform.util.RequestUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage/teacher/*")
public class TeacherManageServlet extends HttpServlet {

    private final TeacherService teacherService = new TeacherServiceImpl();
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
            case "/info":
                detail(request, response);
                break;
            case "/update-self":
                updateModel(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer userId = RequestUtil.getUserId(request);
        User user = userService.getById(userId);
        Teacher teacher = teacherService.getById(userId);
        AjaxResult.ok().data("model", teacher).data("username", user.getUsername()).write(response);
    }

    private void updateModel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Teacher teacher = RequestUtil.parseJson(request, Teacher.class);
        Integer userId = RequestUtil.getUserId(request);
        teacher.setUserId(userId);
        try {
            AjaxResult.auto(teacherService.saveOrUpdate(teacher)).write(response);
        } catch (Exception e) {
            e.printStackTrace();
            AjaxResult.error().message("工号已存在").write(response);
        }
    }
}

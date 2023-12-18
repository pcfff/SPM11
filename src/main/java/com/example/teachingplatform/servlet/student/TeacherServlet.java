package com.example.teachingplatform.servlet.student;

import com.example.teachingplatform.entity.Teacher;
import com.example.teachingplatform.service.TeacherService;
import com.example.teachingplatform.service.impl.TeacherServiceImpl;
import com.example.teachingplatform.util.AjaxResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/*")
public class TeacherServlet extends HttpServlet {

    private final TeacherService teacherService = new TeacherServiceImpl();

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
            case "/list":
                list(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Teacher> list = teacherService.list();
        AjaxResult.ok().data(list).write(response);
    }
}

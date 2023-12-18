package com.example.teachingplatform.servlet.student;


import com.example.teachingplatform.dto.CourseResourceDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.CourseResource;
import com.example.teachingplatform.service.CourseResourceService;
import com.example.teachingplatform.service.impl.CourseResourceServiceImpl;
import com.example.teachingplatform.util.AjaxResult;
import com.example.teachingplatform.util.RequestUtil;
import com.github.pagehelper.Page;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/course-resource/*")
public class CourseResourceServlet extends HttpServlet {

    private final CourseResourceService courseResourceService = new CourseResourceServiceImpl();

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
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String courseId = request.getParameter("courseId");
        CourseResource courseResource = new CourseResource();
        if (courseId != null && !"".equals(courseId)) {
            courseResource.setCourseId(Integer.parseInt(courseId));
        }
        PageResult<CourseResourceDTO> pageResult = courseResourceService.list(new Page<>(pageNum, pageSize), courseResource);
        AjaxResult.ok().data("rows", pageResult.getData()).data("count", pageResult.getTotal()).write(response);
    }

}

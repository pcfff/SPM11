package com.example.teachingplatform.servlet.teacher;


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


@WebServlet("/manage/course-resource/*")
public class CourseResourceManageServlet extends HttpServlet {

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
            case "/query":
                findById(request, response);
                break;
            case "/create":
                insertModel(request, response);
                break;
            case "/remove":
                removeModel(request, response);
                break;
            case "/update":
                updateModel(request, response);
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
        courseResource.setName(request.getParameter("name"));
        PageResult<CourseResourceDTO> pageResult = courseResourceService.list(new Page<>(pageNum, pageSize), courseResource);
        AjaxResult.ok().data("rows", pageResult.getData()).data("count", pageResult.getTotal()).write(response);
    }

    private void findById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        CourseResource courseResource = courseResourceService.queryById(id);
        AjaxResult.ok().data(courseResource).write(response);
    }

    private void insertModel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CourseResource courseResource = RequestUtil.parseJson(request, CourseResource.class);
        AjaxResult.auto(courseResourceService.insert(courseResource));
    }

    private void removeModel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        AjaxResult.auto(courseResourceService.deleteById(id)).write(response);
    }

    private void updateModel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CourseResource courseResource = RequestUtil.parseJson(request, CourseResource.class);
        AjaxResult.auto(courseResourceService.update(courseResource)).write(response);
    }
}

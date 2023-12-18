package com.example.teachingplatform.servlet.student;

import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.Course;
import com.example.teachingplatform.entity.Student;
import com.example.teachingplatform.service.CourseService;
import com.example.teachingplatform.service.StudentService;
import com.example.teachingplatform.service.impl.CourseServiceImpl;
import com.example.teachingplatform.service.impl.StudentServiceImpl;
import com.example.teachingplatform.util.AjaxResult;
import com.example.teachingplatform.util.RequestUtil;
import com.github.pagehelper.Page;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/course/*")
public class CourseServlet extends HttpServlet {

    private final CourseService courseService = new CourseServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();

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
                handleCourseList(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void handleCourseList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer userId = RequestUtil.getUserId(request);
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        Course queryWrapper = new Course();
        Student student = studentService.getById(userId);
        queryWrapper.setUserId(student.getTeacherId());
        queryWrapper.setName(request.getParameter("name"));

        PageResult<Course> pageResult = courseService.getPageList(new Page<>(pageNum, pageSize), queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", pageResult.getData());
        map.put("count", pageResult.getTotal());
        AjaxResult.ok().data(map).write(response);
    }
}

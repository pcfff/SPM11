package com.example.teachingplatform.servlet.teacher;


import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.Course;
import com.example.teachingplatform.service.CourseService;
import com.example.teachingplatform.service.impl.CourseServiceImpl;
import com.example.teachingplatform.util.AjaxResult;
import com.example.teachingplatform.util.RequestUtil;
import com.github.pagehelper.Page;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/manage/course/*")
public class CourseManageServlet extends HttpServlet {

    private final CourseService courseService = new CourseServiceImpl();

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
            case "/condition-list":
                conditionList(request, response);
                break;
            case "/list":
                list(request, response);
                break;
            case "/query":
                query(request, response);
                break;
            case "/create":
                create(request, response);
                break;
            case "/remove":
                remove(request, response);
                break;
            case "/update":
                update(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void conditionList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Course course = new Course();
        course.setUserId(RequestUtil.getUserId(request));
        List<Course> list = courseService.list(course);
        AjaxResult.ok().data(list).write(response);
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        Course course = new Course();
        course.setUserId(RequestUtil.getUserId(request));
        course.setName(request.getParameter("name")); // Set other parameters you need

        PageResult<Course> pageResult = courseService.getPageList(new Page<>(pageNum, pageSize), course);

        Map<String, Object> map = new HashMap<>(2);
        map.put("rows", pageResult.getData());
        map.put("count", pageResult.getTotal());

        AjaxResult.ok().data(map).write(response);
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Course course = courseService.getById(id);
        AjaxResult.ok().data("model", course).write(response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Course course = RequestUtil.parseJson(request, Course.class);
        course.setUserId(RequestUtil.getUserId(request));
        course.setCreateTime(new Date());
        AjaxResult.auto(courseService.save(course)).write(response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        AjaxResult.auto(courseService.removeById(id)).write(response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Course course = RequestUtil.parseJson(request, Course.class);
        AjaxResult.auto(courseService.updateById(course)).write(response);
    }
}

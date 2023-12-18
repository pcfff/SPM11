package com.example.teachingplatform.servlet.student;


import com.example.teachingplatform.dto.ExperimentDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.Experiment;
import com.example.teachingplatform.entity.Student;
import com.example.teachingplatform.service.ExperimentService;
import com.example.teachingplatform.service.StudentService;
import com.example.teachingplatform.service.impl.ExperimentServiceImpl;
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

@WebServlet("/experiment/*")
public class ExperimentServlet extends HttpServlet {

    private final ExperimentService experimentService = new ExperimentServiceImpl();
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
                handleExperimentList(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void handleExperimentList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer userId = RequestUtil.getUserId(request);
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String courseId = request.getParameter("courseId");

        Experiment queryWrapper = new Experiment();
        Student student = studentService.getById(userId);
        queryWrapper.setUserId(student.getTeacherId());
        queryWrapper.setName(request.getParameter("name"));
        if (courseId != null && !"".equals(courseId)){
            queryWrapper.setCourseId(Integer.parseInt(courseId));
        }
        PageResult<ExperimentDTO> pageResult = experimentService.getPage(new Page<>(pageNum, pageSize), queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", pageResult.getData());
        map.put("count", pageResult.getTotal());
        AjaxResult.ok().data(map).write(response);
    }
}

package com.example.teachingplatform.servlet.student;

import com.alibaba.fastjson.JSONArray;
import com.example.teachingplatform.dto.HomeworkDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.Homework;
import com.example.teachingplatform.entity.Question;
import com.example.teachingplatform.entity.Student;
import com.example.teachingplatform.service.HomeworkService;
import com.example.teachingplatform.service.QuestionService;
import com.example.teachingplatform.service.StudentService;
import com.example.teachingplatform.service.impl.HomeworkServiceImpl;
import com.example.teachingplatform.service.impl.QuestionServiceImpl;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/homework/*")
public class HomeworkServlet extends HttpServlet {

    private final HomeworkService homeworkService = new HomeworkServiceImpl();
    private final QuestionService questionService = new QuestionServiceImpl();
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
            case "/query-question":
                queryQuestion(request, response);
                break;
            case "/list":
                list(request, response);
                break;
            case "/query":
                query(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void queryQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer homeworkId = Integer.parseInt(request.getParameter("homeworkId"));
        Homework homework = homeworkService.getById(homeworkId);
        List<Question> list = new ArrayList<>();
        if (homework != null && homework.getQuestionIds() != null && !homework.getQuestionIds().isEmpty()) {
            List<Integer> ids = JSONArray.parseArray(homework.getQuestionIds(), Integer.class);
            for (Integer id : ids) {
                Question question = questionService.getById(id);
                list.add(question);
            }
        }
        AjaxResult.ok().data(list).write(response);
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer userId = RequestUtil.getUserId(request);
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String type = request.getParameter("type");

        Homework queryWrapper = new Homework();
        Student student = studentService.getById(userId);
        queryWrapper.setUserId(student.getTeacherId());
        queryWrapper.setName(request.getParameter("name"));
        if (type != null && !type.isEmpty()) {
            queryWrapper.setType(Integer.parseInt(type));
        }

        PageResult<HomeworkDTO> pageResult = homeworkService.getPage(new Page<>(pageNum, pageSize), queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", pageResult.getData());
        map.put("count", pageResult.getTotal());
        AjaxResult.ok().data(map).write(response);
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        AjaxResult.ok().data("model", homeworkService.getById(id)).write(response);
    }
}

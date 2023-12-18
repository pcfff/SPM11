package com.example.teachingplatform.servlet.teacher;

import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.dto.QuestionDTO;
import com.example.teachingplatform.entity.Question;
import com.example.teachingplatform.service.QuestionService;
import com.example.teachingplatform.service.impl.QuestionServiceImpl;
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
import java.util.List;
import java.util.Map;

@WebServlet("/manage/question/*")
public class QuestionManageServlet extends HttpServlet {

    private final QuestionService questionService = new QuestionServiceImpl();

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

    private void conditionList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer userId = RequestUtil.getUserId(request);
        String courseId = request.getParameter("courseId");

        Question queryWrapper = new Question();
        queryWrapper.setUserId(userId);
        if (courseId != null && !"".equals(courseId)){
            queryWrapper.setCourseId(Integer.parseInt(courseId));
        }
        List<Question> list = questionService.list(queryWrapper);
        AjaxResult.ok().data(list).write(response);
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        Question question = new Question();
        question.setName(request.getParameter("name"));
        String courseId = request.getParameter("courseId");
        if (courseId!= null &&!"".equals(courseId)){
            question.setCourseId(Integer.parseInt(courseId));
        }
        String type = request.getParameter("type");
        if (type!= null &&!"".equals(type)){
            question.setType(Integer.parseInt(type));
        }

        Question queryWrapper = new Question();
        queryWrapper.setName(question.getName());
        queryWrapper.setCourseId(question.getCourseId());
        queryWrapper.setType(question.getType());
        PageResult<QuestionDTO> pageResult = questionService.getPage(new Page<>(pageNum, pageSize), queryWrapper);

        Map<String, Object> map = new HashMap<>(2);
        map.put("rows", pageResult.getData());
        map.put("count", pageResult.getTotal());

        AjaxResult.ok().data(map).write(response);
    }

    private void findById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        AjaxResult.ok().data("model", questionService.getById(id)).write(response);
    }

    private void insertModel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Question question = RequestUtil.parseJson(request, Question.class);
        question.setUserId(RequestUtil.getUserId(request));
        AjaxResult.auto(questionService.save(question)).write(response);
    }

    private void removeModel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        AjaxResult.auto(questionService.removeById(id)).write(response);
    }

    private void updateModel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Question question = RequestUtil.parseJson(request, Question.class);
        AjaxResult.auto(questionService.updateById(question)).write(response);
    }
}

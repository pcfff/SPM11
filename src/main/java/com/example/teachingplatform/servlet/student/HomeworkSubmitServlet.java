package com.example.teachingplatform.servlet.student;

import com.example.teachingplatform.entity.Homework;
import com.example.teachingplatform.entity.HomeworkSubmit;
import com.example.teachingplatform.param.AnswerParam;
import com.example.teachingplatform.param.HomeworkSubmitParam;
import com.example.teachingplatform.service.HomeworkService;
import com.example.teachingplatform.service.HomeworkSubmitService;
import com.example.teachingplatform.service.impl.HomeworkServiceImpl;
import com.example.teachingplatform.service.impl.HomeworkSubmitServiceImpl;
import com.example.teachingplatform.util.AjaxResult;
import com.example.teachingplatform.util.RequestUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/homework-submit/*")
public class HomeworkSubmitServlet extends HttpServlet {

    private final HomeworkSubmitService homeworkSubmitService = new HomeworkSubmitServiceImpl();
    private final HomeworkService homeworkService = new HomeworkServiceImpl();

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
            case "/add":
                add(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer userId = RequestUtil.getUserId(request);
        Integer homeworkId = Integer.parseInt(request.getParameter("homeworkId"));
        HomeworkSubmit homework = new HomeworkSubmit();
        homework.setHomeworkId(homeworkId);
        homework.setUserId(userId);
        List<HomeworkSubmit> list = homeworkSubmitService.list(homework);
        AjaxResult.ok().data(list).write(response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer userId = RequestUtil.getUserId(request);
        HomeworkSubmitParam homeworkSubmitParam = RequestUtil.parseJson(request, HomeworkSubmitParam.class);
        Integer homeworkId = homeworkSubmitParam.getHomeworkId();

        Homework homework = homeworkService.getById(homeworkId);
        if (homework == null) {
            AjaxResult.error().message("作业不存在！").write(response);
            return;
        }
        if (homework.getDeadline().getTime() < System.currentTimeMillis()) {
            AjaxResult.error().message("作业已截止").write(response);
            return;
        }

        List<HomeworkSubmit> list = new ArrayList<>();
        Date now = new Date();
        for (AnswerParam answerParam : homeworkSubmitParam.getList()) {
            HomeworkSubmit homeworkSubmit = new HomeworkSubmit();
            homeworkSubmit.setUserId(userId);
            homeworkSubmit.setHomeworkId(homeworkId);
            homeworkSubmit.setQuestionId(answerParam.getQuestionId());
            homeworkSubmit.setAnswer(answerParam.getAnswer());
            homeworkSubmit.setScore(answerParam.getScore());
            homeworkSubmit.setCreateTime(now);
            // 自动批改
            if (homework.getCorrect() == 0){
                homeworkSubmit.setStatus(1);
            // 手动批改
            }else {
                homeworkSubmit.setStatus(0);
            }
            list.add(homeworkSubmit);
        }
        try {
            boolean success = homeworkSubmitService.saveBatch(list);
            AjaxResult.auto(success).write(response);
        } catch (Exception e) {
            e.printStackTrace();
            AjaxResult.error().message("你已经提交过了！").write(response);
        }
    }
}

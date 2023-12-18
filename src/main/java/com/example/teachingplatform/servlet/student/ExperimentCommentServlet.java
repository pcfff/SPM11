package com.example.teachingplatform.servlet.student;

import com.example.teachingplatform.dto.ExperimentCommentDTO;
import com.example.teachingplatform.entity.ExperimentComment;
import com.example.teachingplatform.service.ExperimentCommentService;
import com.example.teachingplatform.service.impl.ExperimentCommentServiceImpl;
import com.example.teachingplatform.util.AjaxResult;
import com.example.teachingplatform.util.RequestUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/experiment-comment/*")
public class ExperimentCommentServlet extends HttpServlet {

    private final ExperimentCommentService experimentCommentService = new ExperimentCommentServiceImpl();

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

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String experimentId = request.getParameter("experimentId");
        ExperimentComment experimentComment = new ExperimentComment();
        experimentComment.setExperimentId(Integer.parseInt(experimentId));
        List<ExperimentCommentDTO> list = experimentCommentService.list(experimentComment);
        AjaxResult.ok().data(list).write(response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExperimentComment experimentComment = RequestUtil.parseJson(request, ExperimentComment.class);
        Integer userId = RequestUtil.getUserId(request);
        experimentComment.setUserId(userId);
        experimentComment.setCreateTime(new Date());
        boolean insert = experimentCommentService.insert(experimentComment);
        AjaxResult.auto(insert).write(response);
    }

}

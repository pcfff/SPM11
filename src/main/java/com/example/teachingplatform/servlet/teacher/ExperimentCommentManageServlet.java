package com.example.teachingplatform.servlet.teacher;

import com.example.teachingplatform.dto.ExperimentCommentChildDTO;
import com.example.teachingplatform.dto.ExperimentCommentDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.ExperimentComment;
import com.example.teachingplatform.service.ExperimentCommentService;
import com.example.teachingplatform.service.impl.ExperimentCommentServiceImpl;
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
import java.util.List;

@WebServlet("/manage/experiment-comment/*")
public class ExperimentCommentManageServlet extends HttpServlet {

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
            case "/remove":
                remove(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ExperimentCommentChildDTO experimentComment = new ExperimentCommentChildDTO();
        experimentComment.setUsername(request.getParameter("username"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        PageResult<ExperimentCommentChildDTO> list = experimentCommentService.getPageList(new Page<>(pageNum, pageSize), experimentComment);
        AjaxResult.ok().data(list).write(response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        AjaxResult.auto(experimentCommentService.deleteById(id)).write(response);
    }


}

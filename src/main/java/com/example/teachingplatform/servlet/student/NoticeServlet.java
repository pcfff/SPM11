package com.example.teachingplatform.servlet.student;


import com.example.teachingplatform.dto.NoticeDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.Notice;
import com.example.teachingplatform.service.NoticeService;
import com.example.teachingplatform.service.impl.NoticeServiceImpl;
import com.example.teachingplatform.util.AjaxResult;
import com.example.teachingplatform.util.RequestUtil;
import com.github.pagehelper.Page;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/notice/*")
public class NoticeServlet extends HttpServlet {

    private final NoticeService noticeService = new NoticeServiceImpl(); // Instantiate your service

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
        Notice notice = new Notice();
        if (courseId != null && !"".equals(courseId)) {
            notice.setCourseId(Integer.parseInt(courseId));
        }
        PageResult<NoticeDTO> pageResult = noticeService.list(new Page<>(pageNum, pageSize), notice);
        AjaxResult.ok().data("rows", pageResult.getData()).data("count", pageResult.getTotal()).write(response);
    }


}

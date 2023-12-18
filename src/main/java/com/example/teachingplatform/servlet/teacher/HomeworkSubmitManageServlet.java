package com.example.teachingplatform.servlet.teacher;

import com.example.teachingplatform.dto.HomeworkSubmitDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.HomeworkSubmit;
import com.example.teachingplatform.service.HomeworkSubmitService;
import com.example.teachingplatform.service.impl.HomeworkSubmitServiceImpl;
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

@WebServlet("/manage/homework-submit/*")
public class HomeworkSubmitManageServlet extends HttpServlet {

    private final HomeworkSubmitService homeworkSubmitService = new HomeworkSubmitServiceImpl();

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
            case "/detail-list":
                detailList(request, response);
                break;
            case "/list":
                list(request, response);
                break;
            case "/correct":
                correct(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void detailList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer studentId = Integer.parseInt(request.getParameter("studentId"));
        Integer homeworkId = Integer.parseInt(request.getParameter("homeworkId"));

        HomeworkSubmit homeworkSubmit = new HomeworkSubmit();
        homeworkSubmit.setUserId(studentId);
        homeworkSubmit.setHomeworkId(homeworkId);
        List<HomeworkSubmit> list = homeworkSubmitService.list(homeworkSubmit);
        AjaxResult.ok().data(list).write(response);
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        HomeworkSubmitDTO homeworkSubmit = new HomeworkSubmitDTO();
        String studentName = request.getParameter("studentName");
        String homeworkId = request.getParameter("homeworkId");
        if (homeworkId != null && !"".equals(homeworkId)) {
            homeworkSubmit.setHomeworkId(Integer.parseInt(homeworkId));
        }
        homeworkSubmit.setStudentName(studentName);

        PageResult<HomeworkSubmitDTO> pageResult = homeworkSubmitService.getPage(new Page<>(pageNum, pageSize), homeworkSubmit, RequestUtil.getUserId(request));

        Map<String, Object> map = new HashMap<>(2);
        map.put("rows", pageResult.getData());
        map.put("count", pageResult.getTotal());

        AjaxResult.ok().data(map).write(response);
    }

    private void correct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<HomeworkSubmit> list = RequestUtil.parseList(request, HomeworkSubmit.class);
        boolean success = homeworkSubmitService.updateBatch(list);
        AjaxResult.ok().data(success).write(response);
    }
}

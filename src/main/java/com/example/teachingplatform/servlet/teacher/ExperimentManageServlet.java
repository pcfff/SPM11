package com.example.teachingplatform.servlet.teacher;


import com.example.teachingplatform.dto.ExperimentDTO;
import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.entity.Experiment;
import com.example.teachingplatform.service.ExperimentService;
import com.example.teachingplatform.service.impl.ExperimentServiceImpl;
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
import java.util.Map;

@WebServlet("/manage/experiment/*")
public class ExperimentManageServlet extends HttpServlet {

    private final ExperimentService experimentService = new ExperimentServiceImpl();

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

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        Experiment experiment = new Experiment();
        experiment.setUserId(RequestUtil.getUserId(request));
        experiment.setName(request.getParameter("name"));

        PageResult<ExperimentDTO> pageResult = experimentService.getPage(new Page<>(pageNum, pageSize), experiment);

        Map<String, Object> map = new HashMap<>(2);
        map.put("rows", pageResult.getData());
        map.put("count", pageResult.getTotal());

        AjaxResult.ok().data(map).write(response);
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Experiment experiment = experimentService.getById(id);
        AjaxResult.ok().data("model", experiment).write(response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Experiment experiment = RequestUtil.parseJson(request, Experiment.class);
        experiment.setUserId(RequestUtil.getUserId(request));
        experiment.setCreateTime(new Date());
        AjaxResult.auto(experimentService.save(experiment)).write(response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        AjaxResult.auto(experimentService.removeById(id)).write(response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Experiment experiment = RequestUtil.parseJson(request, Experiment.class);
        AjaxResult.auto(experimentService.updateById(experiment)).write(response);
    }
}

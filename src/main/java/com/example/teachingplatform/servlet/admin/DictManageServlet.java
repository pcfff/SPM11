package com.example.teachingplatform.servlet.admin;

import com.example.teachingplatform.entity.Dict;
import com.example.teachingplatform.service.DictService;
import com.example.teachingplatform.service.impl.DictServiceImpl;
import com.example.teachingplatform.util.AjaxResult;
import com.example.teachingplatform.util.RequestUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/manage/dict/*")
public class DictManageServlet extends HttpServlet {

    private final DictService dictService = new DictServiceImpl();

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
            case "/query":
                findById(request, response);
                break;
            case "/update":
                updateModel(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void findById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Dict dict = dictService.queryById(id);
        AjaxResult.ok().data(dict).write(response);
    }

    private void updateModel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Dict dict = RequestUtil.parseJson(request, Dict.class);
        AjaxResult.auto(dictService.update(dict)).write(response);
    }


}

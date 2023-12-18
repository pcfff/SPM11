package com.example.teachingplatform.servlet.teacher;

import com.example.teachingplatform.dto.PageResult;
import com.example.teachingplatform.dto.StudentDTO;
import com.example.teachingplatform.entity.Student;
import com.example.teachingplatform.entity.User;
import com.example.teachingplatform.enums.RoleEnums;
import com.example.teachingplatform.service.StudentService;
import com.example.teachingplatform.service.UserService;
import com.example.teachingplatform.service.impl.StudentServiceImpl;
import com.example.teachingplatform.service.impl.UserServiceImpl;
import com.example.teachingplatform.util.AjaxResult;
import com.example.teachingplatform.util.RequestUtil;
import com.github.pagehelper.Page;
import com.google.protobuf.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/manage/student/*")
public class StudentManageServlet extends HttpServlet {

    private final StudentService studentService = new StudentServiceImpl();
    private final UserService userService = new UserServiceImpl();

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

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        Student student = new Student();
        student.setName(request.getParameter("name")); // or any other parameters you need

        Integer userId = RequestUtil.getUserId(request);
        User user = userService.getById(userId);
        Student queryWrapper = new Student();
        if (user.getRole().equals(RoleEnums.ROLE_TEACHER)){
            queryWrapper.setTeacherId(RequestUtil.getUserId(request));
        }
        queryWrapper.setName(student.getName());

        PageResult<StudentDTO> pageResult = studentService.getPage(new Page<>(pageNum, pageSize), queryWrapper);

        Map<String, Object> map = new HashMap<>(2);
        map.put("rows", pageResult.getData());
        map.put("count", pageResult.getTotal());

        AjaxResult.ok().data(map).write(response);
    }

    private void findById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        AjaxResult.ok().data("model", studentService.getById(id)).write(response);
    }

    private void insertModel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StudentDTO studentDTO = RequestUtil.parseJson(request, StudentDTO.class);
        studentDTO.setPassword(studentDTO.getPassword());
        Student student = new Student();
        User user = new User();
        try {
            BeanUtils.copyProperties(student, studentDTO);
            BeanUtils.copyProperties(user, studentDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        student.setTeacherId(RequestUtil.getUserId(request));
        user.setRole(RoleEnums.ROLE_STUDENT.getRole());
        user.setCreateTime(new Date());
        try {
            boolean save = userService.save(user);
            if (save) {
                student.setUserId(user.getId());
                try {
                    boolean save1 = studentService.save(student);
                    if (!save1) {
                        AjaxResult.error().message("添加失败").write(response);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    AjaxResult.error().message("学号已存在").write(response);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            AjaxResult.error().message("用户名已存在").write(response);
            return;
        }
        AjaxResult.ok().write(response);
    }

    private void removeModel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        AjaxResult.auto(studentService.removeById(id)).write(response);
    }

    private void updateModel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Student student = RequestUtil.parseJson(request, Student.class);
        AjaxResult.auto(studentService.updateById(student)).write(response);
    }
}

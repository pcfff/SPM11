package com.example.teachingplatform.servlet;


import com.example.teachingplatform.dto.JwtUser;
import com.example.teachingplatform.entity.Dict;
import com.example.teachingplatform.entity.User;
import com.example.teachingplatform.enums.RoleEnums;
import com.example.teachingplatform.service.DictService;
import com.example.teachingplatform.service.StudentService;
import com.example.teachingplatform.service.TeacherService;
import com.example.teachingplatform.service.UserService;
import com.example.teachingplatform.service.impl.DictServiceImpl;
import com.example.teachingplatform.service.impl.StudentServiceImpl;
import com.example.teachingplatform.service.impl.TeacherServiceImpl;
import com.example.teachingplatform.service.impl.UserServiceImpl;
import com.example.teachingplatform.util.AjaxResult;
import com.example.teachingplatform.util.JwtTokenUtils;
import com.example.teachingplatform.util.RequestUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.Objects;

/**
 * (User)表控制层
 *
 * @author 
 */
@WebServlet("/user/*")
public class UserServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    private final StudentService studentService = new StudentServiceImpl();

    private final TeacherService teacherService = new TeacherServiceImpl();

    private final DictService dictService = new DictServiceImpl();

    public void info(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer userId = RequestUtil.getUserId(request);
        User user = userService.getById(userId);
        if (Objects.equals(user.getRole(), RoleEnums.ROLE_STUDENT.getRole())){
            AjaxResult.ok().data("user", user).data("info", studentService.getById(userId)).write(response);
        }else if (Objects.equals(user.getRole(), RoleEnums.ROLE_TEACHER.getRole())){
            AjaxResult.ok().data("user", user).data("info", teacherService.getById(userId)).write(response);
        }
    }


    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = RequestUtil.parseJson(request, User.class);
        User loginUser = userService.login(user);
        if (loginUser!= null) {
            if (!loginUser.getRole().equals(RoleEnums.ROLE_ADMIN.getRole())){
                Dict dict = dictService.queryById("enable_login");
                if (dict.getDictValue().equals("0")){
                    AjaxResult.error().message("网站维护中，无法登录").write(response);
                    return;
                }
            }
            JwtUser jwtUser = new JwtUser(loginUser);
            String token = JwtTokenUtils.createToken(loginUser.getUsername(), loginUser.getId(), jwtUser.getRole());
            AjaxResult.ok().data("token", token).data("role", jwtUser.getRole()).write(response);
        } else {
            AjaxResult.error().message("账号或密码错误").write(response);
        }
    }

    public void updatePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = RequestUtil.parseJson(request, User.class);
        Integer userId = RequestUtil.getUserId(request);
        User updateUser = new User();
        updateUser.setPassword(user.getPassword());
        updateUser.setId(userId);
        boolean success = userService.update(updateUser);
        AjaxResult.auto(success).write(response);
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // 获取请求的路径信息
        if ("/info".equals(pathInfo)) {
            info(request, response);
        }else if ("/login".equals(pathInfo)) {
            login(request, response);
        } else if ("/update-pwd".equals(pathInfo)) {
            updatePwd(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 如果路径不存在，返回 404 错误
        }
    }
}

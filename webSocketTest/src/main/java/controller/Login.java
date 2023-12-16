package controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // session需要被显示的用到一次(相当于建立session) 不然会爆空指针异常 可能是服务器偷懒压根没建立session?
        request.getSession().setAttribute("666","asdf");
        String name = request.getParameter("name");
        List<String> userList = (List<String>)request.getServletContext().getAttribute("userList");
        if (userList == null) {
            userList = new ArrayList<>();
        }
        userList.add(name);
        request.getServletContext().setAttribute("userList",userList);
        request.getRequestDispatcher("/1.html").forward(request,response);
    }
}

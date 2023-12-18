package com.example.teachingplatform.dto;


import com.example.teachingplatform.entity.User;
import com.example.teachingplatform.enums.RoleEnums;
import org.apache.commons.collections.CollectionUtils;


import java.util.Collections;
import java.util.List;

public class JwtUser {


    private Integer id;
    private String username;
    private String password;
    private User user;

    private String role;



    public JwtUser() {
    }

    /**
     * 写一个能直接使用user创建jwtUser的构造器
     * @param user 用户对象
     */
    public JwtUser(User user) {
        this.user = user;
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        RoleEnums[] values = RoleEnums.values();
        for (RoleEnums role : values) {
            if (user.getRole().equals(role.getRole())){
                this.role = role.name();
                return;
            }
        }
    }


    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", phone='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

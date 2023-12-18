package com.example.teachingplatform.enums;

public enum RoleEnums {

    /**
     *
     */
    ROLE_STUDENT(1),

    /**
     *
     */
    ROLE_TEACHER(2),

    /**
     * 管理员
     */
    ROLE_ADMIN(3);

    private final Integer role;

    RoleEnums(Integer role) {
        this.role = role;
    }

    public Integer getRole() {
        return role;
    }

}

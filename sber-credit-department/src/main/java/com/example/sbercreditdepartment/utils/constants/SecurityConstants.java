package com.example.sbercreditdepartment.utils.constants;

import java.util.List;

public class SecurityConstants {

    private SecurityConstants() {

    }

    public static final List<String> RESOURCES_WHITE_LIST = List.of(
            "/resources/**",
            "/static/**l",
            "/js/**",
            "/css/**",
            "/"
    );

    public static final List<String> CREDITS_WHITE_LIST = List.of(
            "/credits",
            "/credits/{id}"
    );

    public static final List<String> CREDITS_PERMISSIONS_LIST = List.of(
            "/credits/{id}/request/new"
    );

    // TODO: добавить сюда эндпоинты на статистику
    public static final List<String> ADMIN_PERMISSIONS_LIST = List.of(
            "/credits/add",
            "/requests/overview/betweenTwoDates",
            "/creditContracts/overview/betweenTwoDates"
    );

    public static final List<String> MANAGER_PERMISSIONS_LIST = List.of(

    );

    public static final List<String> USER_PERMISSIONS_LIST = List.of(
        "/users/profile"
    );

    public static final List<String> USER_WHITE_LIST = List.of(
            "/login",
            "/users/registration",
            "/users/remember-password/"
    );
}

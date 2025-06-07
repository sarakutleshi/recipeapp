package dev.sarakutleshi.recipeapp.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    MANAGER_READ("manager:read"),
    MANAGER_WRITE("manager:write");

    private final String permission;
}
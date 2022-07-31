package ru.job4j.forum.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

public class Role implements GrantedAuthority {
    private int id;
    private String name;

    public static Role of(int id, String name) {
        Role role = new Role();
        role.id = id;
        role.name = name;
        return role;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

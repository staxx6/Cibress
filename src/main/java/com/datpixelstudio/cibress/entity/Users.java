package com.datpixelstudio.cibress.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Users {
    private String username;
    private String password;
    private byte enabled;
    private Collection<DayEntry> dayEntriesByUsername;

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "enabled")
    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return enabled == users.enabled &&
                Objects.equals(username, users.username) &&
                Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, enabled);
    }

    @OneToMany(mappedBy = "usersByUsername")
    public Collection<DayEntry> getDayEntriesByUsername() {
        return dayEntriesByUsername;
    }

    public void setDayEntriesByUsername(Collection<DayEntry> dayEntriesByUsername) {
        this.dayEntriesByUsername = dayEntriesByUsername;
    }
}

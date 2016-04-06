package com.github.lavenderx.model.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created on 2016-01-18.
 *
 * @author dolphineor
 */
@Table(name = "USER")
public class UserEntity extends AbstractId {

    @Column(columnDefinition = "VARCHAR(50)", unique = true, nullable = false)
    private String username;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String password;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean state;

    @Column(columnDefinition = "DATE")
    private Date birthday;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String email;


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

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

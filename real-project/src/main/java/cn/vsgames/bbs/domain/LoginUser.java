package cn.vsgames.bbs.domain;

import java.io.Serializable;

public class LoginUser implements Serializable {

    private static final long serialVersionUID = -4006794753824335207L;

    private int               id;
    private String            username;
    private String            avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

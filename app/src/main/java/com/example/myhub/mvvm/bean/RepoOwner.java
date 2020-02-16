package com.example.myhub.mvvm.bean;

/**
 * Time:2020/1/27 18:21
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class RepoOwner {

    /**
     * login : flutter
     * id : 14101776
     * node_id : MDEyOk9yZ2FuaXphdGlvbjE0MTAxNzc2
     * avatar_url : https://avatars3.githubusercontent.com/u/14101776?v=4
     * gravatar_id :
     * url : https://api.github.com/users/flutter
     */

    private String login;
    private int id;
    private String node_id;
    private String avatar_url;
    private String gravatar_id;
    private String url;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

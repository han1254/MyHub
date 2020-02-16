package com.example.myhub.mvvm.bean;

import com.example.base.recyclerview_databinding.adapter.listadapter.IDiffUtil;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Time:2020/2/14 15:19
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class GitHubEvent implements IDiffUtil<GitHubEvent> {

    /**
     * id : 11517123258
     * type : CreateEvent
     * actor : {"id":61041581,"login":"Salmania27","display_login":"Salmania27","gravatar_id":"","url":"https://api.github.com/users/Salmania27","avatar_url":"https://avatars.githubusercontent.com/u/61041581?"}
     * created_at : 2020-02-14T07:29:42Z
     */

    private String id;
    private String type;
    private ActorBean actor;
    @SerializedName("created_at")
    private Date created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ActorBean getActor() {
        return actor;
    }

    public void setActor(ActorBean actor) {
        this.actor = actor;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean areItemSame(GitHubEvent gitHubEvent) {
        return this.id.equals(gitHubEvent.getId());
    }

    @Override
    public boolean areContentSame(GitHubEvent gitHubEvent) {
       return this.id.equals(gitHubEvent.getId());
    }

    public static class ActorBean {
        /**
         * id : 61041581
         * login : Salmania27
         * display_login : Salmania27
         * gravatar_id :
         * url : https://api.github.com/users/Salmania27
         * avatar_url : https://avatars.githubusercontent.com/u/61041581?
         */

        private int id;
        private String login;
        private String display_login;
        private String gravatar_id;
        private String url;
        private String avatar_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getDisplay_login() {
            return display_login;
        }

        public void setDisplay_login(String display_login) {
            this.display_login = display_login;
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

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }
}

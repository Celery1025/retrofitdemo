package com.example.retrofitdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xieshuilin on 2017/2/24.
 */

public class PublicData {


    /**
     * url : https://api.github.com/gists/2c1536cb58f54b1656c2cbe5d2a00240
     * forks_url : https://api.github.com/gists/2c1536cb58f54b1656c2cbe5d2a00240/forks
     * commits_url : https://api.github.com/gists/2c1536cb58f54b1656c2cbe5d2a00240/commits
     * id : 2c1536cb58f54b1656c2cbe5d2a00240
     * git_pull_url : https://gist.github.com/2c1536cb58f54b1656c2cbe5d2a00240.git
     * git_push_url : https://gist.github.com/2c1536cb58f54b1656c2cbe5d2a00240.git
     * html_url : https://gist.github.com/2c1536cb58f54b1656c2cbe5d2a00240
     * files : {"git_toturial":{"filename":"git_toturial","type":"text/plain","language":null,"raw_url":"https://gist.githubusercontent.com/lxp561784/2c1536cb58f54b1656c2cbe5d2a00240/raw/f93837afca37753975cd2fc09e55ddbc5e2874c5/git_toturial","size":7386}}
     * public : true
     * created_at : 2017-02-24T03:22:25Z
     * updated_at : 2017-02-24T03:22:25Z
     * description : git命令大全
     * comments : 0
     * user : null
     * comments_url : https://api.github.com/gists/2c1536cb58f54b1656c2cbe5d2a00240/comments
     * owner : {"login":"lxp561784","id":3021651,"avatar_url":"https://avatars.githubusercontent.com/u/3021651?v=3","gravatar_id":"","url":"https://api.github.com/users/lxp561784","html_url":"https://github.com/lxp561784","followers_url":"https://api.github.com/users/lxp561784/followers","following_url":"https://api.github.com/users/lxp561784/following{/other_user}","gists_url":"https://api.github.com/users/lxp561784/gists{/gist_id}","starred_url":"https://api.github.com/users/lxp561784/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/lxp561784/subscriptions","organizations_url":"https://api.github.com/users/lxp561784/orgs","repos_url":"https://api.github.com/users/lxp561784/repos","events_url":"https://api.github.com/users/lxp561784/events{/privacy}","received_events_url":"https://api.github.com/users/lxp561784/received_events","type":"User","site_admin":false}
     * truncated : false
     */

    private String url;
    private String forks_url;
    private String commits_url;
    private String id;
    private String git_pull_url;
    private String git_push_url;
    private String html_url;
    private FilesBean files;
    @SerializedName("public")
    private boolean publicX;
    private String created_at;
    private String updated_at;
    private String description;
    private int comments;
    private Object user;
    private String comments_url;
    private OwnerBean owner;
    private boolean truncated;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getForks_url() {
        return forks_url;
    }

    public void setForks_url(String forks_url) {
        this.forks_url = forks_url;
    }

    public String getCommits_url() {
        return commits_url;
    }

    public void setCommits_url(String commits_url) {
        this.commits_url = commits_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGit_pull_url() {
        return git_pull_url;
    }

    public void setGit_pull_url(String git_pull_url) {
        this.git_pull_url = git_pull_url;
    }

    public String getGit_push_url() {
        return git_push_url;
    }

    public void setGit_push_url(String git_push_url) {
        this.git_push_url = git_push_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public FilesBean getFiles() {
        return files;
    }

    public void setFiles(FilesBean files) {
        this.files = files;
    }

    public boolean isPublicX() {
        return publicX;
    }

    public void setPublicX(boolean publicX) {
        this.publicX = publicX;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public OwnerBean getOwner() {
        return owner;
    }

    public void setOwner(OwnerBean owner) {
        this.owner = owner;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public static class FilesBean {
        /**
         * git_toturial : {"filename":"git_toturial","type":"text/plain","language":null,"raw_url":"https://gist.githubusercontent.com/lxp561784/2c1536cb58f54b1656c2cbe5d2a00240/raw/f93837afca37753975cd2fc09e55ddbc5e2874c5/git_toturial","size":7386}
         */

        private GitToturialBean git_toturial;

        public GitToturialBean getGit_toturial() {
            return git_toturial;
        }

        public void setGit_toturial(GitToturialBean git_toturial) {
            this.git_toturial = git_toturial;
        }

        public static class GitToturialBean {
            /**
             * filename : git_toturial
             * type : text/plain
             * language : null
             * raw_url : https://gist.githubusercontent.com/lxp561784/2c1536cb58f54b1656c2cbe5d2a00240/raw/f93837afca37753975cd2fc09e55ddbc5e2874c5/git_toturial
             * size : 7386
             */

            private String filename;
            private String type;
            private Object language;
            private String raw_url;
            private int size;

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getLanguage() {
                return language;
            }

            public void setLanguage(Object language) {
                this.language = language;
            }

            public String getRaw_url() {
                return raw_url;
            }

            public void setRaw_url(String raw_url) {
                this.raw_url = raw_url;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }
        }
    }

    public static class OwnerBean {
        /**
         * login : lxp561784
         * id : 3021651
         * avatar_url : https://avatars.githubusercontent.com/u/3021651?v=3
         * gravatar_id :
         * url : https://api.github.com/users/lxp561784
         * html_url : https://github.com/lxp561784
         * followers_url : https://api.github.com/users/lxp561784/followers
         * following_url : https://api.github.com/users/lxp561784/following{/other_user}
         * gists_url : https://api.github.com/users/lxp561784/gists{/gist_id}
         * starred_url : https://api.github.com/users/lxp561784/starred{/owner}{/repo}
         * subscriptions_url : https://api.github.com/users/lxp561784/subscriptions
         * organizations_url : https://api.github.com/users/lxp561784/orgs
         * repos_url : https://api.github.com/users/lxp561784/repos
         * events_url : https://api.github.com/users/lxp561784/events{/privacy}
         * received_events_url : https://api.github.com/users/lxp561784/received_events
         * type : User
         * site_admin : false
         */

        private String login;
        private int id;
        private String avatar_url;
        private String gravatar_id;
        private String url;
        private String html_url;
        private String followers_url;
        private String following_url;
        private String gists_url;
        private String starred_url;
        private String subscriptions_url;
        private String organizations_url;
        private String repos_url;
        private String events_url;
        private String received_events_url;
        private String type;
        private boolean site_admin;

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

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public String getFollowers_url() {
            return followers_url;
        }

        public void setFollowers_url(String followers_url) {
            this.followers_url = followers_url;
        }

        public String getFollowing_url() {
            return following_url;
        }

        public void setFollowing_url(String following_url) {
            this.following_url = following_url;
        }

        public String getGists_url() {
            return gists_url;
        }

        public void setGists_url(String gists_url) {
            this.gists_url = gists_url;
        }

        public String getStarred_url() {
            return starred_url;
        }

        public void setStarred_url(String starred_url) {
            this.starred_url = starred_url;
        }

        public String getSubscriptions_url() {
            return subscriptions_url;
        }

        public void setSubscriptions_url(String subscriptions_url) {
            this.subscriptions_url = subscriptions_url;
        }

        public String getOrganizations_url() {
            return organizations_url;
        }

        public void setOrganizations_url(String organizations_url) {
            this.organizations_url = organizations_url;
        }

        public String getRepos_url() {
            return repos_url;
        }

        public void setRepos_url(String repos_url) {
            this.repos_url = repos_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
        }

        public String getReceived_events_url() {
            return received_events_url;
        }

        public void setReceived_events_url(String received_events_url) {
            this.received_events_url = received_events_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isSite_admin() {
            return site_admin;
        }

        public void setSite_admin(boolean site_admin) {
            this.site_admin = site_admin;
        }
    }
}

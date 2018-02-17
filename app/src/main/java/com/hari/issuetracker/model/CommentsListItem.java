package com.hari.issuetracker.model;

/**
 * Created by sivaavatar on 2/17/2018.
 */

public class CommentsListItem {
    private String id;

    private String issue_url;

    private String html_url;

    private String body;

    private String updated_at;

    private String author_association;

    private String created_at;

    private User user;

    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssue_url() {
        return issue_url;
    }

    public void setIssue_url(String issue_url) {
        this.issue_url = issue_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getAuthor_association() {
        return author_association;
    }

    public void setAuthor_association(String author_association) {
        this.author_association = author_association;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", issue_url = " + issue_url + ", html_url = " + html_url + ", body = " + body + ", updated_at = " + updated_at + ", author_association = " + author_association + ", created_at = " + created_at + ", user = " + user + ", url = " + url + "]";
    }
}
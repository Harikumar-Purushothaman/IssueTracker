package com.hari.issuetracker.model;

/**
 * Created by sivaavatar on 2/17/2018.
 */

public class IssueListItem {
    private String body;

    private String closed_at;

    private String[] assignees;

    private String[] labels;

    private String state;

    private String author_association;

    private String assignee;

    private String number;

    private String url;

    private String milestone;

    private String html_url;

    private String id;

    private String title;

    private String updated_at;

    private String repository_url;

    private Pull_request pull_request;

    private String comments_url;

    private String created_at;

    private String events_url;

    private String labels_url;

    private String locked;

    private User user;

    private String comments;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String

    getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    public String[] getAssignees() {
        return assignees;
    }

    public void setAssignees(String[] assignees) {
        this.assignees = assignees;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAuthor_association() {
        return author_association;
    }

    public void setAuthor_association(String author_association) {
        this.author_association = author_association;
    }

    public String

    getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String

    getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getRepository_url() {
        return repository_url;
    }

    public void setRepository_url(String repository_url) {
        this.repository_url = repository_url;
    }

    public Pull_request getPull_request() {
        return pull_request;
    }

    public void setPull_request(Pull_request pull_request) {
        this.pull_request = pull_request;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getLabels_url() {
        return labels_url;
    }

    public void setLabels_url(String labels_url) {
        this.labels_url = labels_url;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ClassPojo [body = " + body + ", closed_at = " + closed_at + ", assignees = " + assignees + ", labels = " + labels + ", state = " + state + ", author_association = " + author_association + ", assignee = " + assignee + ", number = " + number + ", url = " + url + ", milestone = " + milestone + ", html_url = " + html_url + ", id = " + id + ", title = " + title + ", updated_at = " + updated_at + ", repository_url = " + repository_url + ", pull_request = " + pull_request + ", comments_url = " + comments_url + ", created_at = " + created_at + ", events_url = " + events_url + ", labels_url = " + labels_url + ", locked = " + locked + ", user = " + user + ", comments = " + comments + "]";
    }
}
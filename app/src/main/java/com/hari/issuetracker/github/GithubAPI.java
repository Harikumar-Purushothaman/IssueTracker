package com.hari.issuetracker.github;

import com.hari.issuetracker.model.CommentsListItem;
import com.hari.issuetracker.model.IssueListItem;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GithubAPI {
    String ENDPOINT = "https://api.github.com/repos/crashlytics/secureudid/";
    String ISSUES = "issues";
    String COMMENTS = "comments";
    String SLASH = "/";

    @GET(ISSUES + SLASH + "{url}" + SLASH + COMMENTS)
    Single<List<CommentsListItem>> getComments(@Path("url") String url);

    @GET(ISSUES)
    Single<List<IssueListItem>> getIssues();
}

package com.hari.issuetracker.github;

import com.hari.issuetracker.model.CommentsListItem;
import com.hari.issuetracker.model.IssueListItem;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GithubAPI {
    String ENDPOINT = "https://api.github.com/repos/crashlytics/secureudid/";

    @GET("{url}")
    Single<List<CommentsListItem>> getComments(@Url String url);

    @GET("issues")
    Single<List<IssueListItem>> getIssues();
}

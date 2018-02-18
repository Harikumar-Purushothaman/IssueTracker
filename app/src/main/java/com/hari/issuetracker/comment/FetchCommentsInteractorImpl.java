package com.hari.issuetracker.comment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hari.issuetracker.deserialize.GithubCommentListDeserializer;
import com.hari.issuetracker.deserialize.GithubIssueListDeserializer;
import com.hari.issuetracker.github.GithubAPI;
import com.hari.issuetracker.main.FetchIssuesInteractor;
import com.hari.issuetracker.model.CommentsListItem;
import com.hari.issuetracker.model.IssueListItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sivaavatar on 2/17/2018.
 */

public class FetchCommentsInteractorImpl implements FetchCommentsInteractor {
    private CompositeDisposable compositeDisposable;
    private OnFinishedListener listener;
    GithubAPI githubAPI;

    public FetchCommentsInteractorImpl(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
        createGithubAPI();
    }

    private void createGithubAPI() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .registerTypeAdapter(IssueListItem.class, new GithubCommentListDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubAPI.ENDPOINT)
                .client(new OkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        githubAPI = retrofit.create(GithubAPI.class);
    }

    @Override
    public void fetchComments(final OnFinishedListener listener, String url) {
        this.listener = listener;
        url = (url.replace(GithubAPI.ENDPOINT, ""));
        url = (url.replace(GithubAPI.ISSUES, ""));
        url = (url.replace(GithubAPI.COMMENTS, ""));
        url = (url.replace(GithubAPI.SLASH, ""));
        compositeDisposable.add(githubAPI.getComments(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getIssuesObserver()));
    }

    private DisposableSingleObserver<List<CommentsListItem>> getIssuesObserver() {
        return new DisposableSingleObserver<List<CommentsListItem>>() {
            @Override
            public void onSuccess(List<CommentsListItem> value) {
                if (!value.isEmpty()) {
                    listener.onFinished(value);
                } else {
                    listener.onFinished(new ArrayList<CommentsListItem>());
                }
            }

            @Override
            public void onError(Throwable e) {
                listener.onFinished(new ArrayList<CommentsListItem>());
            }
        };
    }
}

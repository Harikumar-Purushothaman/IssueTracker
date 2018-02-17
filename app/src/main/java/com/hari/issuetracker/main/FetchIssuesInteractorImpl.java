package com.hari.issuetracker.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hari.issuetracker.deserialize.GithubIssueListDeserializer;
import com.hari.issuetracker.github.GithubAPI;
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

public class FetchIssuesInteractorImpl implements FetchIssuesInteractor {
    private CompositeDisposable compositeDisposable;
    private OnFinishedListener listener;
    GithubAPI githubAPI;

    public FetchIssuesInteractorImpl(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
        createGithubAPI();
    }

    private void createGithubAPI() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .registerTypeAdapter(IssueListItem.class, new GithubIssueListDeserializer())
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
    public void fetchIssues(final OnFinishedListener listener) {
        this.listener = listener;
        compositeDisposable.add(githubAPI.getIssues()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getIssuesObserver()));
    }

    private DisposableSingleObserver<List<IssueListItem>> getIssuesObserver() {
        return new DisposableSingleObserver<List<IssueListItem>>() {
            @Override
            public void onSuccess(List<IssueListItem> value) {
                if (!value.isEmpty()) {
                    listener.onFinished(value);
                } else {
                    listener.onFinished(new ArrayList<IssueListItem>());
                }
            }

            @Override
            public void onError(Throwable e) {
                listener.onFinished(new ArrayList<IssueListItem>());
            }
        };
    }
}

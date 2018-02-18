package com.hari.issuetracker.comment;

import com.hari.issuetracker.main.FetchIssuesInteractor;
import com.hari.issuetracker.main.MainPresenter;
import com.hari.issuetracker.main.MainView;
import com.hari.issuetracker.model.CommentsListItem;
import com.hari.issuetracker.model.IssueListItem;

import java.util.List;

/**
 * Created by sivaavatar on 2/17/2018.
 */

public class CommentsPresenterImpl implements CommentsPresenter, FetchCommentsInteractor.OnFinishedListener {

    private CommentsView commentsView;
    private FetchCommentsInteractor fetchCommentsInteractor;
    private String url;
    private List<CommentsListItem> items;

    public CommentsPresenterImpl(CommentsView commentsView, FetchCommentsInteractor fetchCommentsInteractor, String url) {
        this.commentsView = commentsView;
        this.fetchCommentsInteractor = fetchCommentsInteractor;
        this.url = url;
    }

    @Override
    public void onResume() {
        if (commentsView != null) {
            commentsView.showProgress();
        }
        fetchCommentsInteractor.fetchComments(this, url);
    }

    @Override
    public void onDestroy() {
        commentsView = null;
    }

    @Override
    public void onFinished(List<CommentsListItem> items) {
        this.items = items;
        if (commentsView != null) {
            commentsView.setItems(items);
            commentsView.hideProgress();
        }
    }

    public CommentsView getCommentsView() {
        return commentsView;
    }
}
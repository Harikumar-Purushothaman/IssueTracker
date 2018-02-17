package com.hari.issuetracker.main;

import com.hari.issuetracker.model.IssueListItem;

import java.util.List;

/**
 * Created by sivaavatar on 2/17/2018.
 */

public class MainPresenterImpl implements MainPresenter, FetchIssuesInteractor.OnFinishedListener {

    private MainView mainView;
    private FetchIssuesInteractor fetchIssuesInteractor;
    private List<IssueListItem> items;

    public MainPresenterImpl(MainView mainView, FetchIssuesInteractor fetchIssuesInteractor) {
        this.mainView = mainView;
        this.fetchIssuesInteractor = fetchIssuesInteractor;
    }

    @Override
    public void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }

        fetchIssuesInteractor.fetchIssues(this);
    }

    @Override
    public void onItemClicked(int position) {
        if (mainView != null) {
            mainView.showMessage(String.format("Position %d clicked", position + 1));
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onFinished(List<IssueListItem> items) {
        this.items = items;
        if (mainView != null) {
            mainView.setItems(items);
            mainView.hideProgress();
        }
    }

    public MainView getMainView() {
        return mainView;
    }
}
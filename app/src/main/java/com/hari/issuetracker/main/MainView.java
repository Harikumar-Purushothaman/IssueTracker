package com.hari.issuetracker.main;

import com.hari.issuetracker.model.IssueListItem;

import java.util.List;

/**
 * Created by sivaavatar on 2/17/2018.
 */

public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(List<IssueListItem> items);

    void showMessage(String message);

    void showComments(String url);
}

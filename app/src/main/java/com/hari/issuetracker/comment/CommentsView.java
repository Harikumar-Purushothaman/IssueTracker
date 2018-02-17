package com.hari.issuetracker.comment;

import com.hari.issuetracker.model.CommentsListItem;
import com.hari.issuetracker.model.IssueListItem;

import java.util.List;

/**
 * Created by sivaavatar on 2/17/2018.
 */

public interface CommentsView {

    void showProgress();

    void hideProgress();

    void setItems(List<CommentsListItem> items);

    void showMessage(String message);
}

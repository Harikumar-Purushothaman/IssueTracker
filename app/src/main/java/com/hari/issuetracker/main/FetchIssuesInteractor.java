package com.hari.issuetracker.main;

import com.hari.issuetracker.model.IssueListItem;

import java.util.List;

/**
 * Created by sivaavatar on 2/17/2018.
 */

public interface FetchIssuesInteractor {
    interface OnFinishedListener {
        void onFinished(List<IssueListItem> issues);
    }

    void fetchIssues(OnFinishedListener listener);
}

package com.hari.issuetracker.comment;

import com.hari.issuetracker.model.CommentsListItem;
import com.hari.issuetracker.model.IssueListItem;

import java.util.List;

/**
 * Created by sivaavatar on 2/17/2018.
 */

public interface FetchCommentsInteractor {
    interface OnFinishedListener {
        void onFinished(List<CommentsListItem> issues);
    }

    void fetchComments(OnFinishedListener listener);
}

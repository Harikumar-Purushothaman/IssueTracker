package com.hari.issuetracker.comment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hari.issuetracker.R;
import com.hari.issuetracker.adapter.MyAdapter;
import com.hari.issuetracker.adapter.MyCommentsAdapter;
import com.hari.issuetracker.common.CommonActivity;
import com.hari.issuetracker.listener.RecyclerItemClickListener;
import com.hari.issuetracker.main.FetchIssuesInteractorImpl;
import com.hari.issuetracker.main.MainPresenter;
import com.hari.issuetracker.main.MainPresenterImpl;
import com.hari.issuetracker.main.MainView;
import com.hari.issuetracker.model.CommentsListItem;
import com.hari.issuetracker.model.IssueListItem;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class CommentsActivity extends CommonActivity implements CommentsView {
    private RecyclerView recyclerView;
    private LinearLayout progressBar, nodata;
    private TextView nodatatxt;
    private CommentsPresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private Context mContext;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String url;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isSwipeRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        setUpViews();
        checkNetwork();
    }

    private void setUpViews() {
        mContext = this;
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutComments);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        progressBar = (LinearLayout) findViewById(R.id.progress);
        nodata = (LinearLayout) findViewById(R.id.nodatacomments);
        nodatatxt = (TextView) findViewById(R.id.nodatatxt);
        url = getIntent().getStringExtra("COMMENTS_URL");
        presenter = new CommentsPresenterImpl(this, new FetchCommentsInteractorImpl(compositeDisposable), url);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isSwipeRefresh = true;
                checkNetwork();
            }
        });
    }

    private void checkNetwork() {

        // Send request every single time when there is network
        if (isNetworkEnabled(this)) {
            presenter.onResume();
        } else {
            //Check for network availability
            retrieveNetworkCheck();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle("Comments");
    }

    @Override
    public void showProgress() {
        if (isSwipeRefresh) {
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        nodata.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        nodata.setVisibility(View.GONE);
    }

    @Override
    public void setItems(List<CommentsListItem> items) {
        hideProgress();
        if (items.size() > 0) {
            recyclerView.setAdapter(new MyCommentsAdapter(items, mContext));
        } else {
            showMessage("No comments available");
        }
    }

    @Override
    public void showMessage(final String message) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        nodata.setVisibility(View.VISIBLE);
        nodatatxt.setText(message);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

}

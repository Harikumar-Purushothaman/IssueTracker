package com.hari.issuetracker.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hari.issuetracker.R;
import com.hari.issuetracker.adapter.MyAdapter;
import com.hari.issuetracker.comment.CommentsActivity;
import com.hari.issuetracker.common.CommonActivity;
import com.hari.issuetracker.listener.RecyclerItemClickListener;
import com.hari.issuetracker.model.IssueListItem;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends CommonActivity implements MainView {
    private RecyclerView recyclerView;
    private LinearLayout progressBar, nodata;
    private TextView nodatatxt;
    private MainPresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private Context mContext;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isSwipeRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        presenter.onItemClicked(position);
                    }
                })
        );
        progressBar = (LinearLayout) findViewById(R.id.progress);
        nodata = (LinearLayout) findViewById(R.id.nodata);
        nodatatxt = (TextView) findViewById(R.id.nodatatxt);
        presenter = new MainPresenterImpl(this, new FetchIssuesInteractorImpl(compositeDisposable), mContext);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isSwipeRefresh = true;
                checkNetwork();
            }
        });
        checkNetwork();
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
        setTitle(getString(R.string.app_name));
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
    public void setItems(List<IssueListItem> items) {
        hideProgress();
        if (items.size() > 0) {
            recyclerView.setAdapter(new MyAdapter(items, mContext));
        } else {
            showMessage("No issues available");
        }
    }

    @Override
    public void showMessage(String message) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        nodata.setVisibility(View.VISIBLE);
        nodatatxt.setText(message);
    }

    @Override
    public void showComments(String url) {
        Intent i = new Intent(mContext, CommentsActivity.class);
        i.putExtra("COMMENTS_URL", url);
        startActivity(i);
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

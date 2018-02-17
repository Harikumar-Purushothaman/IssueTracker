package com.hari.issuetracker.comment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hari.issuetracker.R;
import com.hari.issuetracker.adapter.MyAdapter;
import com.hari.issuetracker.adapter.MyCommentsAdapter;
import com.hari.issuetracker.listener.RecyclerItemClickListener;
import com.hari.issuetracker.main.FetchIssuesInteractorImpl;
import com.hari.issuetracker.main.MainPresenter;
import com.hari.issuetracker.main.MainPresenterImpl;
import com.hari.issuetracker.main.MainView;
import com.hari.issuetracker.model.CommentsListItem;
import com.hari.issuetracker.model.IssueListItem;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class CommentsActivity extends AppCompatActivity implements CommentsView {
    private RecyclerView recyclerView;
    private LinearLayout progressBar;
    private CommentsPresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private Context mContext;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        mContext = this;
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        progressBar = (LinearLayout) findViewById(R.id.progress);
        presenter = new CommentsPresenterImpl(this, new FetchCommentsInteractorImpl(compositeDisposable));
        presenter.onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<CommentsListItem> items) {
        hideProgress();
        if (items.size() > 0)
            recyclerView.setAdapter(new MyCommentsAdapter(items, mContext));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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
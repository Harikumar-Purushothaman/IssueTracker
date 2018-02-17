package com.hari.issuetracker.main;

/**
 * Created by sivaavatar on 2/17/2018.
 */

public interface MainPresenter {

    void onResume();

    void onItemClicked(int position);

    void onDestroy();
}

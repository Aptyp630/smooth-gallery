package com.alex_zaitsev.smoothgallery.feature.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alex_zaitsev.smoothgallery.R;
import com.alex_zaitsev.smoothgallery.feature.details.DetailsActivity;
import com.alex_zaitsev.smoothgallery.model.Photo;
import com.alex_zaitsev.smoothgallery.model.PhotoFeed;

public class MainActivity extends AppCompatActivity implements MainContractor.MainMvpView {

    private static final int SPAN_COUNT = 2;

    private MainMvpPresenterImpl presenter = new MainMvpPresenterImpl();
    private boolean isLoading;
    private int visibleThreshold = 6;
    private int lastVisibleItem, totalItemCount;

    private RecyclerView photoFeed;
    private PhotoFeedAdapter photoFeedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.attachView(this);

        photoFeed = (RecyclerView) findViewById(R.id.photo_feed);

        initRecyclerView();
        onLoadMore();
    }

    private void initRecyclerView() {
        final GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
        photoFeed.setLayoutManager(layoutManager);
        photoFeed.addItemDecoration(new GridItemDecorator(
                getResources().getDimensionPixelSize(R.dimen.photo_feed_padding), SPAN_COUNT));
        photoFeed.setAdapter(photoFeedAdapter = new PhotoFeedAdapter(this));
        photoFeed.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    onLoadMore();
                    isLoading = true;
                }
            }
        });
        photoFeedAdapter.setOnItemClickListener(new OnGridItemClickListener<Photo>() {

            @Override
            public void onItemClicked(Photo item, int position) {
                startActivity(DetailsActivity.newIntent(MainActivity.this, item));
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onLoadMore() {
        if (!isLoading) {
            // we should show progress here
            presenter.getPhotoFeed();
        }
    }

    @Override
    public void onNetworkException() {
        onLoadFinished();
        Snackbar.make(photoFeed, R.string.err_network, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestFailed(String reason) {
        onLoadFinished();
        Snackbar.make(photoFeed, reason, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestSuccess(PhotoFeed result) {
        photoFeedAdapter.addItems(result.getPhotoList());
        onLoadFinished();
    }

    private void onLoadFinished() {
        isLoading = false;
        // here we can hide progress which indicates loading
    }
}

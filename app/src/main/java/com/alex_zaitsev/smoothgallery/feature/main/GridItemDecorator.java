package com.alex_zaitsev.smoothgallery.feature.main;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Alex Zaitsev on 6/11/16.
 */
public class GridItemDecorator extends RecyclerView.ItemDecoration {

    private int space;
    private int spanCount;

    public GridItemDecorator(int space, int spanCount) {
        this.space = space;
        this.spanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        outRect.right = space;
        outRect.bottom = space;

        // Add top margin only for the first items to avoid double space between items
        outRect.top = position < spanCount ? space : 0;
    }
}

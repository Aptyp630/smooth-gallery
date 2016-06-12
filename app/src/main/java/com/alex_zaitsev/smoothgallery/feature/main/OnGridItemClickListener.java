package com.alex_zaitsev.smoothgallery.feature.main;

/**
 * Created by Alex Zaitsev on 6/12/16.
 */
public interface OnGridItemClickListener<T> {

    void onItemClicked(T item, int position);
}

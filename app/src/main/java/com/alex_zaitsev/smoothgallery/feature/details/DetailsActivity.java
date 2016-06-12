package com.alex_zaitsev.smoothgallery.feature.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alex_zaitsev.smoothgallery.R;
import com.alex_zaitsev.smoothgallery.model.Photo;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoView;

public class DetailsActivity extends AppCompatActivity {

    private static final String EXTRA_PHOTO = "EXTRA_PHOTO";

    private Photo photo;
    private PhotoView photoView;
    private TextView overlay;
    private FloatingActionButton share;

    public static Intent newIntent(Context context, Photo photo) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_PHOTO, photo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        photo = getIntent().getParcelableExtra(EXTRA_PHOTO);
        photoView = (PhotoView) findViewById(R.id.details_image);
        overlay = (TextView) findViewById(R.id.details_overlay);
        share = (FloatingActionButton) findViewById(R.id.details_share);
        Picasso.with(this).load(photo.getUrl()).into(photoView);
        addInfo();
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                // here we can customize sharing text (it should come from presenter)
                sendIntent.putExtra(Intent.EXTRA_TEXT, overlay.getText().toString() +
                        "\n" + getString(R.string.url) + ": " + photo.getUrl());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

    private void addInfo() {
        String info = getString(R.string.name) + ": " + photo.getName() +
                (TextUtils.isEmpty(photo.getCamera()) ? "" :
                        "\n" + getString(R.string.camera) + ": " + photo.getCamera()) +
                (photo.getAuthor() == null || TextUtils.isEmpty(photo.getAuthor().getFullName()) ? "" :
                        "\n" + getString(R.string.author) + ": " + photo.getAuthor().getFullName());
        overlay.setText(info);
    }
}

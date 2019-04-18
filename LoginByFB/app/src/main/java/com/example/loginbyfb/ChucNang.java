package com.example.loginbyfb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChucNang extends AppCompatActivity {

    @BindView(R.id.editTitle)
    EditText editTitle;

    @BindView(R.id.editdescribe)
    EditText editdescribe;

    @BindView(R.id.editLink)
    EditText editLink;

    @BindView(R.id.btnShareLink)
    Button btnShareLink;

    @BindView(R.id.imageHinh)
    ImageView imageHinh;

    @BindView(R.id.btnShareImage)
    Button btnShareImage;

    @BindView(R.id.videoView)
    VideoView videoView;

    @BindView(R.id.btnPickVideo)
    Button btnPickVideo;

    @BindView(R.id.btnShareVideo)
    Button btnShareVideo;

    private ShareDialog shareDialog;
    private ShareLinkContent shareLinkContent;
    private int SELECT_IMAGE = 1;
    private int PICK_VIDEO = 2;
    private Bitmap bitmap;
    private Uri uriVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuc_nang);
        ButterKnife.bind(this);
        shareDialog = new ShareDialog(ChucNang.this);

        buttonShareLink();
        PickImage();
        ButtonShareImage();
        ButtonPickVideo();
        ButtonShareVideo();
    }

    private void ButtonShareVideo() {
        btnShareVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareVideo shareVideo = null;
                shareVideo = new ShareVideo.Builder()
                        .setLocalUrl(uriVideo)
                        .build();
                ShareVideoContent content = new ShareVideoContent.Builder()
                        .setVideo(shareVideo)
                        .build();
                shareDialog.show(content);
                videoView.stopPlayback();
            }
        });
    }

    private void ButtonPickVideo() {
        btnPickVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("video/*");
                startActivityForResult(i,PICK_VIDEO);
            }
        });
    }

    private void ButtonShareImage() {
        btnShareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(bitmap)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                shareDialog.show(content);
            }
        });
    }

    private void PickImage() {
        imageHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,SELECT_IMAGE);
            }
        });
    }

    private void buttonShareLink() {
        btnShareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ShareDialog.canShow(ShareLinkContent.class)){
                    shareLinkContent = new ShareLinkContent.Builder()
                            .setContentTitle(editTitle.getText().toString())
                            .setContentDescription(editdescribe.getText().toString())
                            .setContentUrl(Uri.parse(editLink.getText().toString()))
                            .build();
                }
                shareDialog.show(shareLinkContent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_IMAGE && resultCode == RESULT_OK){
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageHinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == PICK_VIDEO && resultCode == RESULT_OK){
            uriVideo = data.getData();
            videoView.setVideoURI(uriVideo);
            videoView.start();
        }

    }
}

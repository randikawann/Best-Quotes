package com.xiteb.mortivationalquotes.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xiteb.mortivationalquotes.R;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageVIewActivity extends AppCompatActivity {

    String titletext;
    ImageView imgmailview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        getSupportActionBar().setTitle("Quotes");
//        String imgurl = getIntent().getStringExtra("imgeurl");
        int imgurlint = getIntent().getIntExtra("imgurlint2",R.drawable.pic1);
        titletext = getIntent().getStringExtra("titletext");
        Log.i("1234", "img value :"+imgurlint);

        imgmailview = findViewById(R.id.imgmailview);

        
//        tvmaintext.setText(titletext);

//        Glide.with(this)
//                .load(imgurl)
//                .centerCrop()
////                .placeholder(R.drawable.loading_spinner)
//                .into(imgmailview);

        imgmailview.setImageResource(imgurlint);

        //Bottom navigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view_image);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.action_copyitem:
//                        copytoclipboard();
//                        break;
                    case R.id.action_downloaditem:
                        saveImage();
                        break;
                    case R.id.action_shareitem:
                        shareImage();

                        break;
                }
                return true;
            }
        });
    }

    private void shareImage() {

        imgmailview.getDrawable();

        Bitmap bitmap = convertdrawabletobitmap();
        File root = new File(Environment.getExternalStorageDirectory(), "Quotes Downloads");

       if (!root.exists()) {
            root.mkdir();
        }
       File mypath = new File(root, "default.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            Log.e("1234", e.getMessage(), e);
        }

        //////////////////////Share part
        Intent shareIntent = new Intent(Intent.ACTION_SEND);

      File imageFileToShare = new File(root, "default.jpg");

        Uri phototUri;

        if (Build.VERSION.SDK_INT < 24) {
            phototUri = Uri.fromFile(imageFileToShare);
        } else {
            phototUri = Uri.parse(imageFileToShare.getPath()); // My work-around for new SDKs, causes ActivityNotFoundException in API 10.
        }

        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, phototUri);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Share Via"));



    }

    private void copytoclipboard() {
        Toast.makeText(ImageVIewActivity.this, "Copy to clip board", Toast.LENGTH_SHORT).show();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("copy value", titletext);
        clipboard.setPrimaryClip(clip);
    }

    public Bitmap convertdrawabletobitmap(){

        imgmailview.buildDrawingCache();
        Bitmap bmap = imgmailview.getDrawingCache();

        return bmap;

    }
    public void saveImage(){

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "IMG_" + timeStamp + ".jpg";

        imgmailview.getDrawable();

        Bitmap bitmap = convertdrawabletobitmap();
        File root = new File(Environment.getExternalStorageDirectory(), "Quotes Downloads");

//        ContextWrapper cw = new ContextWrapper(getApplicationContext());
//        File directory = cw.getDir("profile", Context.MODE_PRIVATE);
        if (!root.exists()) {
            root.mkdir();
        }

//        String datetimenameimage = getDateimage();
//        Log.i("1234", "date is "+datetimenameimage);

        File mypath = new File(root, imageName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            Toast.makeText(ImageVIewActivity.this, "Image Save to Quotes Dowloads", Toast.LENGTH_SHORT).show();
//            this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
//            this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.fromFile(root)));
        } catch (Exception e) {
            Log.e("1234", e.getMessage(), e);
        }
        //this is for save file in media storage

//        String path = mediaStorageDir.getPath() + File.separator
//                + "IMG_Some_name.jpg";
//        Log.i("1234","path"+ mypath);
        this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + mypath)));

    }
}

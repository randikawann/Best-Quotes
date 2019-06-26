package com.xiteb.mortivationalquotes.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmedadeltito.photoeditorsdk.OnPhotoEditorSDKListener;
import com.ahmedadeltito.photoeditorsdk.PhotoEditorSDK;
import com.ahmedadeltito.photoeditorsdk.ViewType;
import com.xiteb.mortivationalquotes.adapter.FontFaceAdapter;
import com.xiteb.mortivationalquotes.interfaces.FontFaceClick;
import com.xiteb.mortivationalquotes.R;
import com.xiteb.mortivationalquotes.interfaces.RecyclerImageClick;
import com.xiteb.mortivationalquotes.adapter.ColorPickerAdapter;
import com.xiteb.mortivationalquotes.adapter.ImageGaleryAdapter;
import com.xiteb.mortivationalquotes.adapter.PopupAdapter;
import com.xiteb.mortivationalquotes.widget.SlidingUpPanelLayout;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnPhotoEditorSDKListener, RecyclerImageClick, FontFaceClick {

    private final String TAG = "PhotoEditorActivity";
    private RelativeLayout parentImageRelativeLayout;
    private RecyclerView drawingViewColorPickerRecyclerView;
    private TextView undoTextView, undoTextTextView, doneDrawingTextView, eraseDrawingTextView;
    private SlidingUpPanelLayout mLayout;
    private Typeface emojiFont;
    private View topShadow;
    private RelativeLayout topShadowRelativeLayout;
    private View bottomShadow;
    private RelativeLayout bottomShadowRelativeLayout;
    private ArrayList<Integer> colorPickerColors;
    private ArrayList<Integer> galerypicker;
    private ArrayList<Typeface> fontfacepicker;
    private int colorCodeTextView = -1;
    private PhotoEditorSDK photoEditorSDK;
    public ImageView photoEditImageView;

    Typeface newFont;
    Typeface fontAlexBrush;
    Typeface fontAmaticSC;
    Typeface fontGeateVibes;
    Typeface fontLato;
    Typeface fontMontserrate;

    String maintextletter;
    int maintextcolor;
    Typeface maintextface;


    private static final int PERMISSION_REQUEST_CODE = 1;


    private RecyclerView backgroundrecyclerview;

    String maintextvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setTitle("Create Quotes");
        newFont = Typeface.createFromAsset(getAssets(), "Eventtus-Icons.ttf");

        fontAlexBrush = Typeface.createFromAsset(getAssets(), "AlexBrush-Regular.ttf");
        fontAmaticSC = Typeface.createFromAsset(getAssets(), "Amatic-Bold.ttf");
        fontGeateVibes = Typeface.createFromAsset(getAssets(), "GreatVibes-Regular.otf");
        fontLato = Typeface.createFromAsset(getAssets(), "Lato-Black.ttf");
        fontMontserrate = Typeface.createFromAsset(getAssets(), "Montserrat-Bold.otf");


        fontfacepicker = new ArrayList<Typeface>();
        fontfacepicker.add(newFont);
        fontfacepicker.add(fontAlexBrush);
        fontfacepicker.add(fontGeateVibes);
        fontfacepicker.add(fontLato);
        fontfacepicker.add(fontMontserrate);

        // Default text styles;
        maintextletter = "Click Here";
        maintextcolor = getResources().getColor(R.color.black);
        maintextface = newFont;



        backgroundrecyclerview = findViewById(R.id.backgroundrecyclerview);
        drawingViewColorPickerRecyclerView = (RecyclerView) findViewById(R.id.drawing_view_color_picker_recycler_view);
        parentImageRelativeLayout = (RelativeLayout) findViewById(R.id.parent_image_rl);
        RelativeLayout deleteRelativeLayout = (RelativeLayout) findViewById(R.id.delete_rl);
        TextView deleteTextView = (TextView) findViewById(R.id.delete_tv);
        doneDrawingTextView = (TextView) findViewById(R.id.done_drawing_tv);
        eraseDrawingTextView = (TextView) findViewById(R.id.erase_drawing_tv);
        photoEditImageView = (ImageView) findViewById(R.id.photo_edit_iv);
        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);


        deleteTextView.setTypeface(newFont);

        final List<Fragment> fragmentsList = new ArrayList<>();

        photoEditorSDK = new PhotoEditorSDK.PhotoEditorSDKBuilder(MainActivity.this)
                .parentView(parentImageRelativeLayout) // add parent image view
                .childView(photoEditImageView) // add the desired image view
                .deleteView(deleteRelativeLayout) // add the deleted view that will appear during the movement of the views
                .buildPhotoEditorSDK(); // build photo editor sdk
        photoEditorSDK.setOnPhotoEditorSDKListener(this);


        addText(maintextletter, maintextcolor, maintextface);



//        closeTextView.setOnClickListener(this);
//        addTextView.setOnClickListener(this);
//        addPencil.setOnClickListener(this);
//        saveTextView.setOnClickListener(this);
//        saveTextTextView.setOnClickListener(this);
//        undoTextView.setOnClickListener(this);
//        undoTextTextView.setOnClickListener(this);
        doneDrawingTextView.setOnClickListener(this);
        eraseDrawingTextView.setOnClickListener(this);
//        clearAllTextView.setOnClickListener(this);
//        clearAllTextTextView.setOnClickListener(this);
//        goToNextTextView.setOnClickListener(this);

        galerypicker = new ArrayList<>();
        galerypicker.add(R.drawable.a1);
        galerypicker.add(R.drawable.a2);
        galerypicker.add(R.drawable.a3);
        galerypicker.add(R.drawable.a4);
        galerypicker.add(R.drawable.a5);
        galerypicker.add(R.drawable.a6);



//        galerypicker = new int[]{R.drawable.above_shadow, R.drawable.below_shadow, R.drawable.below_shadow};
// later...
//        myImageView.setImageResource(myImageList[i]);




/*
        colorPickerColors = new ArrayList<>();
        colorPickerColors.add(getResources().getColor(R.color.black));
        colorPickerColors.add(getResources().getColor(R.color.blue_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.brown_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.green_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.orange_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.red_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.red_orange_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.sky_blue_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.violet_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.white));
        colorPickerColors.add(getResources().getColor(R.color.yellow_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.yellow_green_color_picker));
*/

        colorPickerColors = new ArrayList<>();
        colorPickerColors.add(getResources().getColor(R.color.color1));
        colorPickerColors.add(getResources().getColor(R.color.color2));
        colorPickerColors.add(getResources().getColor(R.color.color3));
        colorPickerColors.add(getResources().getColor(R.color.color4));
        colorPickerColors.add(getResources().getColor(R.color.color5));
        colorPickerColors.add(getResources().getColor(R.color.color6));
        colorPickerColors.add(getResources().getColor(R.color.color7));
        colorPickerColors.add(getResources().getColor(R.color.color8));
        colorPickerColors.add(getResources().getColor(R.color.color9));
        colorPickerColors.add(getResources().getColor(R.color.color10));
        colorPickerColors.add(getResources().getColor(R.color.color11));
        colorPickerColors.add(getResources().getColor(R.color.color12));
        colorPickerColors.add(getResources().getColor(R.color.color13));
        colorPickerColors.add(getResources().getColor(R.color.color14));
        colorPickerColors.add(getResources().getColor(R.color.color15));
        colorPickerColors.add(getResources().getColor(R.color.color16));
        colorPickerColors.add(getResources().getColor(R.color.color17));
        colorPickerColors.add(getResources().getColor(R.color.color18));
        colorPickerColors.add(getResources().getColor(R.color.color19));
        colorPickerColors.add(getResources().getColor(R.color.color20));
        colorPickerColors.add(getResources().getColor(R.color.color21));



        //Bottom navigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_quotes:
                        drawingViewColorPickerRecyclerView.setVisibility(View.INVISIBLE);
                        backgroundrecyclerview.setVisibility(View.INVISIBLE);
//                        addgaleryvalue();
                        openAddTextPopupWindow(maintextletter, -1);
                        break;
                    case R.id.action_fontcolor:
                        drawingViewColorPickerRecyclerView.setVisibility(View.VISIBLE);
                        backgroundrecyclerview.setVisibility(View.INVISIBLE);
                        fontcolorchange();
                        updateBrushDrawingView2(true);
                        break;
                    case R.id.action_fontface:
                        drawingViewColorPickerRecyclerView.setVisibility(View.INVISIBLE);
                        backgroundrecyclerview.setVisibility(View.VISIBLE);
                        fontfacechange();
                        break;
                    case R.id.action_background:
                        drawingViewColorPickerRecyclerView.setVisibility(View.VISIBLE);
                        backgroundrecyclerview.setVisibility(View.VISIBLE);
                        galleyimageselect();
                        updateBrushDrawingView(true);
                        break;
                }
                return true;
            }
        });

//        bottomNavigationView.setItemIconTintList();



    }







    private boolean stringIsNotEmpty(String string) {
        if (string != null && !string.equals("null")) {
            if (!string.trim().equals("")) {
                return true;
            }
        }
        return false;
    }

    private void addText(String text, int colorCodeTextView, Typeface fontface) {
        Log.i("1234", "Pop up text "+text+" "+colorCodeTextView+" "+fontface);
        photoEditorSDK.clearAllViews();
//        photoEditorSDK.addText(text, colorCodeTextView, newFont);

        photoEditorSDK.addText(text, colorCodeTextView, fontface);
    }

    private void clearAllViews() {
        photoEditorSDK.clearAllViews();
    }

    private void undoViews() {
        photoEditorSDK.viewUndo();
    }

    private void eraseDrawing() {
        photoEditorSDK.brushEraser();
    }

    private void openAddTextPopupWindow(String text, int colorCode) {
        if(text.equals("Click Here")){
            text = "";
        }

        colorCodeTextView = colorCode;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View addTextPopupWindowRootView = inflater.inflate(R.layout.add_text_popup_window, null);
        final EditText addTextEditText = (EditText) addTextPopupWindowRootView.findViewById(R.id.add_text_edit_text);
        TextView addTextDoneTextView = (TextView) addTextPopupWindowRootView.findViewById(R.id.add_text_done_tv);
        TextView add_text_galery = addTextPopupWindowRootView.findViewById(R.id.add_text_galery);
        RecyclerView addTextColorPickerRecyclerView = (RecyclerView) addTextPopupWindowRootView.findViewById(R.id.add_text_color_picker_recycler_view);
        addTextColorPickerRecyclerView.setVisibility(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        addTextColorPickerRecyclerView.setLayoutManager(layoutManager);
        addTextColorPickerRecyclerView.setHasFixedSize(true);


        ColorPickerAdapter colorPickerAdapter = new ColorPickerAdapter(MainActivity.this, colorPickerColors);
        colorPickerAdapter.setOnColorPickerClickListener(new ColorPickerAdapter.OnColorPickerClickListener() {
            @Override
            public void onColorPickerClickListener(int colorCode) {
                addTextEditText.setTextColor(colorCode);
//                addTextEditText.setTextColor(maintextcolor);
                colorCodeTextView = colorCode;
            }
        });
        addTextColorPickerRecyclerView.setAdapter(colorPickerAdapter);
        if (stringIsNotEmpty(text)) {
            addTextEditText.setText(text);
            addTextEditText.setTextColor(colorCode == -1 ? getResources().getColor(R.color.white) : colorCode);
        }
        ///

        //
        final PopupWindow pop = new PopupWindow(MainActivity.this);
        pop.setContentView(addTextPopupWindowRootView);
        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setFocusable(true);
        pop.setBackgroundDrawable(null);
        pop.showAtLocation(addTextPopupWindowRootView, Gravity.TOP, 0, 0);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        add_text_galery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
                addgaleryvalue();
            }
        });



        addTextDoneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                maintextcolor = colorCodeTextView;
                maintextletter = addTextEditText.getText().toString();
                addText(maintextletter, maintextcolor, maintextface);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                pop.dismiss();
            }
        });
    }

    private void updateView(int visibility) {
//        topShadow.setVisibility(visibility);
//        topShadowRelativeLayout.setVisibility(visibility);
//        bottomShadow.setVisibility(visibility);
//        bottomShadowRelativeLayout.setVisibility(visibility);
    }

    //Action bar option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.createtopmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            Log.i("1234", "Save button clicked");
            returnBackWithSavedImage();
            return true;
        }
        else if (id == R.id.action_share) {
            Log.i("1234", "send button clicked");
            returnBackWithShareImage();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fontcolorchange() {
//        if(maintextcolor == getResources().getColor(R.color.black)){
//            maintextcolor = getResources().getColor(R.color.red_color_picker);
//        }else{
//            maintextcolor = getResources().getColor(R.color.black);
//        }
        addText(maintextletter, maintextcolor, maintextface);

    }



    private void fontfacechange() {

//        if(maintextface == newFont){
//            maintextface = fontAlexBrush;
//        }else{
//            maintextface = newFont;
//        }
//        addText(maintextletter, maintextcolor, maintextface);

        updateView(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        backgroundrecyclerview.setLayoutManager(layoutManager);
        backgroundrecyclerview.setHasFixedSize(true);
        FontFaceAdapter fontFaceAdapter = new FontFaceAdapter(this, fontfacepicker);



        backgroundrecyclerview.setAdapter(fontFaceAdapter);

    }
    private void galleyimageselect(){
//        photoEditImageView.setImageResource(R.drawable.a3);
//        Log.i("1234", "1st resource : "+photoEditImageView.getResources().toString());
        updateView(View.GONE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        backgroundrecyclerview.setLayoutManager(layoutManager);
        backgroundrecyclerview.setHasFixedSize(true);
        ImageGaleryAdapter imageGaleryAdapter = new ImageGaleryAdapter(this, galerypicker);



        backgroundrecyclerview.setAdapter(imageGaleryAdapter);
//        backgroundrecyclerview



    }

    private void updateBrushDrawingView2(boolean brushDrawingMode) {

//        photoEditorSDK.setBrushDrawingMode(brushDrawingMode);
        if (brushDrawingMode) {
            updateView(View.GONE);
            drawingViewColorPickerRecyclerView.setVisibility(View.INVISIBLE);
//            doneDrawingTextView.setVisibility(View.VISIBLE);
//            eraseDrawingTextView.setVisibility(View.VISIBLE);
            backgroundrecyclerview.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
//            drawingViewColorPickerRecyclerView.setLayoutManager(layoutManager);
//            drawingViewColorPickerRecyclerView.setHasFixedSize(true);
            backgroundrecyclerview.setLayoutManager(layoutManager);
            backgroundrecyclerview.setHasFixedSize(true);
            ColorPickerAdapter colorPickerAdapter = new ColorPickerAdapter(MainActivity.this, colorPickerColors);
            colorPickerAdapter.setOnColorPickerClickListener(new ColorPickerAdapter.OnColorPickerClickListener() {
                @Override
                public void onColorPickerClickListener(int colorCode) {
                    photoEditorSDK.setBrushColor(colorCode);
//                    photoEditImageView.setBackgroundColor(getResources().getColor(R.color.blue_color_picker));
//                    photoEditImageView.setBackgroundColor(colorCode);

//                    Log.i("1234", "Color code :"+colorCode);
                    maintextcolor = colorCode;
                    addText(maintextletter, maintextcolor, maintextface);


                }
            });

//            drawingViewColorPickerRecyclerView.setAdapter(colorPickerAdapter);
            backgroundrecyclerview.setAdapter(colorPickerAdapter);
            //wedfgcvh


        } else {
            updateView(View.VISIBLE);
            drawingViewColorPickerRecyclerView.setVisibility(View.GONE);
            doneDrawingTextView.setVisibility(View.GONE);
            eraseDrawingTextView.setVisibility(View.GONE);
        }
    }


    private void updateBrushDrawingView(boolean brushDrawingMode) {

//        photoEditorSDK.setBrushDrawingMode(brushDrawingMode);
        if (brushDrawingMode) {
            updateView(View.GONE);
            drawingViewColorPickerRecyclerView.setVisibility(View.VISIBLE);
//            doneDrawingTextView.setVisibility(View.VISIBLE);
//            eraseDrawingTextView.setVisibility(View.VISIBLE);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
            drawingViewColorPickerRecyclerView.setLayoutManager(layoutManager);
            drawingViewColorPickerRecyclerView.setHasFixedSize(true);
            ColorPickerAdapter colorPickerAdapter = new ColorPickerAdapter(MainActivity.this, colorPickerColors);
            colorPickerAdapter.setOnColorPickerClickListener(new ColorPickerAdapter.OnColorPickerClickListener() {
                @Override
                public void onColorPickerClickListener(int colorCode) {
                    photoEditorSDK.setBrushColor(colorCode);
//                    photoEditImageView.setBackgroundColor(getResources().getColor(R.color.blue_color_picker));
                    photoEditImageView.setBackgroundColor(colorCode);


                }
            });

            drawingViewColorPickerRecyclerView.setAdapter(colorPickerAdapter);
            //wedfgcvh


        } else {
            updateView(View.VISIBLE);
            drawingViewColorPickerRecyclerView.setVisibility(View.GONE);
            doneDrawingTextView.setVisibility(View.GONE);
            eraseDrawingTextView.setVisibility(View.GONE);
        }
    }

    private void saveImage2(){
        updateView(View.GONE);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        parentImageRelativeLayout.setLayoutParams(layoutParams);
        new CountDownTimer(1000, 500) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageName = "IMG_" + timeStamp + ".png";
                Intent returnIntent = new Intent();
                returnIntent.putExtra("imagePath", photoEditorSDK.saveImage("PhotoEditorSDK", imageName));
                setResult(Activity.RESULT_OK, returnIntent);

//                finish();
                startActivity(new Intent(MainActivity.this, MainActivity.class));




            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Are you sure?")
                .setMessage("Are you sure you want to Exit?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(MainActivity.this, MenuActivity.class));
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("No", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    private void savenshareimage(){
        updateView(View.GONE);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        parentImageRelativeLayout.setLayoutParams(layoutParams);
        new CountDownTimer(1000, 500) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageName = "IMG_" + timeStamp + ".png";
                Intent returnIntent = new Intent();
                returnIntent.putExtra("imagePath", photoEditorSDK.saveshareImage("PhotoEditorSDK", imageName));
                setResult(Activity.RESULT_OK, returnIntent);


                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        finish();
                    }
                }, 3000);


            }
        }.start();
    }

    private void returnBackWithShareImage() {

        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission()) {
                savenshareimage();
            } else {
                requestPermission();
            }
        }
        else
        {
            requestPermission();
        }
    }


    private void returnBackWithSavedImage() {

        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission()) {
               saveImage2();

//                Toast.makeText(MainActivity.this, "Save image to picture", Toast.LENGTH_LONG).show();
//                Intent mainintent = new Intent(MainActivity.this, MainActivity.class);
//                startActivity(mainintent);

            } else {
                requestPermission();
            }
        }
        else
        {
            requestPermission();
        }
    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(MainActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }


    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.close_tv) {
//            onBackPressed();
//        }else if (v.getId() == R.id.add_text_tv) {
//            //for text layout
////            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
//            //text edit window
//            addgaleryvalue();
//
//        } else if (v.getId() == R.id.add_pencil_tv) {
//            updateBrushDrawingView(true);
//        }
        if (v.getId() == R.id.done_drawing_tv) {
            updateBrushDrawingView(false);
        }
//        else if (v.getId() == R.id.save_tv || v.getId() == R.id.save_text_tv) {
//            returnBackWithSavedImage();
//        } else if (v.getId() == R.id.clear_all_tv || v.getId() == R.id.clear_all_text_tv) {
//            clearAllViews();
//        } else if (v.getId() == R.id.undo_text_tv || v.getId() == R.id.undo_tv) {
//            undoViews();
//        }
        else if (v.getId() == R.id.erase_drawing_tv) {
            eraseDrawing();
        }
//        else if (v.getId() == R.id.go_to_next_screen_tv) {
//            returnBackWithSavedImage();
//        }
    }

    private void addgaleryvalue() {

        final String[] quotesname = {"Anything is good than nothing", "Your parent is the best friends", "Do Something what you can't do", "Build confident what you wan't to be"};
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.popuplayout);
        dialog.setTitle("Gallery text");
        dialog.setCancelable(true);

        ListView popuplistview = dialog.findViewById(R.id.popup_listVIew);
        PopupAdapter adapter = new PopupAdapter(this, quotesname);
        popuplistview.setAdapter(adapter);
        dialog.show();

        popuplistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("1234", quotesname[position]);
                dialog.dismiss();
                maintextvalue = quotesname[position];
                openAddTextPopupWindow(quotesname[position], -1);
            }
        });


//        popuplistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i("1234", "selected item ");
//            }
//        });







//        openAddTextPopupWindow("", -1);

    }

    @Override
    public void onEditTextChangeListener(String text, int colorCode) {
        openAddTextPopupWindow(text, colorCode);
        maintextvalue = text;
    }

    @Override
    public void onAddViewListener(ViewType viewType, int numberOfAddedViews) {
        if (numberOfAddedViews > 0) {
//            undoTextView.setVisibility(View.VISIBLE);
//            undoTextTextView.setVisibility(View.VISIBLE);
        }
        switch (viewType) {
            case BRUSH_DRAWING:
                Log.i("BRUSH_DRAWING", "onAddViewListener");
                break;
            case EMOJI:
                Log.i("EMOJI", "onAddViewListener");
                break;
            case IMAGE:
                Log.i("IMAGE", "onAddViewListener");
                break;
            case TEXT:
                Log.i("TEXT", "onAddViewListener");
                break;
        }
    }

    @Override
    public void onRemoveViewListener(int numberOfAddedViews) {
        Log.i(TAG, "onRemoveViewListener");
        if (numberOfAddedViews == 0) {
            undoTextView.setVisibility(View.GONE);
            undoTextTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStartViewChangeListener(ViewType viewType) {
        switch (viewType) {
            case BRUSH_DRAWING:
                Log.i("BRUSH_DRAWING", "onStartViewChangeListener");
                break;
            case EMOJI:
                Log.i("EMOJI", "onStartViewChangeListener");
                break;
            case IMAGE:
                Log.i("IMAGE", "onStartViewChangeListener");
                break;
            case TEXT:
                Log.i("TEXT", "onStartViewChangeListener");
                break;
        }
    }

    @Override
    public void onStopViewChangeListener(ViewType viewType) {
        switch (viewType) {
            case BRUSH_DRAWING:
                Log.i("BRUSH_DRAWING", "onStopViewChangeListener");
                break;
            case EMOJI:
                Log.i("EMOJI", "onStopViewChangeListener");
                break;
            case IMAGE:
                Log.i("IMAGE", "onStopViewChangeListener");
                break;
            case TEXT:
                Log.i("TEXT", "onStopViewChangeListener");
                break;
        }
    }

    //interface
    @Override
    public void myClickListener(int imagePath) {
        drawingViewColorPickerRecyclerView.setVisibility(View.INVISIBLE);
        photoEditImageView.setImageResource(imagePath);
    }

    //interface
    @Override
    public void colorchange() {
        drawingViewColorPickerRecyclerView.setVisibility(View.VISIBLE);
        photoEditImageView.setImageResource(0);
        photoEditImageView.setBackgroundColor(colorPickerColors.get(1));

    }

    //interface
    @Override
    public void fontchange(Typeface typeface) {

        maintextface = typeface;

        addText(maintextletter, maintextcolor, maintextface);

    }

    private class PreviewSlidePagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragments;

        PreviewSlidePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            if (mFragments == null) {
                return (null);
            }
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}

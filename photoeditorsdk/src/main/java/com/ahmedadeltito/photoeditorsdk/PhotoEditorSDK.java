package com.ahmedadeltito.photoeditorsdk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.ColorInt;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Adel on 02/06/2017.
 */

public class PhotoEditorSDK implements MultiTouchListener.OnMultiTouchListener {

    private Context context;
    private RelativeLayout parentView;
    private ImageView imageView;
    private View deleteView;
    private BrushDrawingView brushDrawingView;
    private List<View> addedViews;
    private OnPhotoEditorSDKListener onPhotoEditorSDKListener;
    private View addTextRootView;
    private View addsubviewinRootView;

    private PhotoEditorSDK(PhotoEditorSDKBuilder photoEditorSDKBuilder) {
        this.context = photoEditorSDKBuilder.context;
        this.parentView = photoEditorSDKBuilder.parentView;
        this.imageView = photoEditorSDKBuilder.imageView;
        this.deleteView = photoEditorSDKBuilder.deleteView;
        this.brushDrawingView = photoEditorSDKBuilder.brushDrawingView;
        addedViews = new ArrayList<>();
    }

    public void addImage(Bitmap desiredImage) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View imageRootView = inflater.inflate(R.layout.photo_editor_sdk_image_item_list, null);
        ImageView imageView = (ImageView) imageRootView.findViewById(R.id.photo_editor_sdk_image_iv);
        imageView.setImageBitmap(desiredImage);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        MultiTouchListener multiTouchListener = new MultiTouchListener(deleteView,
                parentView, this.imageView, onPhotoEditorSDKListener);
        multiTouchListener.setOnMultiTouchListener(this);
        imageRootView.setOnTouchListener(multiTouchListener);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        parentView.addView(imageRootView, params);
        addedViews.add(imageRootView);
        if (onPhotoEditorSDKListener != null)
            onPhotoEditorSDKListener.onAddViewListener(ViewType.IMAGE, addedViews.size());
    }

    public void addText(String text, int colorCodeTextView, Typeface fontface) {
//        Log.i("1234", "text "+text+"," +colorCodeTextView+","+fontface);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addTextRootView = inflater.inflate(R.layout.photo_editor_sdk_text_item_list, null);
        TextView addTextView = (TextView) addTextRootView.findViewById(R.id.photo_editor_sdk_text_tv);
        addTextView.setGravity(Gravity.CENTER);
        addTextView.setText(text);
//        if (colorCodeTextView != -1)
            addTextView.setTextColor(colorCodeTextView);
            addTextView.setTypeface(fontface);
        MultiTouchListener multiTouchListener = new MultiTouchListener(deleteView,
                parentView, this.imageView, onPhotoEditorSDKListener);
        multiTouchListener.setOnMultiTouchListener(this);
        addTextRootView.setOnTouchListener(multiTouchListener);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        parentView.addView(addTextRootView, params);
        addedViews.add(addTextRootView);
        if (onPhotoEditorSDKListener != null)
            onPhotoEditorSDKListener.onAddViewListener(ViewType.TEXT, addedViews.size());
    }

    public void addEmoji(String emojiName, Typeface emojiFont) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View emojiRootView = inflater.inflate(R.layout.photo_editor_sdk_text_item_list, null);
        TextView emojiTextView = (TextView) emojiRootView.findViewById(R.id.photo_editor_sdk_text_tv);
        emojiTextView.setTypeface(emojiFont);
        emojiTextView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        emojiTextView.setText(convertEmoji(emojiName));
        MultiTouchListener multiTouchListener = new MultiTouchListener(deleteView,
                parentView, this.imageView, onPhotoEditorSDKListener);
        multiTouchListener.setOnMultiTouchListener(this);
        emojiRootView.setOnTouchListener(multiTouchListener);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        parentView.addView(emojiRootView, params);
        addedViews.add(emojiRootView);
        if (onPhotoEditorSDKListener != null)
            onPhotoEditorSDKListener.onAddViewListener(ViewType.EMOJI, addedViews.size());
    }

    public void setBrushDrawingMode(boolean brushDrawingMode) {
        if (brushDrawingView != null)
            brushDrawingView.setBrushDrawingMode(brushDrawingMode);
    }

    public void setBrushSize(float size) {
        if (brushDrawingView != null)
            brushDrawingView.setBrushSize(size);
    }

    public void setBrushColor(@ColorInt int color) {
        if (brushDrawingView != null)
            brushDrawingView.setBrushColor(color);
    }

    public void setBrushEraserSize(float brushEraserSize) {
        if (brushDrawingView != null)
            brushDrawingView.setBrushEraserSize(brushEraserSize);
    }

    public void setBrushEraserColor(@ColorInt int color) {
        if (brushDrawingView != null)
            brushDrawingView.setBrushEraserColor(color);
    }

    public float getEraserSize() {
        if (brushDrawingView != null)
            return brushDrawingView.getEraserSize();
        return 0;
    }

    public float getBrushSize() {
        if (brushDrawingView != null)
            return brushDrawingView.getBrushSize();
        return 0;
    }

    public int getBrushColor() {
        if (brushDrawingView != null)
            return brushDrawingView.getBrushColor();
        return 0;
    }

    public void brushEraser() {
        if (brushDrawingView != null)
            brushDrawingView.brushEraser();
    }

    public void viewUndo() {
        if (addedViews.size() > 0) {
            parentView.removeView(addedViews.remove(addedViews.size() - 1));
            if (onPhotoEditorSDKListener != null)
                onPhotoEditorSDKListener.onRemoveViewListener(addedViews.size());
        }
    }

    private void viewUndo(View removedView) {
        if (addedViews.size() > 0) {
            if (addedViews.contains(removedView)) {
                parentView.removeView(removedView);
                addedViews.remove(removedView);
                if (onPhotoEditorSDKListener != null)
                    onPhotoEditorSDKListener.onRemoveViewListener(addedViews.size());
            }
        }
    }

    public void clearBrushAllViews() {
        if (brushDrawingView != null)
            brushDrawingView.clearAll();
    }

    public void clearAllViews() {
        for (int i = 0; i < addedViews.size(); i++) {
            parentView.removeView(addedViews.get(i));
        }
        if (brushDrawingView != null)
            brushDrawingView.clearAll();
    }
//    saveshareImage
public String saveshareImage(String folderName, String imageName) {
    String selectedOutputPath = "";
    if (isSDCARDMounted()) {
        File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), folderName);
        // Create a storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("1234", "Failed to create directory");
            }
        }
        // Create a media file name
        selectedOutputPath = mediaStorageDir.getPath() + File.separator + imageName;
        Log.d("1234", "selected camera path " + selectedOutputPath);
        File file = new File(selectedOutputPath);
        try {
            FileOutputStream out = new FileOutputStream(file);
            if (parentView != null) {
                parentView.setDrawingCacheEnabled(true);
//                    parentView.getDrawingCache().compress(Bitmap.CompressFormat.JPEG, 80, out);
                parentView.getDrawingCache().compress(Bitmap.CompressFormat.PNG, 100, out);
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent shareIntent = new Intent(Intent.ACTION_SEND);

        String imagePath = Environment.getExternalStorageDirectory() + File.separator +"Pictures"+File.separator+ "PhotoEditorSDK"+File.separator+ imageName;
        Log.i("1234", "Image path : "+ imagePath);
        File imageFileToShare = new File(imagePath);

        Uri phototUri;

        if (Build.VERSION.SDK_INT < 24) {
            phototUri = Uri.fromFile(imageFileToShare);
        } else {
            phototUri = Uri.parse(imageFileToShare.getPath()); // My work-around for new SDKs, causes ActivityNotFoundException in API 10.
        }

        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, phototUri);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(shareIntent, "Share Via"));

    }
    return selectedOutputPath;
}

    public String saveImage(String folderName, String imageName) {
        String selectedOutputPath = "";
        if (isSDCARDMounted()) {
            File mediaStorageDir = new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), folderName);
            // Create a storage directory if it does not exist
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.d("1234", "Failed to create directory");
                }
            }
            // Create a media file name
            selectedOutputPath = mediaStorageDir.getPath() + File.separator + imageName;
            Log.d("1234", "selected camera path " + selectedOutputPath);
            File file = new File(selectedOutputPath);
            try {
                FileOutputStream out = new FileOutputStream(file);
                if (parentView != null) {
                    parentView.setDrawingCacheEnabled(true);
//                    parentView.getDrawingCache().compress(Bitmap.CompressFormat.JPEG, 80, out);
                    parentView.getDrawingCache().compress(Bitmap.CompressFormat.PNG, 100, out);
                }
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + selectedOutputPath)));
        }
        return selectedOutputPath;
    }

    private boolean isSDCARDMounted() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    private String convertEmoji(String emoji) {
        String returnedEmoji = "";
        try {
            int convertEmojiToInt = Integer.parseInt(emoji.substring(2), 16);
            returnedEmoji = getEmojiByUnicode(convertEmojiToInt);
        } catch (NumberFormatException e) {
            returnedEmoji = "";
        }
        return returnedEmoji;
    }

    private String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    public void setOnPhotoEditorSDKListener(OnPhotoEditorSDKListener onPhotoEditorSDKListener) {
        this.onPhotoEditorSDKListener = onPhotoEditorSDKListener;
//        brushDrawingView.setOnPhotoEditorSDKListener(onPhotoEditorSDKListener);
    }

    @Override
    public void onEditTextClickListener(String text, int colorCode) {
        if (addTextRootView != null) {
            parentView.removeView(addTextRootView);
            addedViews.remove(addTextRootView);
        }
    }

    @Override
    public void onRemoveViewListener(View removedView) {
        viewUndo(removedView);
    }

    public static class PhotoEditorSDKBuilder {

        private Context context;
        private RelativeLayout parentView;
        private ImageView imageView;
        private View deleteView;
        private BrushDrawingView brushDrawingView;

        public PhotoEditorSDKBuilder(Context context) {
            this.context = context;
        }

        public PhotoEditorSDKBuilder parentView(RelativeLayout parentView) {
            this.parentView = parentView;
            return this;
        }

        public PhotoEditorSDKBuilder childView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public PhotoEditorSDKBuilder deleteView(View deleteView) {
            this.deleteView = deleteView;
            return this;
        }

        public PhotoEditorSDKBuilder brushDrawingView(BrushDrawingView brushDrawingView) {
            this.brushDrawingView = brushDrawingView;
            return this;
        }

        public PhotoEditorSDK buildPhotoEditorSDK() {
            return new PhotoEditorSDK(this);
        }
    }
}

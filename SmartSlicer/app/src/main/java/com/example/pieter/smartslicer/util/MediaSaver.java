package com.example.pieter.smartslicer.util;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pieter on 3/30/15.
 */

public class MediaSaver {

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    public static Uri getOutputMediaFileUri(int type, Context context) {
        return Uri.fromFile(getOutputMediaFile(type, context));
    }

    private static File getOutputMediaFile(int type, Context context) {
        if( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
                || Environment.getExternalStorageState().equals(Environment.MEDIA_SHARED)
                ) {
            File mediaStorageDir = new File(context.getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES), "SmartSlicer" );

            if (! mediaStorageDir.exists()) {
                if (! mediaStorageDir.mkdirs()){
                    Log.d("Smart-Slicer", "Failed to create external files directory");
                    return null;
                }
            }

            assert type == MEDIA_TYPE_IMAGE; // :TODO: implement for video type
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File mediaFile;
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timestamp + ".jpg");
            return mediaFile;
        }
        else
        {
            Log.d("Smart-Slicer", "Unable to access external storage!.. Status: " + Environment.getExternalStorageState());
            return null;
        }
    }
}

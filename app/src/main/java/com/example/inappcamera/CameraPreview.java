package com.example.inappcamera;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/*  A camera preview class is a SurfaceView that can display the live image data coming from a camera, so users can frame and capture a picture or video.*/

/*preview here means showing images that the camera presently can see on surfaceVew*/
public class CameraPreview  extends SurfaceView implements SurfaceHolder.Callback {    //classPreview class implements SurfaceHolder.Callback in order to capture the callback events for creating and destroying the view, which are needed for assigning the camera preview input.

    private SurfaceHolder mHolder;
    private Camera mCamera;

    //constructor
    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            Log.d("CameraPreview","surface created!");
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d("surfaceCreated", "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null){
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e){
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            mCamera.setDisplayOrientation(0);
            mCamera.lock();
        }
        else {
            mCamera.setDisplayOrientation(90);
            mCamera.lock();
        }

        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e){
            Log.d("surfaceChanged", "Error starting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // empty. Take care of releasing the Camera preview in your activity.
    }
}

package com.example.inappcamera;

import android.hardware.Camera;
import android.util.Log;

public class cameraProvider {
    /**
     * A safe way to get an instance of the Camera object.
     */
    private static Camera camera =null;
    public static Camera getCameraInstance() {          // return obj of Camera type
        if(camera==null){
            try {
                Log.d("inside try", "try");
                camera = Camera.open(); // attempt to get a Camera instance     for primary camera
            } catch (Exception e) {
                // Camera is not available (in use or does not exist)
                Log.e("getCameraInstance", e.toString());
            }
            return camera; // returns null if camera is unavailable
        }
        else{
            return camera;
        }
    }

    public static void setCamera(Camera camera) {
        cameraProvider.camera = camera;
    }
}

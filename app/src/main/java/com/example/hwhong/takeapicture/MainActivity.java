package com.example.hwhong.takeapicture;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final int CAPTURE = 1;
    ImageView photo;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        photo = (ImageView) findViewById(R.id.imageView);

        if (!hasCamera()) {
            //when the camera is non existent
            button.setEnabled(false);
        }

    }

    public boolean hasCamera() {
        //if there is any camera installed to the phone
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Start an activity and expect something in return
        //i.e when changing user picture, explicitly opens the gallery app
        startActivityForResult(intent, CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extra = data.getExtras();
            Bitmap photo1 = (Bitmap) extra.get("data");
            photo.setImageBitmap(photo1);
        }
    }
}

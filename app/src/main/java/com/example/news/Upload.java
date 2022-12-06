package com.example.news;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Upload extends AppCompatActivity {
    Button submit,upload;
    ImageView imageview;

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        imageview= findViewById(R.id.imageView3);
        submit=findViewById(R.id.submit);
        upload=findViewById(R.id.button4);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                         if (result.getResultCode()==RESULT_OK && result.getData() != null){
                             Bundle bundle=result.getData().getExtras();
                             Bitmap bitmap=(Bitmap) bundle.get("data");
                             imageview.setImageBitmap(bitmap);
                         }
                    }
                }
        );
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) !=null){
                    activityResultLauncher.launch(intent);
                }
                else {
                    Toast.makeText(Upload.this, "No support for this action", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void upload(View view) {


    }

}
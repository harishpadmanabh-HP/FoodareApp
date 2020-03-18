package com.hp.foodareapp.donator;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.core.content.FileProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.hp.foodareapp.BuildConfig;
import com.hp.foodareapp.R;
import com.hp.foodareapp.Utils.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import okhttp3.MultipartBody;

public class AddFood extends AppCompatActivity {

    private TextInputEditText foodname;
    private TextInputEditText foodtype;
    private TextInputEditText foodquantity;
    private TextInputEditText foodadd;
    private AppCompatAutoCompleteTextView city;
    private MaterialButton addphoto;
    private MaterialButton addfood;
    private ArrayList<String> districlist;
    private File imgFile;
    private boolean isPhototaken;
    private String pictureFilePath;
    MultipartBody.Part filePart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        initView();
        View rootView = findViewById(android.R.id.content);
        isPhototaken=false;

        //conecting all TextInputEditText as list
        final List<TextInputEditText> textInputEditTexts = Utils.findViewsWithType(
                rootView, TextInputEditText.class);
        districlist=new ArrayList<>();
        districlist.add("Thiruvananthapuram");
        districlist.add("Kanyakumari");
        ArrayAdapter<String> d_adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,districlist);
        city.setThreshold(1);//will start working from first character
        city.setAdapter(d_adapter);//setting the adapter data into the AutoCompleteTextView
        city.setTextColor(Color.BLACK);

        addfood.setOnClickListener(v -> {

            //checking null values for each edittesxt
            boolean noErrors = true;
            for (TextInputEditText textInputEditText : textInputEditTexts) {
                //get strings from each edittext
                String editTextString = textInputEditText.getText().toString();
                if (editTextString.isEmpty()) {
                    textInputEditText.setError(("please fill this field"));
                    noErrors = false;
                } else {
                    textInputEditText.setError(null);
                }
            }

            if (noErrors) {

            }

        });

        addphoto.setOnClickListener(v -> {
            selectImage();
        });
    }

    private void initView() {
        foodname = findViewById(R.id.foodname);
        foodtype = findViewById(R.id.foodtype);
        foodquantity = findViewById(R.id.foodquantity);
        foodadd = findViewById(R.id.foodadd);
        city = findViewById(R.id.city);
        addphoto = findViewById(R.id.addphoto);
        addfood = findViewById(R.id.addfood);
    }
    private void selectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    takePicture();
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void takePicture() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
        if (cameraIntent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
            //startActivityForResult(cameraIntent, REQUEST_PICTURE_CAPTURE);

            File pictureFile = null;
            try {
                pictureFile = getPictureFile();
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(),
                        "Photo file can't be created, please try again",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if (pictureFile != null) {
                Uri photoURI = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                        BuildConfig.APPLICATION_ID + ".provider", pictureFile);


                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, 1);
            }
        }
    }

    private File getPictureFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "Foodare" + timeStamp;
        File storageDir = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(pictureFile, ".jpg", storageDir);
        pictureFilePath = image.getAbsolutePath();
        return image;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                //File for upload
                imgFile = new File(pictureFilePath);
                if (imgFile.exists()) {
                    isPhototaken = true;
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),
                            bitmapOptions);
                  //  staffdp.setImageBitmap(bitmap);

                    Toast.makeText(getApplicationContext(), "Photo Chosed for upload", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "imgFile not exist", Toast.LENGTH_SHORT).show();

                }
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getApplicationContext().getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                imgFile = new File(picturePath);
                isPhototaken = true;

                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.e("path of image from gallery......******************.........", picturePath + "");
                //staffdp.setImageBitmap(thumbnail);
            }
        }
    }


}

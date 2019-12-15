package com.macpherson.brandon.mainproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
/*
Not Working.  Supposed to ask for info then send it back to MainActivity for a datalist entry.
*/
public class NewPostActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST = 0;
    private static final int RESULT_LOAD_IMAGE = 1;

    public String _item, _info, _address, _city, _state, _location;
    public int _photo;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
           checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
        }

        imageView = findViewById(R.id.imadgeView);

        //Intent intent = new Intent();


    }

    public void onPic(View theButton) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    public void onPost(View theButton) {
        EditText item = findViewById(R.id.editText_Item);
        EditText info = findViewById(R.id.editText_Info);
        EditText address = findViewById(R.id.editText_Address);
        EditText city = findViewById(R.id.editText_City);
        EditText state = findViewById(R.id.editText_State);

        _item = item.getText().toString();
        _info = info.getText().toString();
        _address = address.getText().toString();
        _city = city.getText().toString();
        _state = state.getText().toString();
        _location = String.format("%s\n%s, %s", _address, _city, _state);
        _photo = R.id.imadgeView;

        SharedPreferences pref = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("item", _item);
        editor.putString("info", _info);
        editor.putString("address", _address);
        editor.putString("city", _city);
        editor.putString("state", _state);
        editor.putString("location", _location);
        editor.putInt("photo", _photo);

        editor.apply();

        

//        Intent result = new Intent();
//        result.putExtra("item", _item);
//        result.putExtra("info", _info);
//        result.putExtra("address", _address);
//        result.putExtra("city", _city);
//        result.putExtra("state", _state);
//        result.putExtra("location", _location);
//        result.putExtra("photo", _photo);
//
//        setResult(RESULT_OK, result);
//        finish();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case RESULT_LOAD_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                }
        }
    }
}

package com.softhans.myimplicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mwebsite, mLocation, mText;
    private Button webBtn, locBtn, txtBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mwebsite = findViewById(R.id.website_txt);
        mLocation = findViewById(R.id.location_txt);
        mText = findViewById(R.id.text_txt);
        webBtn = findViewById(R.id.webBtn);
        locBtn = findViewById(R.id.locationBtn);
        txtBtn = findViewById(R.id.textBtn);
    }

    public void shareText(View view) {

        String txt = mText.getText().toString();

        //Define the mime type of the text to share:
        String mimeType = "text/plain";

        //Call ShareCompat.IntentBuilder with these methods:
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();

    }

    public void openLocation(View view) {

        String loc = mLocation.getText().toString();

        //Parse that string into a Uri object with a geo search query:
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        //Create a new Intent with Intent.ACTION_VIEW as the action and loc as the data.
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        // Find an activity to handle the intent, and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }

    }

    public void openWebsite(View view) {


        String url = mwebsite.getText().toString();

        //Encode and parse that string into a Uri object:
        Uri webpage = Uri.parse(url);

        //Create a new Intent with Intent.ACTION_VIEW as the action and the URI as the data:
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        // Find an activity to hand the intent and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }
}

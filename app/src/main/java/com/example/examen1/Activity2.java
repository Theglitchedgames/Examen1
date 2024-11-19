package com.example.examen1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

public class Activity2 extends AppCompatActivity implements
        Fragment1.OnTextUpdateListener,
        Fragment1.OnTextSizeChangedListener,
        Fragment3.OnColorChangedListener {

    private Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        Toolbar toolbar = findViewById(R.id.topBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            fragment2 = new Fragment2();
            transaction.replace(R.id.fragment_container2, fragment2);
            transaction.replace(R.id.fragment_container1, new Fragment1());
            transaction.replace(R.id.fragment_container3, new Fragment3());
            transaction.commit();
        } else {
            fragment2 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment_container2);
        }

        configureEmojis();
    }

    private void configureEmojis() {
        ImageView emoji2 = findViewById(R.id.emoji2);
        emoji2.setOnClickListener(v -> {
            Intent intent = new Intent(Activity2.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onTextUpdated(String text) {
        if (fragment2 != null) {
            fragment2.updateText(text);
        }
    }

    @Override
    public void onTextSizeChanged(int textSize) {
        if (fragment2 != null) {
            fragment2.updateTextSize(textSize);
        }
    }

    @Override
    public void onColorChanged(int red, int green, int blue) {
        if (fragment2 != null) {
            int color = Color.rgb(red, green, blue);
            fragment2.updateTextColor(color);
        }
    }
}

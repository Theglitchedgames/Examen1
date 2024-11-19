package com.example.examen1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        textView = view.findViewById(R.id.textView);
        return view;
    }

    public void updateText(String text) {
        if (textView != null) {
            textView.setText(text);
        }
    }

    public void updateTextSize(int size) {
        if (textView != null) {
            textView.setTextSize(size);
        }
    }

    public void updateTextColor(int color) {
        if (textView != null) {
            textView.setTextColor(color);
        }
    }
}

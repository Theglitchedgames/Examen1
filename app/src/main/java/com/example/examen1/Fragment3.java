package com.example.examen1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {

    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    private Button changeColorButton;

    private OnColorChangedListener listener;

    public interface OnColorChangedListener {
        void onColorChanged(int red, int green, int blue);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof OnColorChangedListener) {
            listener = (OnColorChangedListener) getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);

        redSeekBar = view.findViewById(R.id.redSeekBar);
        greenSeekBar = view.findViewById(R.id.greenSeekBar);
        blueSeekBar = view.findViewById(R.id.blueSeekBar);
        changeColorButton = view.findViewById(R.id.changeColorButton);

        changeColorButton.setOnClickListener(v -> {
            int red = redSeekBar.getProgress();
            int green = greenSeekBar.getProgress();
            int blue = blueSeekBar.getProgress();
            if (listener != null) {
                listener.onColorChanged(red, green, blue);
            }
        });

        return view;
    }
}

package com.example.examen1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    private OnTextSizeChangedListener textSizeChangedListener;
    private OnTextUpdateListener textUpdateListener;

    public interface OnTextSizeChangedListener {
        void onTextSizeChanged(int textSize);
    }

    public interface OnTextUpdateListener {
        void onTextUpdated(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnTextSizeChangedListener) {
            textSizeChangedListener = (OnTextSizeChangedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnTextSizeChangedListener");
        }
        if (context instanceof OnTextUpdateListener) {
            textUpdateListener = (OnTextUpdateListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnTextUpdateListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        EditText editText = view.findViewById(R.id.editText);
        SeekBar seekBar = view.findViewById(R.id.seekBar);
        Button btnChangeText = view.findViewById(R.id.changeTextButton);

        // Listener para el SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (textSizeChangedListener != null) {
                    textSizeChangedListener.onTextSizeChanged(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // Botón para actualizar el texto
        btnChangeText.setOnClickListener(v -> {
            String text = editText.getText().toString();
            if (textUpdateListener != null) {
                textUpdateListener.onTextUpdated(text);
            }
        });

        return view;
    }
}

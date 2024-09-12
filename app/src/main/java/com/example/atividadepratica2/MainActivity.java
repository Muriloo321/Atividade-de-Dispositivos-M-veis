package com.example.atividadepratica2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CircleView circleView;
    private SharedPreferences sharedPreferences;
    private static final String PREFERENCE_KEY = "circle_color_pref";
    private static final String COLOR_KEY = "circle_color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleView = findViewById(R.id.circleView);

        sharedPreferences = getSharedPreferences(PREFERENCE_KEY, MODE_PRIVATE);

        int savedColor = sharedPreferences.getInt(COLOR_KEY, -1);
        if (savedColor != -1) {
            circleView.setCircleColor(savedColor);
        } else {
            circleView.setDefaultColor();
        }

        circleView.setOnClickListener(v -> showColorSelectionDialog());
    }

    private void showColorSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecione a Cor");

        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);

        String[] colors = {"Vermelho", "Verde", "Azul"};
        int[] colorValues = {0xFFFF0000, 0xFF00FF00, 0xFF0000FF};

        for (int i = 0; i < colors.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(colors[i]);
            radioButton.setId(i);
            radioButton.setTextColor(0xFF000000);
            radioGroup.addView(radioButton);
        }

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int selectedColor = colorValues[checkedId];
            circleView.setCircleColor(selectedColor);
            saveColorToPreferences(selectedColor);
        });

        builder.setView(radioGroup);
        builder.show();
    }

    private void saveColorToPreferences(int color) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(COLOR_KEY, color);
        editor.apply();
        Toast.makeText(this, "Cor salva com sucesso!", Toast.LENGTH_SHORT).show();
    }
}

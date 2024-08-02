package com.ebertcs.tarea_pmdm02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EuropaContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paises_content);

        // Recibir la información de la actividad principal
        Intent intent = getIntent();
        boolean option1Selected = intent.getBooleanExtra("OPCION_1", false);
        boolean option2Selected = intent.getBooleanExtra("OPCION_2", false);
        boolean option3Selected = intent.getBooleanExtra("OPCION_3", false);

        TextView textView1 = findViewById(R.id.textPais1);
        ImageView myImageView1 = findViewById(R.id.imagePais1);
        TextView textView2 = findViewById(R.id.textPais2);
        ImageView myImageView2 = findViewById(R.id.imagePais2);
        TextView textView3 = findViewById(R.id.textPais3);
        ImageView myImageView3 = findViewById(R.id.imagePais3);

        StringBuilder info = new StringBuilder("Europa");
        TextView titulo = findViewById(R.id.paises_titulo);
        titulo.setText(info.toString());

        if (option1Selected) {

            myImageView1.setImageResource(R.drawable.espania);
            textView1.setText("ESPAÑA");
            myImageView2.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            myImageView3.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
        }

        if (option2Selected) {
            myImageView1.setImageResource(R.drawable.italia);
            textView1.setText("ITALIA");
            myImageView2.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            myImageView3.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);

        }

        if (option3Selected) {
            myImageView1.setImageResource(R.drawable.portugal);
            textView1.setText("PORTUGAL");
            myImageView2.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            myImageView3.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);

        }

        if (option1Selected && option2Selected) {
            myImageView1.setImageResource(R.drawable.espania);
            textView1.setText("ESPAÑA");
            myImageView2.setImageResource(R.drawable.italia);
            textView2.setText("ITALIA");
            myImageView2.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            myImageView3.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
        }

        if (option1Selected && option3Selected) {
            myImageView1.setImageResource(R.drawable.espania);
            textView1.setText("ESPAÑA");
            myImageView2.setImageResource(R.drawable.portugal);
            textView2.setText("PORTUGAL");
            myImageView2.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            myImageView3.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
        }

        if (option2Selected && option3Selected) {
            myImageView1.setImageResource(R.drawable.italia);
            textView1.setText("ITALIA");
            myImageView2.setImageResource(R.drawable.portugal);
            textView2.setText("PORTUGAL");
            myImageView2.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            myImageView3.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
        }

        if (option1Selected && option2Selected && option3Selected) {
            myImageView1.setImageResource(R.drawable.espania);
            textView1.setText("ESPAÑA");
            myImageView2.setImageResource(R.drawable.italia);
            textView2.setText("ITALIA");
            myImageView3.setImageResource(R.drawable.portugal);
            textView3.setText("PORTUGAL");
            myImageView2.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            myImageView3.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);

        }

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EuropaContentActivity.this, EuropaViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
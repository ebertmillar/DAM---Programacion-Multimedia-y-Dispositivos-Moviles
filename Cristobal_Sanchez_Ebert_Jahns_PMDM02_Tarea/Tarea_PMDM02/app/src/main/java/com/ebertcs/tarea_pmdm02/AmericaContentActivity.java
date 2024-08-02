package com.ebertcs.tarea_pmdm02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AmericaContentActivity extends AppCompatActivity {

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

        StringBuilder info = new StringBuilder("América");
        TextView titulo = findViewById(R.id.paises_titulo);
        titulo.setText(info.toString());

        if (option1Selected) {

            myImageView1.setImageResource(R.drawable.argentina);
            textView1.setText("ARGENTINA");
            myImageView2.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            myImageView3.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
        }

        if (option2Selected) {
            myImageView1.setImageResource(R.drawable.canada);
            textView1.setText("CANADA");
            myImageView2.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            myImageView3.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);

        }

        if (option3Selected) {
            myImageView1.setImageResource(R.drawable.peru);
            textView1.setText("PERU");
            myImageView2.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            myImageView3.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);

        }

        if (option1Selected && option2Selected) {
            myImageView1.setImageResource(R.drawable.argentina);
            textView1.setText("ARGENTINA");
            myImageView2.setImageResource(R.drawable.canada);
            textView2.setText("CANADA");
            myImageView2.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            myImageView3.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
        }

        if (option1Selected && option3Selected) {
            myImageView1.setImageResource(R.drawable.argentina);
            textView1.setText("ARGENTINA");
            myImageView2.setImageResource(R.drawable.peru);
            textView2.setText("PERU");
            myImageView2.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            myImageView3.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
        }

        if (option2Selected && option3Selected) {
            myImageView1.setImageResource(R.drawable.canada);
            textView1.setText("CANADA");
            myImageView2.setImageResource(R.drawable.peru);
            textView2.setText("PERU");
            myImageView2.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            myImageView3.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
        }

        if (option1Selected && option2Selected && option3Selected) {
            myImageView1.setImageResource(R.drawable.argentina);
            textView1.setText("ARGENTINA");
            myImageView2.setImageResource(R.drawable.canada);
            textView2.setText("CANADA");
            myImageView3.setImageResource(R.drawable.peru);
            textView3.setText("PERU");
            myImageView2.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            myImageView3.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);

        }

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AmericaContentActivity.this, AmericaViewActivity.class);
                startActivity(intent);
            }
        });


    }
}
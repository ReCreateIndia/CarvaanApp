package startup.carvaan.myapplication.ui.navbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import startup.carvaan.myapplication.R;
import startup.carvaan.myapplication.ui.mainActivity.MainActivity;

public class Helppage extends AppCompatActivity {
private ImageView downarrow1,downarrow2,downarrow3;
private LinearLayout description1,description2,description3,howtoinvest;
private TextView invest;
private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helppage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        downarrow1=findViewById(R.id.down1);
        description1=findViewById(R.id.description1);
        howtoinvest=findViewById(R.id.howtoinvest);
        invest=findViewById(R.id.Investtest);
        downarrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if(count%2==1)
                {
                        downarrow1.setImageResource(R.drawable.uparu);
                        invest.setTextColor(Color.parseColor("#FFFFFF"));
                        howtoinvest.setBackgroundColor(Color.parseColor("#000000"));
                        description1.setVisibility(View.VISIBLE);
                }
                else
                {
                    downarrow1.setImageResource(R.drawable.downarrow);
                    invest.setTextColor(Color.parseColor("#000000"));
                    howtoinvest.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    description1.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        startActivity(new Intent(Helppage.this, MainActivity.class));
    }
}
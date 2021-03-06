package startup.carvaan.myapplication.ui.navbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import startup.carvaan.myapplication.R;

public class Helppage extends AppCompatActivity {
private ImageView downarrow1,downarrow2,downarrow3;
private LinearLayout description1,description2,description3,howtoinvest,howtoearn,rulesre,picture;
private TextView invest,earn,rules;
private int count=0,count2=0,count3=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helppage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        downarrow1 = findViewById(R.id.down1);
        description1 = findViewById(R.id.description1);
        howtoinvest = findViewById(R.id.howtoinvest);
        invest = findViewById(R.id.Investtest);
        downarrow2 = findViewById(R.id.down2);
        description2 = findViewById(R.id.description2);
        howtoearn = findViewById(R.id.howtoearn);
        earn = findViewById(R.id.howearnmoney);
        picture = findViewById(R.id.pict);
        downarrow3 = findViewById(R.id.down3);
        description3 = findViewById(R.id.description3);
        rulesre = findViewById(R.id.setyougoal);
        rules = findViewById(R.id.rules);

        howtoinvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if (count % 2 == 1) {
                    count2=0;
                    count3=0;
                    description3.setVisibility(View.GONE);
                    description2.setVisibility(View.GONE);
                    downarrow3.setImageResource(R.drawable.downarrow);
                    rules.setTextColor(Color.parseColor("#000000"));
                    rulesre.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    description3.setVisibility(View.GONE);
                    downarrow2.setImageResource(R.drawable.downarrow);
                    earn.setTextColor(Color.parseColor("#000000"));
                    howtoearn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    description2.setVisibility(View.GONE);
                    picture.setVisibility(View.GONE);
                    downarrow1.setImageResource(R.drawable.expanda);
                    invest.setTextColor(Color.parseColor("#FFFFFF"));
                    howtoinvest.setBackgroundColor(Color.parseColor("#FF533D78"));
                    description1.setVisibility(View.VISIBLE);


                } else {
                    picture.setVisibility(View.VISIBLE);
                    downarrow1.setImageResource(R.drawable.downarrow);
                    invest.setTextColor(Color.parseColor("#000000"));
                    howtoinvest.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    description1.setVisibility(View.GONE);
                }
            }
        });


        howtoearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count2++;
                if (count2 % 2 == 1) {
                    count=0;
                    count3=0;
                    description3.setVisibility(View.GONE);
                    description1.setVisibility(View.GONE);
                    downarrow1.setImageResource(R.drawable.downarrow);
                    invest.setTextColor(Color.parseColor("#000000"));
                    howtoinvest.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    description1.setVisibility(View.GONE);
                    downarrow3.setImageResource(R.drawable.downarrow);
                    rules.setTextColor(Color.parseColor("#000000"));
                    rulesre.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    description3.setVisibility(View.GONE);
                    picture.setVisibility(View.GONE);
                    downarrow2.setImageResource(R.drawable.expanda);
                    earn.setTextColor(Color.parseColor("#FFFFFF"));
                    howtoearn.setBackgroundColor(Color.parseColor("#FF533D78"));
                    description2.setVisibility(View.VISIBLE);
                } else {
                    picture.setVisibility(View.VISIBLE);
                    downarrow2.setImageResource(R.drawable.downarrow);
                    earn.setTextColor(Color.parseColor("#000000"));
                    howtoearn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    description2.setVisibility(View.GONE);
                }
            }
        });
        rulesre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count3++;
                if (count3 % 2 == 1) {
                    count2=0;
                    count=0;
                    description2.setVisibility(View.GONE);
                    description1.setVisibility(View.GONE);
                    downarrow1.setImageResource(R.drawable.downarrow);
                    invest.setTextColor(Color.parseColor("#000000"));
                    howtoinvest.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    downarrow2.setImageResource(R.drawable.downarrow);
                    earn.setTextColor(Color.parseColor("#000000"));
                    howtoearn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    description2.setVisibility(View.GONE);
                    description1.setVisibility(View.GONE);
                    picture.setVisibility(View.GONE);
                    downarrow3.setImageResource(R.drawable.expanda);
                    rules.setTextColor(Color.parseColor("#FFFFFF"));
                    rulesre.setBackgroundColor(Color.parseColor("#FF533D78"));
                    description3.setVisibility(View.VISIBLE);
                } else {
                    picture.setVisibility(View.VISIBLE);
                    downarrow3.setImageResource(R.drawable.downarrow);
                    rules.setTextColor(Color.parseColor("#000000"));
                    rulesre.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    description3.setVisibility(View.GONE);
                }

            }
        });
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        startActivity(new Intent(Helppage.this, MainActivity.class));
//    }
}
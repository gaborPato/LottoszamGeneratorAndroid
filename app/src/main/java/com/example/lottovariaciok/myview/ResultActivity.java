package com.example.lottovariaciok.myview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lottovariaciok.R;
import com.example.lottovariaciok.myControler.myFactory.LottoAbs;

import java.util.List;

public class ResultActivity extends AppCompatActivity {


    private TextView resultTextView;

    private ImageView logoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        activityColorSet();
        viewsInit();
        resultTextViewInit();


    }

    private void viewsInit() {
        resultTextView = findViewById(R.id.result_text_view);
        logoImageView = findViewById(R.id.logoImView);

        int resLogoImage = getIntent().getIntExtra("logo", R.drawable.default_image);

        logoImageView.setImageResource(resLogoImage);

    }

    private void activityColorSet() {
        View v = findViewById(R.id.res_display);
        View root = v.getRootView();

        root.setBackgroundColor(getIntent().getIntExtra("backgr",Color.WHITE));
    }

    private void resultTextViewInit() {

        List<LottoAbs> lottolist = (List<LottoAbs>) getIntent().getSerializableExtra("Lottolist");

        StringBuilder sb = new StringBuilder();
        for (LottoAbs lottoAbs : lottolist) {

            sb.append(lottoAbs.lottoLine());
            sb.append("\n");

        }
        resultTextView.setText(sb.toString());
    }
}

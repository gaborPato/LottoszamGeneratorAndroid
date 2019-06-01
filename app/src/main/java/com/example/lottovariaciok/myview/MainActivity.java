package com.example.lottovariaciok.myview;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lottovariaciok.myControler.MyIntegers;
import com.example.lottovariaciok.myControler.MyStrings;
import com.example.lottovariaciok.R;
import com.example.lottovariaciok.myControler.CHK_UserDataKt;
import com.example.lottovariaciok.myControler.myFactory.LottoAbs;
import com.example.lottovariaciok.myControler.myFactory.LottoFactory;
import com.example.lottovariaciok.myException.MultiSameNumbersExc;
import com.example.lottovariaciok.myException.NullLineException;
import com.example.lottovariaciok.myException.NumberLimitException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MainActivity extends AppCompatActivity {

    private final String LOTTOTYPE_TEXT_VIEW = "Lottotípus: ";

    private AppCompatSpinner spinner;
    private EditText[] fixNumberEditTexts;
    private LinearLayout lottodata1LinearLayout;
    private LinearLayout fix_numbersLinearLayout;
    private LinearLayout lotto_dataBoxLinearLayout;
    private EditText lineEditText;
    private TextView lottoTypeTextView;
    private int resPictureCode;
    private int passColorCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lotto_dataBoxLinearLayout = findViewById(R.id.lottodata_box_layout);
        lottodata1LinearLayout = findViewById(R.id.lotto1_layout);
        fix_numbersLinearLayout = findViewById(R.id.fix_nums_edit_text_layout);
        resPictureCode = R.drawable.hatos;

        initLottoTypeTV();
        initSpinner();
        lineEditTextInit();
        initfixNumberEditText();
        add_fixET_to_layout();


    }

    private void initLottoTypeTV() {
        lottoTypeTextView = new TextView(this);
        lottoTypeTextView.setText(LOTTOTYPE_TEXT_VIEW);
        lottoTypeTextView.setTextSize(MyIntegers.ViewValues.lottodata1_items_size);
        lottodata1LinearLayout.addView(lottoTypeTextView);
    }

    private void lineEditTextInit() {

        lineEditText = new EditText(this);
        lineEditText.setHint("sorok száma");
        lineEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

        lineEditText.setTextSize(MyIntegers.ViewValues.lottodata1_items_size);
        lineEditText.setLongClickable(false);
        lotto_dataBoxLinearLayout.addView(lineEditText);

    }

    private void initSpinner() {
        spinner = new AppCompatSpinner(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_spinner,
                new String[]{MyStrings.six_lotto_type_str,
                        MyStrings.seven_lotto_type_str,
                        MyStrings.five_lotto_type_str});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        lottodata1LinearLayout.addView(spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(MainActivity.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                    resPictureCode = R.drawable.hatos;
                    passColorCode=MyIntegers.Colors.six_l_background;
                }
                if (spinner.getSelectedItemPosition() == 1) {
                    Toast.makeText(MainActivity.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    resPictureCode = R.drawable.skandi;
                    passColorCode=MyIntegers.Colors.seven_l_background;

                }
                if (spinner.getSelectedItemPosition()==2){
                    Toast.makeText(MainActivity.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    resPictureCode =R.drawable.otos;
                    passColorCode=MyIntegers.Colors.five_l_background;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void add_fixET_to_layout() {
        for (EditText fixNumberEditText : fixNumberEditTexts) {
            fix_numbersLinearLayout.addView(fixNumberEditText);
        }
    }

    private void initfixNumberEditText() {

        fixNumberEditTexts = new EditText[MyIntegers.fix_numbers_max];
        for (int i = 0; i < fixNumberEditTexts.length; i++) {

            fixNumberEditTexts[i] = new EditText(this);
            fixNumberEditTexts[i].setHint((i + 1) + ".fix");
            fixNumberEditTexts[i].setTextSize(32.0f);
            fixNumberEditTexts[i].setInputType(InputType.TYPE_CLASS_NUMBER);
            fixNumberEditTexts[i].setLongClickable(false);
        }
    }


    public void generatedLottoClick(View view) {
        if (lineEditText.getText().toString().equals("") || Integer.valueOf(lineEditText.getText().toString()) < 1) {
            new MyExceptionToast(this, new NullLineException().getMessage());
            return;
        }


        try {
            List<Integer> myFixNumList = createFixNumberSTR();
            List<LottoAbs> my_lott = new LottoFactory(myFixNumList, spinner.getSelectedItem().toString(), Integer.parseInt(lineEditText.getText().toString())).makeFullLotto();


            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("Lottolist", (Serializable) my_lott);
            intent.putExtra("logo", resPictureCode);
            intent.putExtra("backgr",passColorCode);

            startActivity(intent);


        } catch (NoSuchElementException | NumberLimitException | MultiSameNumbersExc ex) {
            String exMessage = ex.getMessage();
            new MyExceptionToast(this, exMessage);

        }


    }


    private List<Integer> createFixNumberSTR() {
        List<String> fixNumList = new ArrayList<>();
        for (EditText text : fixNumberEditTexts) {

            if (!text.getText().toString().equals(""))
                fixNumList.add(text.getText().toString());
        }

        return CHK_UserDataKt.chkFixNumbers(fixNumList, spinner.getSelectedItem().toString());
    }

    private static /*inner*/ class MyExceptionToast extends Toast {

        /**
         * Construct an empty Toast object.  You must call {@link #setView} before you
         * can call {@link #show}.
         *
         * @param context The context to use.  Usually your {@link Application}
         *                or {@link Activity} object.
         */
        private MyExceptionToast(Context context, String excMessage) {
            super(context);

            Toast toast = Toast.makeText(context, excMessage, Toast.LENGTH_LONG);
            ViewGroup view = (ViewGroup) toast.getView();
            TextView toastText = (TextView) view.getChildAt(0);
            toastText.setTextSize(22.5f);
            toastText.setTextColor(Color.WHITE);
            view.setBackgroundColor(MyIntegers.Colors.toastBackGroundColor);

            toast.show();


        }
    }
}




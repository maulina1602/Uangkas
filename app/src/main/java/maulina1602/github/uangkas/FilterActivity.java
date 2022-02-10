package maulina1602.github.uangkas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.andexert.library.RippleView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import maulina1602.github.uangkas.helper.CurrentDate;

public class FilterActivity extends AppCompatActivity {

    MainActivity M = new MainActivity();

    EditText edit_dari, edit_ke;
    Button btn_filter;
    RippleView rip_filter;

    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        edit_dari   = (EditText) findViewById(R.id.edit_dari);
        edit_ke     = (EditText) findViewById(R.id.edit_ke);
        btn_filter  = (Button) findViewById(R.id.btn_filter);
        rip_filter  = (RippleView) findViewById(R.id.rip_filter);

        edit_dari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(FilterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month_of_year, int day_of_month) {
                        NumberFormat numberFormat = new DecimalFormat("00");
                        M.tgl_dari = year  + "-" + numberFormat.format(month_of_year + 1) + "-" +
                                numberFormat.format(day_of_month);
                        edit_dari.setText(numberFormat.format(day_of_month) + "/" + numberFormat.format(month_of_year + 1) +
                                "/" + year);
                    }
                }, CurrentDate.year, CurrentDate.month, CurrentDate.day);
                datePickerDialog.show();
            }
        });

        edit_ke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(FilterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month_of_year, int day_of_month) {
                        NumberFormat numberFormat = new DecimalFormat("00");
                        M.tgl_ke = year  + "-" + numberFormat.format(month_of_year + 1) + "-" +
                                numberFormat.format(day_of_month);
                        edit_ke.setText(numberFormat.format(day_of_month) + "/" + numberFormat.format(month_of_year + 1) +
                                "/" + year);
                    }
                }, CurrentDate.year, CurrentDate.month, CurrentDate.day);
                datePickerDialog.show();
            }
        });

        rip_filter.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                if (edit_dari.getText().toString().equals("") || edit_ke.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Isi data dengan benar",
                            Toast.LENGTH_LONG).show();
                } else {
                    M.filter = true;
                    M.text_filter.setText( edit_dari.getText().toString() + " - " + edit_ke.getText().toString() );
                    M.text_filter.setVisibility(View.VISIBLE);

                    finish();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Atur Tanggal");

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
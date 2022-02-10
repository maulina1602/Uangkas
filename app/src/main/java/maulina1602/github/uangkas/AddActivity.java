package maulina1602.github.uangkas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import maulina1602.github.uangkas.helper.Config;

public class AddActivity extends AppCompatActivity {

    RadioGroup radio_status;
    EditText edit_jumlah, edit_keterangan;
    Button btn_simpan;
    RippleView rip_simpan;

    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        status = "";
        radio_status    = (RadioGroup) findViewById(R.id.radio_status);
        edit_jumlah     = (EditText) findViewById(R.id.edit_jumlah);
        edit_keterangan = (EditText) findViewById(R.id.edit_keterangan);
        btn_simpan      = (Button) findViewById(R.id.btn_simpan);
        rip_simpan      = (RippleView) findViewById(R.id.rip_simpan);

        radio_status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_masuk:
                        status = "MASUK";
                        break;
                    case R.id.radio_keluar:
                        status = "KELUAR";
                        break;
                }
                Log.d("Log Status", status);
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rip_simpan.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                if(status.equals("") || edit_jumlah.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(), "Tolong Isi Data Dengan Benar", Toast.LENGTH_LONG).show();
                }else{
                    _save();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Data");

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void _save()
    {
        AndroidNetworking.post(Config.host + "add.php")
                .addBodyParameter("status", status)
                .addBodyParameter("jumlah", edit_jumlah.getText().toString())
                .addBodyParameter("keterangan", edit_keterangan.getText().toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString());
                        if(response.optString("response").toString().equals("success")){
                            Toast.makeText(getApplicationContext(), "Berhasil Menyimpan Data", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Gagal Menyimpan Data", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
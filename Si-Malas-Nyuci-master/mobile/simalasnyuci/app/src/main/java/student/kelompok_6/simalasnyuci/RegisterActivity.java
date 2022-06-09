package student.kelompok_6.simalasnyuci;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import student.kelompok_6.simalasnyuci.ResponseApi.LoginApiResponse;
import student.kelompok_6.simalasnyuci.api.ApiSiMalasNyuci;
import student.kelompok_6.simalasnyuci.api.RESTClient;
import student.kelompok_6.simalasnyuci.model.User;
import student.kelompok_6.simalasnyuci.pegawai.PegawaiActivity;
import student.kelompok_6.simalasnyuci.pemilik.DashboardActivity;

public class RegisterActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText etKode;
    private TextView lblKode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        spinner = findViewById(R.id.spRole);
        etKode = findViewById(R.id.etKode);
        lblKode = findViewById(R.id.lblKode);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    etKode.setVisibility(View.VISIBLE);
                    lblKode.setVisibility(View.VISIBLE);
                }else{
                    etKode.setVisibility(View.GONE);
                    lblKode.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v->{
            String nama, email, no_hp,password, Kode;
            int role = 0;
            EditText etNama, etEmail, etNoHP, etPassword;
            etNama = findViewById(R.id.etName);
            etEmail = findViewById(R.id.etEmail);
            etPassword = findViewById(R.id.etPass);
            etNoHP = findViewById(R.id.etNoHp);
            nama = etNama.getText().toString();
            email = etEmail.getText().toString();
            no_hp = etNoHP.getText().toString();
            password = etPassword.getText().toString();
            role = (int) spinner.getSelectedItemId();
            if(role == 0){
                Kode = etKode.getText().toString();
            }else{
                Kode = "";
            }
            ApiSiMalasNyuci client = RESTClient.get();
            Call<LoginApiResponse> call = client.registerApi(email, password, no_hp, nama, role, Kode);
            call.enqueue(new Callback<LoginApiResponse>() {
                @Override
                public void onResponse(Call<LoginApiResponse> call, Response<LoginApiResponse> response) {
                    if(response.isSuccessful()){
                        User user = response.body().getUser();
                        String role = user.getRole();
                        if(role.equals("Owner")){
                            Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                            finish();
                        }else{
                            Intent intent = new Intent(RegisterActivity.this, PegawaiActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                            finish();
                        }
                    }else if(response.code() == 400){
                        Toast.makeText(RegisterActivity.this, "Email sudah terdaftar.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterActivity.this, "Gagal Mendaftarkan.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginApiResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Internal server error.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }
}
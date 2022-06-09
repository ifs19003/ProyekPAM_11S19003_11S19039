package student.kelompok_6.simalasnyuci;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import student.kelompok_6.simalasnyuci.ResponseApi.LoginApiResponse;
import student.kelompok_6.simalasnyuci.api.ApiSiMalasNyuci;
import student.kelompok_6.simalasnyuci.api.RESTClient;
import student.kelompok_6.simalasnyuci.model.User;
import student.kelompok_6.simalasnyuci.pegawai.PegawaiActivity;
import student.kelompok_6.simalasnyuci.pemilik.DashboardActivity;

public class LoginActivity extends AppCompatActivity {
    private AppCompatButton btnLogin;
    private EditText etEmail, etPassword;
    private TextView linkToRegister, tvEmailError, tvPasswordError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPass);
        linkToRegister = findViewById(R.id.toRegister);
        tvEmailError = findViewById(R.id.tvEmailError);
        tvPasswordError = findViewById(R.id.tvPasswordError);
        btnLogin.setOnClickListener(v->{
            ApiSiMalasNyuci client = RESTClient.get();
            Call<LoginApiResponse> call =client.loginApi(etEmail.getText().toString(), etPassword.getText().toString());
            call.enqueue(new Callback<LoginApiResponse>() {
                @Override
                public void onResponse(Call<LoginApiResponse> call, Response<LoginApiResponse> response) {
                    if(response.isSuccessful()){
                        User user = response.body().getUser();
                        tvPasswordError.setVisibility(View.GONE);
                        tvEmailError.setVisibility(View.GONE);
                        String role = user.getRole();
                        if(role.equals("Owner")){
                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                            finish();
                        }else{
                            Intent intent = new Intent(LoginActivity.this, PegawaiActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                            finish();
                        }
                    }else if(response.code() == 404){
                        tvEmailError.setVisibility(View.VISIBLE);
                        tvEmailError.setText("Email Tidak Ditemukan!");
                        tvPasswordError.setVisibility(View.GONE);
                    }else if(response.code() == 401){
                        tvEmailError.setVisibility(View.GONE);
                        tvPasswordError.setVisibility(View.VISIBLE);
                        tvPasswordError.setText("Password Salah!");
                    }else if(response.code() == 500){
                        Toast.makeText(LoginActivity.this, String.valueOf(response.code())
                                + " Internal Server Error, check your email or password", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<LoginApiResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });
        });
        linkToRegister.setOnClickListener(v->{
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
        });
    }
}
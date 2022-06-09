package student.kelompok_6.simalasnyuci.pemilik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import student.kelompok_6.simalasnyuci.R;
import student.kelompok_6.simalasnyuci.ResponseApi.DataManyResponse;
import student.kelompok_6.simalasnyuci.adapter.AdapterListPegawai;
import student.kelompok_6.simalasnyuci.api.ApiSiMalasNyuci;
import student.kelompok_6.simalasnyuci.api.RESTClient;
import student.kelompok_6.simalasnyuci.model.User;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListPegawaiActivity extends AppCompatActivity {
    private RecyclerView rvPegawai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pegawai);
        getSupportActionBar().hide();
        rvPegawai = findViewById(R.id.rvPegawai);
        rvPegawai.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        ApiSiMalasNyuci Client = RESTClient.get();
        Call<DataManyResponse> call = Client.getPegawais();
        call.enqueue(new Callback<DataManyResponse>() {
            @Override
            public void onResponse(Call<DataManyResponse> call, Response<DataManyResponse> response) {
                if(response.isSuccessful()){
                    LinkedList<User> users = new LinkedList<User>();
                    users = response.body().getUsers();
                    AdapterListPegawai adapter = new AdapterListPegawai(getApplicationContext(), users);
                    rvPegawai.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<DataManyResponse> call, Throwable t) {

            }
        });
    }
}
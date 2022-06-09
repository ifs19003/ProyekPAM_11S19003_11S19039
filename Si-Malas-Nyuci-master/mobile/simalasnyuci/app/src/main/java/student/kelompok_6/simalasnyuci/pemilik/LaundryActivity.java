package student.kelompok_6.simalasnyuci.pemilik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import student.kelompok_6.simalasnyuci.R;
import student.kelompok_6.simalasnyuci.ResponseApi.DataGetResponse;
import student.kelompok_6.simalasnyuci.api.ApiSiMalasNyuci;
import student.kelompok_6.simalasnyuci.api.RESTClient;
import student.kelompok_6.simalasnyuci.model.Laundry;

public class LaundryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);
        getSupportActionBar().hide();
        ApiSiMalasNyuci Client = RESTClient.get();
        Call<DataGetResponse> call =Client.getLaundry();
        call.enqueue(new Callback<DataGetResponse>() {
            @Override
            public void onResponse(Call<DataGetResponse> call, Response<DataGetResponse> response) {
                if(response.isSuccessful()){
                    ImageView imageView = findViewById(R.id.profileLaundry);
                    Laundry laundry = response.body().getData();
                    Picasso.get()
                            .load(RESTClient.BASE_URL + "uploads/laundry/" + laundry.getProfile_laundry())
                            .placeholder(R.drawable.profil)
                            .fit()
                            .into(imageView);
                    TextView tvNama, tvKode, tvAlamat, tvDeskripsi;
                    tvNama = findViewById(R.id.namaLaundry);
                    tvAlamat = findViewById(R.id.alamatLaundry);
                    tvKode = findViewById(R.id.kodeLaundry);
                    tvDeskripsi = findViewById(R.id.deskripsiLaundry);

                    tvNama.setText(laundry.getNama_laundry());
                    tvAlamat.setText(laundry.getAlamat());
                    tvKode.setText(laundry.getKode_laundry());
                    tvDeskripsi.setText(laundry.getDeskripsi());
                }
            }

            @Override
            public void onFailure(Call<DataGetResponse> call, Throwable t) {

            }
        });
    }
}
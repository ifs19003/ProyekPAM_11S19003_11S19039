package student.kelompok_6.simalasnyuci.pemilik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.util.FileUtil;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import student.kelompok_6.simalasnyuci.R;
import student.kelompok_6.simalasnyuci.ResponseApi.AddSuccessResponse;
import student.kelompok_6.simalasnyuci.api.ApiSiMalasNyuci;
import student.kelompok_6.simalasnyuci.api.RESTClient;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class TambahCucianActivity extends AppCompatActivity {
    ImageView IDProf;
    Button Upload_Btn;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_cucian);
        IDProf=(ImageView)findViewById(R.id.IdProf);
        Upload_Btn=(Button)findViewById(R.id.UploadBtn);

        Upload_Btn.setOnClickListener(v->{
            ImagePicker.Companion.with(TambahCucianActivity.this)
                    .crop()
                    .start();
        });
        Button btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(v->{
            try {
                uploadFile();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

    }

    private void uploadFile() throws URISyntaxException {
        EditText etNama, etNoTelepon, etDeskripsi, etTotalHarga, etTanggal;
        etNama = findViewById(R.id.etNama);
        etNoTelepon = findViewById(R.id.etTelp);
        etDeskripsi = findViewById(R.id.etDeskripsi);
        etTotalHarga = findViewById(R.id.etTotal);
        etTanggal = findViewById(R.id.tangggalSelesai);

        String  nama, notelepon, deskripsi, tanggal;
        int totalHarga;
        nama = etNama.getText().toString();
        notelepon = etNoTelepon.getText().toString();
        deskripsi = etDeskripsi.getText().toString();
        totalHarga = Integer.parseInt(etTotalHarga.getText().toString());
        tanggal = etTanggal.getText().toString();
        File file = new File(new URI(uri.toString()));
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("cucian", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
//        RequestBody _nama =
//                RequestBody.create(
//                        okhttp3.MultipartBody.FORM, nama);
//        RequestBody _telepon =
//                RequestBody.create(
//                        okhttp3.MultipartBody.FORM, notelepon);
//        RequestBody _harga =
//                RequestBody.create(
//                        okhttp3.MultipartBody.FORM, String.valueOf(totalHarga));
//        RequestBody _tanggal =
//                RequestBody.create(
//                        okhttp3.MultipartBody.FORM, tanggal);
//        RequestBody _deskripsi =
//                RequestBody.create(
//                        okhttp3.MultipartBody.FORM, deskripsi);
//        RequestBody _kode =
//                RequestBody.create(
//                        okhttp3.MultipartBody.FORM, "DJKFDFLMNJ");
//        ApiSiMalasNyuci Client = RESTClient.get();
//        Call<AddSuccessResponse> call = Client.addCucian(_nama, _harga, _telepon, _deskripsi, _kode, _tanggal, body);
//        call.enqueue(new Callback<AddSuccessResponse>() {
//            @Override
//            public void onResponse(Call<AddSuccessResponse> call, Response<AddSuccessResponse> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(getApplicationContext(), "Berhasil Menambahkan Data Cucian", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AddSuccessResponse> call, Throwable t) {
//
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        IDProf.setImageURI(uri);
        this.uri = uri;
    }
}
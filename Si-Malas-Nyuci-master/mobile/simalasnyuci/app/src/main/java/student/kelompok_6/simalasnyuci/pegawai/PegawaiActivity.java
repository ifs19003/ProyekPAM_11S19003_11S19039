package student.kelompok_6.simalasnyuci.pegawai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import student.kelompok_6.simalasnyuci.R;

public class PegawaiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pegawai);
        getSupportActionBar().hide();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.framePegawai, new DashboardPegawai());
        ft.commit();
    }
}
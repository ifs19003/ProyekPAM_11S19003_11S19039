package student.kelompok_6.simalasnyuci.pemilik;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import student.kelompok_6.simalasnyuci.R;
import student.kelompok_6.simalasnyuci.api.RESTClient;
import student.kelompok_6.simalasnyuci.model.User;

public class DashboardActivity extends AppCompatActivity {
    private ImageView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        profile = findViewById(R.id.usersProfile);
        User user = (User) getIntent().getSerializableExtra("user");
        Picasso.get()
                .load(RESTClient.BASE_URL + "uploads/users/" + user.getProfile())
                .placeholder(R.drawable.profil)
                .fit()
                .into(profile);

        LinearLayout menuLaundry = findViewById(R.id.menuLaundry);
        menuLaundry.setOnClickListener(v->{
            Intent intent = new Intent(DashboardActivity.this, LaundryActivity.class);
            startActivity(intent);
        });
        LinearLayout menuPegawai = findViewById(R.id.menuPegawai);
        menuPegawai.setOnClickListener(v->{
            Intent intent = new Intent(DashboardActivity.this, ListPegawaiActivity.class);
            startActivity(intent);
        });
        LinearLayout menuCucian = findViewById(R.id.menuCucian);
        menuCucian.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, TambahCucianActivity.class);
            startActivity(intent);
        });
    }
}
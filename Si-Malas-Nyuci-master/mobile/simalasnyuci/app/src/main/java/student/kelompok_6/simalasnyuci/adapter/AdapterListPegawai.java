package student.kelompok_6.simalasnyuci.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import student.kelompok_6.simalasnyuci.R;
import student.kelompok_6.simalasnyuci.model.User;

public class AdapterListPegawai extends RecyclerView.Adapter<AdapterListPegawai.Holder> {
    private Context context;
    private LinkedList<User> users;

    public AdapterListPegawai(Context context, LinkedList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_pegawai, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.nama.setText(users.get(position).getFull_name());
        holder.alamat.setText(users.get(position).getEmail());
        holder.noTelepon.setText(users.get(position).getPhone_number());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView nama, alamat, noTelepon;
        public Holder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.pegawaiName);
            alamat = itemView.findViewById(R.id.alamat);
            noTelepon = itemView.findViewById(R.id.noTelepon);
        }
    }
}

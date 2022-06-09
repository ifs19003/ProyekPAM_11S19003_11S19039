package student.kelompok_6.simalasnyuci.pegawai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import student.kelompok_6.simalasnyuci.R;
import student.kelompok_6.simalasnyuci.api.RESTClient;
import student.kelompok_6.simalasnyuci.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardPegawai#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardPegawai extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardPegawai() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardPegawai.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardPegawai newInstance(String param1, String param2) {
        DashboardPegawai fragment = new DashboardPegawai();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_pegawai, container, false);
        // Inflate the layout for this fragment
        User user = (User) getActivity().getIntent().getSerializableExtra("user");
        ImageView profile = view.findViewById(R.id.usersProfile);
        Picasso.get()
                .load(RESTClient.BASE_URL + "uploads/users/" + user.getProfile())
                .placeholder(R.drawable.profil)
                .fit()
                .into(profile);
        return view;
    }
}
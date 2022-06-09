package student.kelompok_6.simalasnyuci.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import student.kelompok_6.simalasnyuci.ResponseApi.AddSuccessResponse;
import student.kelompok_6.simalasnyuci.ResponseApi.DataGetResponse;
import student.kelompok_6.simalasnyuci.ResponseApi.DataManyResponse;
import student.kelompok_6.simalasnyuci.ResponseApi.LoginApiResponse;

public interface ApiSiMalasNyuci {

    @FormUrlEncoded
    @POST("api/login")
    Call<LoginApiResponse> loginApi(@Field("email") String email,
                  @Field("password") String password);
    @FormUrlEncoded
    @POST("api/register")
    Call<LoginApiResponse> registerApi(@Field("email") String email,
                                       @Field("password") String password,
                                       @Field("phone_number") String phoneNumber,
                                       @Field("full_name") String full_name,
                                       @Field("role") int role,
                                       @Field("kode") String kode);

    @GET("api/laundry/DJKFDFLMNJ?key=siallangjagal00011212")
    Call<DataGetResponse> getLaundry();
    @GET("api/pegawai/DJKFDFLMNJ?key=siallangjagal00011212")
    Call<DataManyResponse> getPegawais();

    @Multipart
    @POST("api/cucian?key=siallangjagal00011212")
    Call<AddSuccessResponse> addCucian(
            @Part("nama")RequestBody nama,
            @Part("harga")RequestBody harga,
            @Part("no_telepon")RequestBody no_telepon,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("kode_laundry") RequestBody kode_laundry,
            @Part("tanggal_selesai") RequestBody tanggal_selesai,
            @Part MultipartBody.Part file
            );

}

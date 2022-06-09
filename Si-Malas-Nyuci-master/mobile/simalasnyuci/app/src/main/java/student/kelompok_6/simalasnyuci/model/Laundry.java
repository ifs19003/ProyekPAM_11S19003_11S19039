package student.kelompok_6.simalasnyuci.model;

public class Laundry {
    private String kode_laundry;
    private String nama_laundry;
    private long id_pemilik;
    private String profile_laundry;
    private String alamat;
    private String deskripsi;
    private String created_at;
    private String update_at;

    public String getKode_laundry() {
        return kode_laundry;
    }

    public void setKode_laundry(String kode_laundry) {
        this.kode_laundry = kode_laundry;
    }

    public String getNama_laundry() {
        return nama_laundry;
    }

    public void setNama_laundry(String nama_laundry) {
        this.nama_laundry = nama_laundry;
    }

    public long getId_pemilik() {
        return id_pemilik;
    }

    public void setId_pemilik(long id_pemilik) {
        this.id_pemilik = id_pemilik;
    }

    public String getProfile_laundry() {
        return profile_laundry;
    }

    public void setProfile_laundry(String profile_laundry) {
        this.profile_laundry = profile_laundry;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }
}

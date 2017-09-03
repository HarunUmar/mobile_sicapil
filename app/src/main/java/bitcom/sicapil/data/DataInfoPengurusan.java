package bitcom.sicapil.data;

public class DataInfoPengurusan {

    public String id_pengurusan;
    public String nama;
    public String tgl_pengurusan;
    public String tgl_verifikasi;
    public String nama_kategori;
    public String jenis_pengurusan;
    public String status_verifikasi;

    public String get_id_pengurusan() { return id_pengurusan; }
    public String get_nama() { return nama; }
    public String get_tgl_pengurusan() { return tgl_pengurusan; }
    public String get_tgl_verifikasi() { return tgl_verifikasi; }
    public String get_nama_kategori() { return nama_kategori; }
    public String get_jenis_pengurusan() {return jenis_pengurusan;}
    public String get_status_verifikasi(){return status_verifikasi;}

    public void set_id_pengurusan(String id_pengurusan) { this.id_pengurusan = id_pengurusan; }
    public void set_nama(String nama) { this.nama = nama; }
    public void set_tgl_pengurusan(String tgl_pengurusan) { this.tgl_pengurusan = tgl_pengurusan; }
    public void set_tgl_verikasi(String tgl_verifikasi) { this.tgl_verifikasi = tgl_verifikasi; }
    public void set_nama_kategori(String nama_kategori) { this.nama_kategori =nama_kategori; }
    public void set_jenis_pengurusan(String jenis_pengurusan) { this.jenis_pengurusan = jenis_pengurusan; }
    public void set_status_verifikasi(String status_verifikasi) { this.status_verifikasi= status_verifikasi; }

}

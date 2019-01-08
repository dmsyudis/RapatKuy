package id.duakoma.com.meetsquare.http;

/**
 * Created by lenovo on 9/4/2018.
 */

public class Process extends Connection {
    // Source Code untuk URL -> URL menggunakan IP Address yang didapat dari komputer / laptop
//    String URL = " http://10.0.2.2/room/server-room.php";
    String URL = "http://rizkyernanda-septianid.000webhostapp.com/server.php";
    String url = "";
    String response = "";

    // Menampilkan biodata dari database
    public String getInfoData() {
        try {
            url = URL + "?operasi=view";
            System.out.println("URL Tampil : " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return response;
    }

    // Memasukkan data biodata baru ke database
    public String insertData(String day, String date, String time, String unit, String agenda, String pic) {

        try {
            url = URL + "?operasi=insert&day=" + day + "&date=" + date + "&time=" + time + "&unit="+ unit + "&agenda=" + agenda.replace(" ", "%20") + "&pic=" + pic.replace(" ", "%20");

            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return  response;
    }

    public String login(String username, String password){
        try{
            url = URL + "?operasi=login&username=" + username + "&password=" + password;
            System.out.println("URL Login : " + url);
            response = call(url);
        }
        catch (Exception e){
        }
        return response;
    }

    // Melihat biodata yang diinginkan melalui ID
    public String getDataByID (int id) {
        try {
            url = URL + "?operasi=get_biodata_by_id&id_room=" + id;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return response;
    }

    // Mengubah isi biodata yang sudah ada di database
    public String updateData (String id, String day, String date, String time, String unit, String agenda, String pic) {
        try {
            url = URL + "?operasi=update&id_room=" + id + "&day=" + day + "&date=" + date + "&time=" + time + "&unit="+ unit + "&agenda=" + agenda.replace(" ", "%20") + "&pic=" + pic.replace(" ", "%20");
            System.out.println("URL Update Biodata : " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return response;
    }

    // Menghapus salah satu biodata pada database
    public String deleteData (int id) {
        try {
            url = URL + "?operasi=delete&id_room=" + id;
            System.out.println("URL delete data : " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return response;
    }
    public String getDataRoom() {
        try {
            url = URL + "?operasi=ruangan";
            System.out.println("URL Tampil : " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return response;
    }
}

package id.duakoma.com.meetsquare;

/**
 * Created by lenovo on 9/9/2018.
 */

public class  MyData {
    private int id;
    private String description, image_link;
//    private String description, image_link, room_name;

    public MyData(int id, String description, String image_link) {
//    public MyData(int id, String description, String image_link, String room_name) {
        this.id = id;
        this.description = description;
        this.image_link = image_link;
        //this.room_name = room_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
/*
    public String getRoom_name(){
        return room_name;
    }

    public void setRoom_name(String room_name){
        this.room_name = room_name;
    }
    */
}

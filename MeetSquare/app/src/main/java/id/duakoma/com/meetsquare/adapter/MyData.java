package id.duakoma.com.meetsquare.adapter;

/**
 * Created by lenovo on 9/10/2018.
 */

public class MyData {
    private int id;
    private String room_name, image_link, description;

    public MyData(int id, String room_name, String image_link, String description) {
        this.id = id;
        this.room_name = room_name;
        this.image_link = image_link;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

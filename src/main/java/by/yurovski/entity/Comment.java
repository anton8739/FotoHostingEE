package by.yurovski.entity;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Comment {
    public Comment(){}

    public Comment(int id, String text,int userid, int fotoid, Timestamp timeOfCreation){
        this.id=id;
        this.text=text;
        this.userid=userid;
        this.fotoid=fotoid;
        this.timeOfCreation=timeOfCreation;
    }
    public Comment(String text,int userid, int fotoid){
        this.id=id;
        this.text=text;
        this.userid=userid;
        this.fotoid=fotoid;
        this.timeOfCreation=new Timestamp(System.currentTimeMillis());
    }

    private int id;

    private String text;

    private Timestamp timeOfCreation;

    private int userid;

    private int fotoid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(Timestamp timestamp) {
        this.timeOfCreation = timestamp;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getFotoid() {
        return fotoid;
    }

    public void setFotoid(int fotoid) {
        this.fotoid = fotoid;
    }
    public String getDate(){

        Date date= new Date(this.timeOfCreation.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
    public String getTime(){
        Date date= new Date(this.timeOfCreation.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
}

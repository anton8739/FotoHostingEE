package by.yurovski.entity;


import java.sql.Timestamp;

public class Foto {
    public Foto(){}
    public Foto(String name, String URL){
        this.name=name;
        this.URL=URL;
        this.timeOfCreation=new Timestamp(System.currentTimeMillis());
    }
    public Foto(String name, String URL, int userId, Timestamp timeOfCreation) {
        this.name = name;
        this.URL = URL;
        this.userId = userId;
        this.timeOfCreation = timeOfCreation;
    }
    public Foto(int id, String name, String URL, String text, String title, int userId, Timestamp timeOfCreation) {
        this.id = id;
        this.name = name;
        this.URL = URL;
        this.text = text;
        this.title = title;
        this.userId = userId;
        this.timeOfCreation = timeOfCreation;
    }

    private int id;

    private String name;

    private String URL;

    private String text;

    private String title;
    private int userId;

    private Timestamp timeOfCreation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getTimeOfCreation() {
        return timeOfCreation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTimeOfCreation(Timestamp timestamp) {
        this.timeOfCreation = timestamp;
    }
}

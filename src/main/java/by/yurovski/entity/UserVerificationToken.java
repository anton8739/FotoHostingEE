package by.yurovski.entity;


import java.util.Date;

public class UserVerificationToken {


    public UserVerificationToken(){}
    public UserVerificationToken(User user, String token, Date date){
        this.userId=user.getId();
        this.token=token;
        this.expiryDate=calculateExpiryDate(date);
    }
    public UserVerificationToken(int id, String token, long expiryDate, int userId){
        this.id=id;
        this.userId=userId;
        this.token=token;
        this.expiryDate=expiryDate;
    }
    private int id;

    private String token;

    private long expiryDate;

    private  int userId;


    private long calculateExpiryDate(Date date){
        long time=(long) 8.64e5+date.getTime();

        return time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(long expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

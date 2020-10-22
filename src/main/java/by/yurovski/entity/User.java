package by.yurovski.entity;


import by.yurovski.enums.UserStatusEnum;

import java.sql.Timestamp;

public class User{


    private int id;

    private String name;

    private String surname;

    private String login;

    private String password;

    private String status;

    private String email;

    private String mobPhone;


    private Timestamp timeOfRegistration;

    private boolean enabled;

    public User (){

    }
    public User (int id, String name, String surname, String email,
                 String login, String password, String mobPhone,
                 Timestamp timeOfRegistration, boolean enabled, UserStatusEnum status){
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.login=login;
        this.password=password;
        this.mobPhone=mobPhone;
        this.timeOfRegistration=timeOfRegistration;
        this.enabled=enabled;
        this.status=status.toString();
    }
    public User(String email, String login, String password){
        this.email=email;
        this.login=login;
        this.password=password;
        this.timeOfRegistration=new Timestamp(System.currentTimeMillis());
        this.enabled=false;
        this.status=UserStatusEnum.CLIENT.toString();
    }
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobPhone() {
        return mobPhone;
    }

    public void setMobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
    }

    public Timestamp getTimeOfRegistration() {
        return timeOfRegistration;
    }

    public void setTimeOfRegistration(Timestamp timestamp) {
        this.timeOfRegistration = timestamp;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

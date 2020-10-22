package by.yurovski.entity;



public class LikeFoto {

    public LikeFoto(){}

    public LikeFoto(int userWhoLikedId, int fotoWhichLikedId){
        this.userWhoLikedId=userWhoLikedId;
        this.fotoWhichLikedId=fotoWhichLikedId;

    }
    public LikeFoto(int id,int userWhoLikedId, int fotoWhichLikedId){
        this.id=id;
        this.userWhoLikedId=userWhoLikedId;
        this.fotoWhichLikedId=fotoWhichLikedId;

    }

    private int id;

    private int userWhoLikedId;

    private int fotoWhichLikedId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserWhoLikedId() {
        return userWhoLikedId;
    }

    public void setUserWhoLikedId(int userWhoLikedId) {
        this.userWhoLikedId = userWhoLikedId;
    }

    public int getFotoWhichLikedId() {
        return fotoWhichLikedId;
    }

    public void setFotoWhichLikedId(int fotoWhichLikedId) {
        this.fotoWhichLikedId = fotoWhichLikedId;
    }
}

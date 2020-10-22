package by.yurovski.entity;


public class LikeComment {
    public LikeComment(){}

    public LikeComment(int userWhoLikedId, int commentWhichLikedId){

        this.userWhoLikedId=userWhoLikedId;
        this.commentWhichLikedId=commentWhichLikedId;

    }
    public LikeComment(int id,int userWhoLikedId, int commentWhichLikedId){
        this.id=id;
        this.userWhoLikedId=userWhoLikedId;
        this.commentWhichLikedId=commentWhichLikedId;

    }

    private int id;

    private int userWhoLikedId;

    private int commentWhichLikedId;

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

    public int getCommentWhichLikedId() {
        return commentWhichLikedId;
    }

    public void setCommentWhichLikedId(int commentWhichLikedId) {
        this.commentWhichLikedId = commentWhichLikedId;
    }
}

package by.yurovski.command;

import by.yurovski.command.comment.AddNewCommentCommand;
import by.yurovski.command.comment.DeleteCommentCommand;
import by.yurovski.command.comment.LikeCommentCommand;
import by.yurovski.command.comment.SortCommentCommand;
import by.yurovski.command.foto.*;
import by.yurovski.command.redirect.*;
import by.yurovski.command.user.*;
import by.yurovski.enums.CommandEnum;

public class CommandFactory {

    public  static Command defineCommand(String commandName){
        Command command;
        CommandEnum currentEnum = CommandEnum.valueOf(commandName.toUpperCase().replace('-', '_'));
        switch (currentEnum){
            case EMPTY:
                command=EmptyCommand.getInstance();
                break;

            case REGISTRATION_PAGE_REDIRECT:
                command=RegistrationPageRedirectCommand.getInstance();
                break;
            case IMG_PAGE_REDIRECT:
                command=AddNewImgPageRedirectCommand.getInstance();
                break;
            case FOLLOWERS_REDIRECT:
                command=FollowerPageRedirectCommand.getInstance();
                break;
            case FOLLOWINGS_REDIRECT:
                command=FollowingRedirectCommand.getInstance();
                break;
            case LOGIN_PAGE_REDIRECT:
                command=LoginPageRedirectCommand.getInstance();
                break;
            case USER_ACCOUNT_REDIRECT:
                command=UserAccountRedirectCommand.getInstance();
                break;
            case USERLIST_PAGE_REDIRECT:
                command=UserListPageRedirectCommand.getInstance();
                break;
            case FOTO_PAGE_REDIRECT:
                command=FotoPageRedirect.getInstance();
                break;
            case ERROR:
                command=ErrorPageRedirectCommand.getInstance();
                break;
            case DELETE_ACCOUNT:
                command=DeleteAccountCommand.getInstance();
                break;
            case FOLLOW_UNFOLLOW:
                command=FollowCommand.getInstance();
                break;
            case LOG_IN:
                command=LoginCommand.getInstance();
                break;
            case LOG_OUT:
                command=LogoutCommand.getInstance();
                break;
            case REGISTRATION:
                command=RegistrationCommand.getInstance();
                break;
            case  USER_SORT:
                command=SortUserCommand.getInstance();
                break;
            case ADD_NEW_IMG:
                command=AddNewImgCommand.getInstance();
                break;
            case DELETE_IMG:
                command=DeleteImgCommand.getInstance();
                break;
            case LIKE_IMG:
                command=LikeImgCommand.getInstance();
                break;
            case FOTO_SORT:
                command=SortFotoCommand.getInstance();
                break;
            case ADD_NEW_COMMENT:
                command=AddNewCommentCommand.getInstance();
                break;
            case DELETE_COMMENT:
                command=DeleteCommentCommand.getInstance();
                break;
            case LIKE_COMMENT:
                command=LikeCommentCommand.getInstance();
                break;
            case COMMENT_SORT:
                command=SortCommentCommand.getInstance();
                break;
            case CHANGE_LOCALE:
                command=ChangeLocaleCommand.getInstance();
                break;
            case FORGOT_PASSWORD:
                command=ForgotPasswordCommand.getInstance();
                break;
            case EDIT_FOTO:
                command=EditFotoCommand.getInstance();
                break;
            case EDIT_USER:
                command=EditUserCommand.getInstance();
                break;
            default: {
                command=EmptyCommand.getInstance();
            }
        }

        return command;
    }
}

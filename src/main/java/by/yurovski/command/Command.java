package by.yurovski.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    default String execute (HttpServletRequest request){
        return null;
    };
    default void executeJSON(HttpServletRequest request, HttpServletResponse response){

    }
}

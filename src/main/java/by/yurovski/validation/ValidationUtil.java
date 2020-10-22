package by.yurovski.validation;

import by.yurovski.entity.User;
import by.yurovski.entity.UserVerificationToken;
import by.yurovski.exception.RegistrationException;
import by.yurovski.exception.ServiceException;
import by.yurovski.manager.PageContentManager;

import by.yurovski.service.UserService;
import by.yurovski.service.UserVerificationTokenService;

import java.util.Date;


public class ValidationUtil {

    public static void registrationValidator(User user) throws RegistrationException, ServiceException {

            if (UserService.getInstance().getUserByEmail(user.getEmail()) != null) {
                throw new RegistrationException(PageContentManager.getProperty("error.registration.email2"));
            }
            if (UserService.getInstance().getUserByLogin(user.getLogin()) != null) {
                throw new RegistrationException(PageContentManager.getProperty("error.registration.login2"));
            }
            if (user.getEmail().matches("^{4,30}$")){
                throw new RegistrationException(PageContentManager.getProperty("error.registration.email1"));
            }
            if (!user.getLogin().matches("^[a-zA-Z0-9_]{4,30}$")) {
                throw new RegistrationException(PageContentManager.getProperty("error.registration.login1"));
            }
            if (!user.getPassword().matches("^[a-zA-Z0-9_]{4,30}$")) {
                throw new RegistrationException(PageContentManager.getProperty("error.registration.password"));
            }
            if (user.getName()!=null && !user.getName().matches("^[a-zA-Z0-9_]{1,50}$")){
            throw new RegistrationException(PageContentManager.getProperty("error.edit.name"));
            }
            if (user.getSurname() !=null && !user.getSurname().matches("^[a-zA-Z0-9_]{1,50}$")){
            throw new RegistrationException(PageContentManager.getProperty("error.edit.surname"));
            }

    }

    public static User checkToken(String token ) throws RegistrationException{
            User user=null;
        try {

                UserVerificationToken userVerificationToken=UserVerificationTokenService.getInstance().getTokenByToken(token);
                if (userVerificationToken.getExpiryDate()<new Date().getTime()){
                    throw new RegistrationException(PageContentManager.getProperty("error.registration.token3"));
                }
                user=UserService.getInstance().getUserById(userVerificationToken.getUserId());


                UserVerificationTokenService.getInstance().delete(userVerificationToken);

            } catch (ServiceException e){
                throw new RegistrationException(PageContentManager.getProperty("error.registration.token1"));
            }
            return user;
    }
    public static void editValidator(User user) throws RegistrationException {
        if (user.getEmail().matches("^{4,30}$")){
            throw new RegistrationException(PageContentManager.getProperty("error.registration.email1"));
        }
        if (!user.getLogin().matches("^[a-zA-Z0-9_]{4,30}$")) {
            throw new RegistrationException(PageContentManager.getProperty("error.registration.login1"));
        }
        if (!user.getPassword().matches("^[a-zA-Z0-9_]{4,30}$")) {
            throw new RegistrationException(PageContentManager.getProperty("error.registration.password"));
        }
        if (user.getName()!=null && !user.getName().matches("^[a-zA-Z0-9_]{1,50}$")){
            throw new RegistrationException(PageContentManager.getProperty("error.edit.name"));
        }
        if (user.getSurname() !=null && !user.getSurname().matches("^[a-zA-Z0-9_]{1,50}$")){
            throw new RegistrationException(PageContentManager.getProperty("error.edit.surname"));
        }
    }

}

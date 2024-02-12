package yongtaek.miniproject.justchatting.exception;


public class UserNotFoundException extends HttpErrorException{
    private String message = "user not found";

    public UserNotFoundException(String message){
        super(404, message);
    }
}

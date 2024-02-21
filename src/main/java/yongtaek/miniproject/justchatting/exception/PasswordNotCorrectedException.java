package yongtaek.miniproject.justchatting.exception;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PasswordNotCorrectedException extends HttpErrorException{
    private String message = "password not corrected";

    public PasswordNotCorrectedException(String message){
        super(404, message);
    }
}

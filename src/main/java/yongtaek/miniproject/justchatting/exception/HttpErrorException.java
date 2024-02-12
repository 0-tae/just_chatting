package yongtaek.miniproject.justchatting.exception;


// TODO: HttpResponse에 ok 항목을 매번 넣으려면 코드를 어떻게 작성해야 하는가?

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HttpErrorException extends RuntimeException{
    private int statusCode = 500;
    private String message = "undefined server error";

    public HttpErrorException(int statusCode, String message){
        super(message);
        this.statusCode=statusCode;
        this.message=message;
    }
}

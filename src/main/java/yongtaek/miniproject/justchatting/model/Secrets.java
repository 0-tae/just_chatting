package yongtaek.miniproject.justchatting.model;

import org.springframework.beans.factory.annotation.Value;

public class Secrets {

    @Value("${secret.x-api-token}")
    public static String X_API_TOKEN;

}

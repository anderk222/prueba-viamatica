package viamatica.prueba.domain;

import lombok.Getter;

@Getter
public class ResponseToken extends Response {
    
   String username;
   String token;

    public ResponseToken(String user,String token, String message){
      super(message);
        this.token = token;
        this.username= user;

    }
}

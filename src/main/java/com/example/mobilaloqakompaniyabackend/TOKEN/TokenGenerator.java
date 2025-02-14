package com.example.mobilaloqakompaniyabackend.TOKEN;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Lavozim;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class TokenGenerator {
    String kalit="1234";

    public String TokenOlish(String username, Lavozim lavozim){
        long vaqt=60*60*100*24;
        Date yashashVaqti=new Date(System.currentTimeMillis()+vaqt);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(yashashVaqti)
                .signWith(SignatureAlgorithm.HS512, kalit)
                .claim("Lavozim",lavozim.getNomi())
                .compact();
        return token;

    }
    public boolean TokenTekshirish(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(kalit)
                    .parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public String Deshifrlash(String token) {
        String username = Jwts
                .parser()
                .setSigningKey(kalit)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        System.out.println(username);
        return username;
    }
}

package com.example.bookstoreapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {


    private static final String TOKEN_SECRET = "Warlock";

    public String createToken(int id)   {
        try {
            //to set algorithm
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            String token = JWT.create()
                    .withClaim("UserID", id)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
                  } catch (IllegalArgumentException e) {

            e.printStackTrace();
        }
        return null;
    }


      public int decodeToken(String token)
    {
        int UserID;
        //for verification algorithm
        Verification verification = null;
        try {
            verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException  e) {

            e.printStackTrace();
        }
        JWTVerifier jwtverifier=verification.build();
        //to decode token
        DecodedJWT decodedjwt=jwtverifier.verify(token);

        Claim claim=decodedjwt.getClaim("UserID");
        UserID=claim.asInt();
        return UserID;

    }
}

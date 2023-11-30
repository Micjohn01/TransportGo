package michaelJohn.transportgo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import michaelJohn.transportgo.data.dtos.request.Location;
import michaelJohn.transportgo.exception.BusinessLogicException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;

public class AppUtilities {
    public static final int NUMBER_OF_ITEMS_PER_PAGE = 3;
    private static final String USER_VERIFICATION_BASE_URL="localhost:8080/api/v1/user/account/verify";
    private static final String WELCOME_MAIL_TEMPLATE_LOCATION = "ggg";
    public static final String EMAIL_REGEX_STRING="ggg";
    public static final String ADMIN_INVITE_MAIL_TEMPLATE_LOCATION="ggg";
    public static final String JSON_CONSTANT="json";
    public static final String TRANSPORT_MODE="driving";
    public static final String UBER_DELUXE_TEST_IMAGE="ggg";
    public static String getAdminMailTemplate() {
        try (BufferedReader reader = new BufferedReader(new FileReader(
            WELCOME_MAIL_TEMPLATE_LOCATION))){
            return reader.lines().collect(Collectors.joining());
        }catch (IOException exception){
            throw new BusinessLogicException(exception.getMessage());
        }
    }

    public static String generateVerificationLink(Long userId){
        return USER_VERIFICATION_BASE_URL+"?userId="+userId+"&token="+generateVerificationToken();
    }

    private static String generateVerificationToken() {
        return Jwts.builder()
                .setIssuer("transportGo")
                .signWith(SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode("hvyduh=")) //TODO still a dummy
                .setIssuedAt(new Date())
                .compact();
    }

    public static boolean isValidToken(String token){
        return Jwts.parser()
                .isSigned(token);
    }

    public static String buildLocation(Location location){
        return location.getHouseNumber() + "," + location.getClass() + "," + location.getCity()+location.getState();
    }

    public static BigDecimal calculateRideFare(String distance){
        return BigDecimal
                .valueOf(Double.parseDouble(distance.split("k")[0]))
                .multiply(BigDecimal.valueOf(1000));
    }
}

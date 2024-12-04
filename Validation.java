package newtry;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean validName(String name) {
        String nameR = "^[A-Za-z]+$";
        Pattern p = Pattern.compile(nameR);
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public static boolean validEmail(String email) {
        String emailR = "^[A-Za-z0-9_-]+@(yahoo|gmail).com";
        Pattern p = Pattern.compile(emailR);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean validPass(String pass) {
        String passR = "^.{6,}";
        Pattern p = Pattern.compile(passR);
        Matcher m = p.matcher(pass);
        return m.matches();
    }

    public static boolean validDate(String date) {
        try {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate d = LocalDate.parse(date, f);
            String r = d.format(f);

            if (!(1940 < d.getYear() && d.getYear() < LocalDate.now().getYear())) {
                return false;
            } else if (LocalDate.now().getYear() - d.getYear() < 8) {
                return false;
            } else {
                return true;
            }
        } catch (DateTimeParseException e) {

            return false;
        }
    }
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
   
}

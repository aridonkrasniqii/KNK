package processor;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class SecurityHelper {

    public static String generateSalt() {
        StringBuffer sb = new StringBuffer();
        SecureRandom rand = new SecureRandom();
        for (int i = 0; i < 8; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public static String computeHash(String pwd, String salt) throws Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest((pwd + salt).getBytes());

        StringBuffer sb = new StringBuffer();

        for (byte b : digest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}

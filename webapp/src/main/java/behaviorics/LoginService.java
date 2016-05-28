package behaviorics;

import behaviorics.models.User;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LoginService {

    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    //Check against database
    public boolean validUser(User user){

        byte[] result = salt(user.getKey(), user.getSalt());

        if(Arrays.equals(user.getHash(), result))
            return true;
        else
            return false;
    }

    // Salt password - user verification
    public byte[] salt(String password, byte[] salt) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKey key = skf.generateSecret(spec);
            byte[] result = key.getEncoded();
            return result;
        }
        catch(Exception e) {
            return null;
        }
    }
}

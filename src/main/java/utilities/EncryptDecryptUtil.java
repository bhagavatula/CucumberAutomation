package utilities;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.Base64;

import static java.util.Base64.getEncoder;

@Component
public class EncryptDecryptUtil {
    private static final Logger logger = Logger.getLogger(EncryptDecryptUtil.class.getName());
    private static SecretKeySpec secretKey;
    private static String scre_key = "F391EEEBD027BA93";
    private static byte[] key;

    public EncryptDecryptUtil() {
    }

    public static void setKey(String myKey) {
        key = myKey.getBytes(StandardCharsets.UTF_8);
        secretKey = new SecretKeySpec(key, "AES");
    }

    public static String encrypt(String strToEncrypt) {
        try {
            setKey(scre_key);
            Cipher cipher = Cipher.getInstance("?AES/ECB/PKCS5Padding");
            cipher.init(1, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception var2) {
            System.out.println("Error while encrypting:" + var2.toString());
            return null;
        }
    }

    public static String decrypt(String strToDecrypt){
        try{
            setKey(scre_key);
            Cipher cipher = Cipher.getInstance("?AES/ECB/PKCS5Padding");
            cipher.init(1, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception var2) {
        System.out.println("Error while decrypting:" + var2.toString());
        return null;
    }

}



}

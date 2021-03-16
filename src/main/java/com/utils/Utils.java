package utils;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import java.nio.charset.StandardCharsets;

import java.util.Base64;
import java.util.Date;

public class Utils {

    public static byte[] decodeStringToBase64(String string) {
            return Base64.getDecoder().decode(string);
    }

    public static String encodeByteToBase64(byte[] byteToEncode){
        return Base64.getEncoder().encodeToString(byteToEncode);
    }

    public static long getTimestamp(){
        return new Date().getTime() / 1000;
    }

    public static String generateHmac(String publicKey, String privateKey) {
        byte[] privateKeyBytes = decodeStringToBase64(privateKey);
        String bodySignature = "";
        long timestamp = getTimestamp();
        long nonce = System.currentTimeMillis();
        String unsignedSignature = publicKey + ":" + nonce + ":" + timestamp + ":" + bodySignature;

        byte[] utf8Signature = unsignedSignature.getBytes(StandardCharsets.UTF_8);
        byte[] hashedSignature = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, privateKeyBytes).doFinal(utf8Signature);
        String signedSignature = encodeByteToBase64(hashedSignature);

        String hmac = publicKey + ":" + nonce + ":" + timestamp + ":" + signedSignature;

        System.out.println("Hmac is: " + hmac);

        return hmac;
    }
}

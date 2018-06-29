package com.lipenglong.passport.cas;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

/**
 * passport-cas rsa加密工具类
 * <p/>
 *
 * @author lipenglong
 * @since 1.0-SNAPSHOT
 */
public class RSAUtil {
    private static final String ALGORITHM = "RSA";
    private static final String CHARSET = "UTF-8";

    public static Map<String, String> createKeys(int keySize) {
        Map<String, String> keyPairMap = new HashMap<>();
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        keyPairGenerator.initialize(keySize);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64Utils.encodeToString(publicKey.getEncoded());
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64Utils.encodeToString(privateKey.getEncoded());
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);
        return keyPairMap;
    }

    public static RSAPublicKey getPublicKey(String publicKeyStr) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64Utils.decodeFromString(publicKeyStr));
            return (RSAPublicKey) keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static RSAPrivateKey getPrivateKey(String privateKeyStr) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(privateKeyStr));
            return (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String date, RSAPublicKey key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(ENCRYPT_MODE, key);
            return Base64Utils.encodeToString(
                    splitCodec(cipher, ENCRYPT_MODE, date.getBytes(CHARSET), key.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String data, RSAPrivateKey key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(DECRYPT_MODE, key);
            return new String(splitCodec(cipher, DECRYPT_MODE,
                    Base64Utils.decodeFromString(data), key.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] splitCodec(Cipher cipher, int opMode, byte[] datas, int keySize) throws Exception {
        int maxBlock;
        if (opMode == DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buffer;
        int i = 0;
        while (datas.length > offSet) {
            if (datas.length - offSet > maxBlock) {
                buffer = cipher.doFinal(datas, offSet, maxBlock);
            } else {
                buffer = cipher.doFinal(datas, offSet, datas.length - offSet);
            }
            outputStream.write(buffer, 0, buffer.length);
            i++;
            offSet = i * maxBlock;
        }
        byte[] result = outputStream.toByteArray();
        outputStream.close();
        return result;
    }
}

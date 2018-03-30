package com.lipenglong.passport.web.util;

import org.junit.Test;

import java.util.Map;

/**
 * RSAUtil测试类
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
public class RSAUtilTest {
    @Test
    public void test() throws Exception {
        Map<String, String> keyMap = RSAUtil.createKeys(1024);
        String publicKey = keyMap.get("publicKey");
        String privateKey = keyMap.get("privateKey");
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);

        String password = "123456";
        System.out.println("明文密码：" + password);
        String encodedPwd = RSAUtil.encrypt(password, RSAUtil.getPublicKey(publicKey));
        System.out.println("密文密码：" + encodedPwd);

        String decodedPwd = RSAUtil.decrypt(encodedPwd, RSAUtil.getPrivateKey(privateKey));
        System.out.println("解密后的密码：" + decodedPwd);
    }

    @Test
    public void testDecrypt() throws Exception {
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOiIrkM1FOUKeVjlkB3ymH8n44z7usUDgY3rBMPTKlm+ilw0r2pYx4IsmJU6OBahmNqt0rX5vozo4NuCwDbEh1ZTPHEEELLOOBPr19fzm+o72YUhKr9WLk4vfjtfzcEnx7whSVus8UW4hEadhf3NJA/yPZcPOXSmvILNOAg/FveFAgMBAAECgYATIau0u9lMPuBdAAYrcJo7W/zMzKlBLFisZHmv4t/JInQfwfXsugEwJJspK/ERa5o347VCY5nZ3g8iAE/wMZswxt8Y55aS9udBvYjKFqNgD5o85kXrjwYth7z89gy56gqm89kEXe9qqAGghEqG74/igftotg0W3nQv9aFo+JfAAQJBAPpb61wc/S0X62vE2S61H4L8uUZ9GargNFITEOAezC8+6iKNuZNFDDAfuDQpimZM3ex9pEsvUy6F1dOhRh2iGIUCQQDtxfH3vUYU3ainE2SWrwUSxWnV/JAqFUqUwMhcR6WlhDcZUmmOenCiZavBtxM1I9I3/DZOBPWfMKyCQoEWBxMBAkEArxpNIg7QcJKTQLvcHtPlZ0r1Frz2j9HiJilZmzdPHhhFz8cKLVuUq6/zuN/5AZXvfbFekItYBWjr5emdTaqKtQJAKNhKGp6e/VKeeCTysJfcLT9QUPwT38bMiwUa5g9Au1AdDvKDQ4Bw/NSuTpaebBB9OjTPN1POxcvctnuqJRiOAQJBAL7vVKUdhN0nKzfUmqhCdpyoCSlB7xwZX/CYwFWgvIOTldPwBkRX9COigTUt3LClBtak4hoIyTjDTzIyqCoqtO4=";
        String pwd = "odGbWRTwHmHi0WxJX2f5DS1AkWPLDV5JlZldRV0LSk1j+0J9ILLlmxyfzM8D09GtDAg4ttAPNfUEw/cbcriWqwUwd8mOVu2dpuqL4CaFLo7DpSZ1dLQLqbR7kbfAT/sppFGhTMajgAFCabgqMXkjvAdpnxeO5xoEduMkPYiiHks=";

        String password = RSAUtil.decrypt(pwd, RSAUtil.getPrivateKey(privateKey));
        System.out.println(password);
    }
}
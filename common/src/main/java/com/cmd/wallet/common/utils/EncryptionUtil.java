package com.cmd.wallet.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Sha2Crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//加密工具类
public class EncryptionUtil {
    public static String pwd(String str) {
        return DigestUtils.sha256Hex(str);
    }

    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }

    public static boolean checkMD5(String input, String password){
        return  password.equals(MD5(input));
    }

    public static String[] getEncryptPassword(String password) {
        String salt = B64Copy.getRandomSaltForSha256();
        return new String[]{Sha2Crypt.sha256Crypt(password.getBytes(), salt), salt};
    }

    public static boolean check(String input, String password, String salt) {
        return password.equals(Sha2Crypt.sha256Crypt(input.getBytes(), salt));
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0 ;i < 10000; ++i) {
            System.out.println(Sha2Crypt.sha256Crypt("123456".getBytes(), "$5$BKMxK0alsFAzfJnQ"));
        }

        long end = System.currentTimeMillis();

        System.out.println("total processed per second: " + 10000 * 1000 / (end - start));
    }
}

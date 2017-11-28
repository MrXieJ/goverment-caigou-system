package com.jybl.admin.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class EncryptUtils {

    private static final String PASSWORD_CRYPT_KEY = "88444488";
    private final static String DES = "DES";

    public final static String md5(String src) {
        return encrypt(src, "md5");
    }

    public final static String sha(String src) {
        return encrypt(src, "sha-1");
    }

    private final static String encrypt(String src, String algorithmName) {
        if (src == null || "".equals(src.trim())) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        if (algorithmName == null || "".equals(algorithmName.trim())) {
            algorithmName = "md5";
        }
        String encryptText = null;
        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(src.getBytes("UTF8"));
            byte s[] = m.digest();
            // m.digest(src.getBytes("UTF8"));
            return hex(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptText;
    }

    private final static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }
}

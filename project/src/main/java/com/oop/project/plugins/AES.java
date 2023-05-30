package com.oop.project.plugins;

import com.oop.project.Plugin;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AES implements Plugin {
    private final SecretKeySpec secretKey = aesKey;
    private byte[] initializationVector;
    private Cipher cipher;

    public static SecretKeySpec aesKey = new SecretKeySpec(
            "qwertyasdfghzxcv".getBytes(), "AES"
    );


    public AES() {
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            initializationVector = new byte[16];
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getExt() {
        return "*.aes";
    }

    @Override
    public String getDescr() {
        return "AES code";
    }

    @Override
    public byte[] encrypt(byte[] data) {
        try {
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] decrypt(byte[] data) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, cipher.getParameters());
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String str ="Hello world";
        AES aes = new AES();
        System.out.println(Arrays.toString(str.getBytes(StandardCharsets.UTF_8)));
        byte[] data = str.getBytes(StandardCharsets.UTF_8);
        data = aes.encrypt(data);
        System.out.println(Arrays.toString(data));
        data = aes.decrypt(data);
        System.out.println(Arrays.toString(data));
    }
}

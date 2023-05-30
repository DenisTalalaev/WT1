package com.oop.project.plugins;

import com.oop.project.Plugin;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DES implements Plugin {
    private final SecretKeySpec secretKey = desKey;
    private byte[] initializationVector;
    private Cipher cipher;

    public DES() {
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            initializationVector = new byte[8];
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SecretKeySpec desKey = new SecretKeySpec(
            "dajwlwlDLCMW;ald".getBytes(), "AES"
    );

    @Override
    public String getExt() {
        return "*.des";
    }

    @Override
    public String getDescr() {
        return "DES code";
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
        System.out.println(Arrays.toString(str.getBytes(StandardCharsets.UTF_8)));
        byte[] data = str.getBytes(StandardCharsets.UTF_8);
        data = aes.encrypt(data);
        System.out.println(Arrays.toString(data));
        data = aes.decrypt(data);
        System.out.println(Arrays.toString(data));
    }
}

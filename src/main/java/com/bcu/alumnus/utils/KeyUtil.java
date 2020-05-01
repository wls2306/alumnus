package com.bcu.alumnus.utils;

import cn.hutool.crypto.SecureUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.util.Arrays;


import static sun.security.x509.CertificateAlgorithmId.ALGORITHM;

public class KeyUtil {

    public static void main(String[] args) {
        generateKey();
    }


    public static void generateKey() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            KeyPair pair = keyPairGen.generateKeyPair();

            ClassPathResource pub = new ClassPathResource("key.pub");
            ClassPathResource pri = new ClassPathResource("key.pri");

            File privateKeyFile = new File(pub.getPath());
            File publicKeyFile = new File(pri.getPath());
            if (privateKeyFile.getParentFile() != null) {
                privateKeyFile.getParentFile().mkdirs();
            }

            privateKeyFile.createNewFile();
            if (publicKeyFile.getParentFile() != null) {
                publicKeyFile.getParentFile().mkdirs();
            }
            publicKeyFile.createNewFile();
            FileOutputStream pubOut = new FileOutputStream(pub.getPath());
            System.out.println("pub:"+Base64.encodeBase64String(pair.getPublic().getEncoded()));
            pubOut.write(Base64.encodeBase64(pair.getPublic().getEncoded()));
            pubOut.close();

            FileOutputStream priOut = new FileOutputStream(pri.getPath());
            System.out.println("pri:"+Base64.encodeBase64String(pair.getPrivate().getEncoded()));
            priOut.write(Base64.encodeBase64(pair.getPrivate().getEncoded()));
            priOut.close();

            FileInputStream inputStream=new FileInputStream(new File("C:/alumnus-res/1.pri"));
            byte[] b=new byte[1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            int n;
            while ((n = inputStream.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            byte[] data = bos.toByteArray();
            inputStream.close();

            System.out.println("read-pri:"+new String(data));





        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

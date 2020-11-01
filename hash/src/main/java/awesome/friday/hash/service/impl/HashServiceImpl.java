package awesome.friday.hash.service.impl;

import awesome.friday.dubbo.service.HashService;
import com.alibaba.dubbo.config.annotation.Service;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;

/**
 * @author yiran
 * @date: 20.10.31 15:07
 */
@Service(interfaceClass = HashService.class)
@Component
public class HashServiceImpl implements HashService {
    @Override
    public String sayHello(String name) {
        System.out.println("你终于来了");
        return "Hello " + name;
    }

    @Override
    public String[] hash(String password) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        // 获取SM3 杂凑算法实例
        MessageDigest sm3Digest = null;
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        try {
            sm3Digest = MessageDigest.getInstance("SM3", "BC");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 安全随机数生成器
        SecureRandom secureRandom = new SecureRandom();
        byte[] saltBin = new byte[128];
        // 生成一个128Byte长的随机数
        secureRandom.nextBytes(saltBin);
        // 将其Hash 为256bit的字节串
        saltBin = sm3Digest.digest(saltBin);
        sm3Digest.reset();
        // 将盐值编码为16进制字符串
        String salt = Hex.toHexString(saltBin);

        /*
         * 向缓存依次写入
         * 口令原文的字节串
         * 盐值字节串
         */
        try {
            buffer.write(password.getBytes(StandardCharsets.UTF_8));
            buffer.write(saltBin);
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 口令 = Hash（口令 + 盐值）
        byte[] passwordBin = sm3Digest.digest(buffer.toByteArray());
        // 将口令编码为16进制字符串
        password = Hex.toHexString(passwordBin);
        return new String[]{password, salt};
    }

    @Override
    public Boolean isRight(String pwd, String comparPwd,String salt) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            /*
             * 数据中存储的口令和盐值 HEX to BYTE
             */
            byte[] targetPwdBin = Hex.decode(comparPwd);
            byte[] targetSaltBin = Hex.decode(salt);

            /*
             * 向缓存依次写入
             * 要比对的口令原文的字节串
             * 存储的盐值字节串
             */
            buffer.write(pwd.getBytes(StandardCharsets.UTF_8));
            buffer.write(targetSaltBin);
            buffer.close();

            // 获取SM3 杂凑算法实例
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            MessageDigest sm3Digest = MessageDigest.getInstance("SM3", "BC");
            // 计算出此时的杂凑值
            byte[] cmpPwdHash = sm3Digest.digest(buffer.toByteArray());
            // 比对字节是否完全一致
            return Arrays.equals(targetPwdBin, cmpPwdHash);
        } catch (NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
            // 内存操作不会发生异常，保险起见还是抛出运行时异常
            throw new RuntimeException(e);
        }
    }

}

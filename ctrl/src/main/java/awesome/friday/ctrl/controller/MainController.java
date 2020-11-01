package awesome.friday.ctrl.controller;

import awesome.friday.ctrl.entity.User;
import awesome.friday.ctrl.feign.HomeClient;
import awesome.friday.dto.LoginDto;
import awesome.friday.dto.R;
import awesome.friday.dubbo.service.HashService;
import awesome.friday.log.LogWriter;
import com.alibaba.dubbo.config.annotation.Reference;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author yiran
 * @date: 20.11.1 14:41
 */
@RestController
public class MainController {

    @Autowired
    private HomeClient homeClient;

    @Reference
    HashService hashService;

    @Resource
    private Producer captchaProducer;

    @Autowired
    private RedisTemplate<String, String> redisTemplateStringString;

    @LogWriter
    @PostMapping("/login")
    public R login(@RequestBody LoginDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String uuid = dto.getUuid();
        String text = dto.getText();


        if (!StringUtils.hasText(username)) {
            return R.errMsg("username is required");
        }
        if (!StringUtils.hasText(password)) {
            return R.errMsg("password is required");
        }
        if (!StringUtils.hasText(uuid)) {
            return R.errMsg("uuid is required");
        }
        if (!StringUtils.hasText(text)) {
            return R.errMsg("verification code is required");
        }


        String generateCode = redisTemplateStringString.opsForValue().get(uuid);

        if (!StringUtils.hasText(generateCode)) {
            return R.errMsg("verification code is expire");
        }
        if (!generateCode.equals(text)) {
            return R.errMsg("error verification code");
        }

        redisTemplateStringString.delete(uuid);

        User user = homeClient.user(username);
        if (user == null) {
            return R.errMsg("this person is disappear");
        }
        if (hashService.isRight(password, user.getPassword(), user.getSalt())) {
            return R.ok(user.getUsername());
        } else {
            return R.errMsg("please try your password again");
        }
    }

    @LogWriter
    @PostMapping("/new")
    public R create(@RequestBody User u) {
        String username = u.getUsername();
        String password = u.getPassword();

        if (!StringUtils.hasText(username)) {
            return R.errMsg("username is required");
        }
        if (!StringUtils.hasText(password)) {
            return R.errMsg("password is required");
        }
        String[] res = hashService.hash(u.getPassword());
        u.setPassword(res[0]);
        u.setSalt(res[1]);
        R r = homeClient.create(u);
        if (r.getCode().equals(R.SUCCESS)) {
            return R.ok(u.getUsername());
        } else {
            return R.errMsg(r.getErrMsg());
        }

    }

    @GetMapping("/captcha/{uuid}")
    public void getCaptchaCode(HttpServletResponse response, @PathVariable String uuid) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        try {
            Assert.hasText(uuid, "验证码生成uuid为空");
            //生成验证码文本
            String capText = captchaProducer.createText();
            //利用生成的字符串构建图片
            BufferedImage bi = captchaProducer.createImage(capText);

            try (ServletOutputStream out = response.getOutputStream()) {
                ImageIO.write(bi, "jpg", out);
                out.flush();
                redisTemplateStringString.opsForValue().set(uuid, capText, 300, TimeUnit.SECONDS);
            }
        } catch (IllegalArgumentException e) {
            response.getWriter().write("{\"code\":1,\"errMsg\":\"" + e.getMessage() + "\"}");
        }
    }

}

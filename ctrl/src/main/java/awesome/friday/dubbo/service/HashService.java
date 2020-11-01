package awesome.friday.dubbo.service;

import awesome.friday.ctrl.entity.User;

/**
 * @author yiran
 * @date: 20.10.31 15:46
 */
public interface HashService {
    String sayHello(String name);

    String[] hash(String str);

    Boolean isRight(String pwd, String comparPwd, String salt);
}

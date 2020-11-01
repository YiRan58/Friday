package awesome.friday.dubbo.service;

/**
 * @author yiran
 * @date: 20.10.31 13:54
 */
public interface HashService {
    String sayHello(String name);

    String[] hash(String str);

    Boolean isRight(String pwd, String comparPwd, String salt);
}

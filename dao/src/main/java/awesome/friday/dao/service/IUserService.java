package awesome.friday.dao.service;

import awesome.friday.dao.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yiran
 * @since 2020-11-01
 */
public interface IUserService extends IService<User> {

    User findByUsername(String username);

}

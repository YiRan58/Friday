package awesome.friday.dao.service.impl;

import awesome.friday.dao.entity.User;
import awesome.friday.dao.mapper.UserMapper;
import awesome.friday.dao.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yiran
 * @since 2020-11-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User findByUsername(String username) {
        return this.baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
    }
}

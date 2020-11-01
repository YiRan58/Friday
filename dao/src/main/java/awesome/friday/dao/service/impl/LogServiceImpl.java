package awesome.friday.dao.service.impl;

import awesome.friday.dao.entity.Log;
import awesome.friday.dao.mapper.LogMapper;
import awesome.friday.dao.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yiran
 * @date: 20.11.1 20:04
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {
}

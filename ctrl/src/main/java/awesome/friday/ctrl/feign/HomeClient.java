package awesome.friday.ctrl.feign;

import awesome.friday.ctrl.entity.User;
import awesome.friday.dto.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yiran
 * @date: 20.11.1 15:48
 */
@FeignClient("dao")
public interface HomeClient {

    @GetMapping("/username")
    User user(@RequestParam  String username);

    @PostMapping("/create")
    R create(@RequestBody User u);
}

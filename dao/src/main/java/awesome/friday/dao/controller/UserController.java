package awesome.friday.dao.controller;


import awesome.friday.dao.dto.R;
import awesome.friday.dao.entity.User;
import awesome.friday.dao.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yiran
 * @since 2020-11-01
 */
@RestController
public class UserController {

    @Autowired
    IUserService iUserService;

    @GetMapping("/username")
    public User user(@RequestParam String username) {
        return iUserService.findByUsername(username);
    }

    @PostMapping("/create")
    public R create(@RequestBody User user){
        if(iUserService.findByUsername(user.getUsername())!=null){
            return R.errMsg("username has been exist");
        }
        return R.ok(iUserService.save(user));
    }
}


package awesome.friday.ctrl.controller;

import awesome.friday.dto.R;
import awesome.friday.log.LogWriter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yiran
 * @date: 20.11.1 19:35
 */
@RestController
public class TController {

    @LogWriter
    @PostMapping("/xxx")
    public R go() {
        System.out.println("this is a test");
        return R.ok("this is a test");
    }
}

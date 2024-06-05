package packdev937.elasticstack.controller;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ElkController {

    @GetMapping("/")
    public String home(){
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("Welcome to Home Page" + localDateTime);
        return "Welcome to Home Page";
    }

    @GetMapping("/logs")
    public String logs(){
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("This is Logs Page" + localDateTime);
        return "Welcome to Logs Page";
    }

}

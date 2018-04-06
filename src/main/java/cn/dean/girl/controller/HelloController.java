package cn.dean.girl.controller;

import cn.dean.girl.properties.GirlProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @Autowired
    private GirlProperty girlProperty;

    @GetMapping(value = {"/hello", "/hi"})
    public String say(@RequestParam(value = "id", defaultValue = "0", required = false) int myId) {
        return "id:" + myId;
    }
}

package cn.dean.girl.controller;

import cn.dean.girl.service.GirlService;
import cn.dean.girl.domain.Girl;
import cn.dean.girl.domain.Result;
import cn.dean.girl.repository.GirlRepository;
import cn.dean.girl.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女生
     *
     * @return
     */
    @GetMapping("/girls")
    public List<Girl> getList() {
        logger.info("girlList");
        return girlRepository.findAll();
    }

    /**
     * 添加一个女生
     *
     * @return
     */
    @PostMapping("/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //打印错误信息
            //System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return ResultUtils.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtils.success(girlRepository.save(girl));
    }

    //根据id查询女生
    @GetMapping("/girls/{id}")
    public Girl girlFindById(@PathVariable("id") Integer id) {
        //SpringBoot 1.4与2.0版本有差异,此处是个小坑
        //1.4版本为girlRepository.findOne(id);
        return girlRepository.findById(id).get();

    }

    //根据id删除女生
    @DeleteMapping("/girls/{id}")
    public void girlDeleteById(@PathVariable("id") Integer id) {
        girlRepository.deleteById(id);
    }

    //更新女生
    //使用POSTMAN调试时，PUT参数使用x-www-form-urlencoded

    //而不是form-data
    @PutMapping("/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("age") Integer age,
                           @RequestParam("cupSize") String cupSize) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlRepository.save(girl);
    }

    /**
     * 通过年龄来查找女生列表
     *
     * @param age
     * @return
     */
    @GetMapping("/girls/age/{age}")
    public List<Girl> girlFindByAge(@PathVariable("age") Integer age) {
        return girlRepository.findByAge(age);
    }

    /**
     * 插入两个女生
     */
    @PostMapping("/girls/two")
    public void insertTwo() {
        girlService.insertTwo();
    }

    @GetMapping("/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        girlService.getAge(id);
    }
}

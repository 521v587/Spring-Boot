package cn.dean.girl.Service;

import cn.dean.girl.domain.Girl;
import cn.dean.girl.enums.ResultEnum;
import cn.dean.girl.exception.GirlException;
import cn.dean.girl.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class GirlService {
    @Autowired
    private GirlRepository girlRepository;

    /**
     * 同时添加两个女生，用来测试事务特性
     */
    @Transactional
    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setAge(18);
        girlA.setCupSize("B");
        girlRepository.save(girlA);

        //int i = 1 / 0;
        Girl girlB = new Girl();
        girlB.setAge(16);
        girlB.setCupSize("DD");
        girlRepository.save(girlB);

    }

    public void getAge(Integer id) throws Exception {
        Girl girl = girlRepository.getOne(id);
        Integer age = girl.getAge();
        if (age < 10) {
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age >= 10 && age < 16) {
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }

        //...

    }

    /**
     * 根据id查询一个女生
     *
     * @param id
     * @return
     */
    public Girl findGirlById(Integer id) {
        Girl girl = girlRepository.findById(id).get();
        return girl;
    }
}

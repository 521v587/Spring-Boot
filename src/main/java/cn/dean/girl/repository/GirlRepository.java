package cn.dean.girl.repository;

import cn.dean.girl.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * jpa模板接口
 */
public interface GirlRepository extends JpaRepository<Girl, Integer> {
    //根据age查询女生列表
    public List<Girl> findByAge(Integer age);
}

package orz.joey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orz.joey.repository.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 根据方法名自动构建sql语句
    User findByCellphone(String cellphone);

}

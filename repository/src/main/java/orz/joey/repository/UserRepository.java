package orz.joey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orz.joey.repository.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 将自动根据方法名构建sql语句
    User findByCellphone(String cellphone);

}

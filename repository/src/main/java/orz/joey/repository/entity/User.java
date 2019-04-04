package orz.joey.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 32)
    private String username;
    @Column(nullable = false, length = 64)
    private String password;
    @Column(length = 16, unique = true)
    private String cellphone;
    @Column(length = 64 , unique = true)
    private String email;
    @Column(nullable = false, columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间'", insertable = false, updatable = false)
    private Timestamp createTime;
    @Column(nullable = false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'", insertable = false, updatable = false)
    private Timestamp updateTime;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Timestamp getCreateTime() { return createTime; }

    public void setCreateTime(Timestamp createTime) { this.createTime = createTime; }

    public Timestamp getUpdateTime() { return updateTime; }

    public void setUpdateTime(Timestamp updateTime) { this.updateTime = updateTime; }
}

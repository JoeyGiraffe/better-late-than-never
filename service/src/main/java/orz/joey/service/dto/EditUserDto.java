package orz.joey.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import orz.joey.service.dto.constraints.Cellphone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@ApiModel(description = "修改用户信息")
public class EditUserDto implements Serializable {
    private static final long serialVersionUID = -2284153512623305443L;

    @ApiModelProperty(value = "用户ID", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "登录密码")
    @Length(min = 8, max = 16)
    @Pattern(regexp = "^[a-zA-Z]\\w{7,15}$")
    private String password;

    @ApiModelProperty(value = "手机号")
    @Cellphone
    private String cellphone;

    @ApiModelProperty(value = "邮箱")
    @Email
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EditUserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

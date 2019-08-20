package orz.joey.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import orz.joey.service.dto.constraints.Cellphone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@ApiModel(description = "用户信息")
public class UserDto implements Serializable {
    private static final long serialVersionUID = -9129518669746552036L;

    @ApiModelProperty(value = "用户ID", example = "1")
    @NotNull
    private Long id;

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank
    private String username;

    @ApiModelProperty(value = "登录密码", required = true)
    @NotBlank
    @Length(min = 8, max = 16)
    @Pattern(regexp = "^[a-zA-Z]\\w{7,15}$")
    private String password;

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank
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
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

package orz.joey.service.dto;

import org.hibernate.validator.constraints.Length;
import orz.joey.service.dto.constraints.Cellphone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class UserDto implements Serializable {
    private static final long serialVersionUID = -9129518669746552036L;
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    @Length(min = 8, max = 16)
    @Pattern(regexp = "^[a-zA-Z]\\w{7,15}$")
    private String password;
    @NotBlank
    @Cellphone
    private String cellphone;
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

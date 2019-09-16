package orz.joey.service.dto.validation;

import org.springframework.util.StringUtils;
import orz.joey.service.dto.validation.constraints.Cellphone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CellphoneValidator implements ConstraintValidator<Cellphone, String> {
    @Override
    public void initialize(Cellphone constraintAnnotation) {

    }

    /** {@code null} or empty string just returns {@code true}*/
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isEmpty(value) || Pattern.matches("^1[34578]\\d{9}$", value);
    }
}

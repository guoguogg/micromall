package run.micromall.micromall.db.validation;

import cn.hutool.core.collection.CollUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class SortValidator implements ConstraintValidator<Sort, String> {
    private List<String> valueList;

    @Override
    public void initialize(Sort sort) {
        valueList = CollUtil.newArrayList();
        for (String val : sort.accepts()) {
            valueList.add(val.toUpperCase());
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!valueList.contains(s.toUpperCase())) {
            return false;
        }
        return true;
    }
}

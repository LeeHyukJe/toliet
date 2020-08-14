package com.wisenut.toliet.web.payload;

import com.wisenut.web.payload.SearchPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class searchPayloadTests {

    private Validator validator;

    @Before
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validate_blankPayload_should_fail(){
        SearchPayload searchPayload = new SearchPayload();
        Set<ConstraintViolation<SearchPayload>> violations = validator.validate(searchPayload);
        Assert.assertEquals(3, violations.size());
    }
}

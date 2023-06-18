package com.example.servercode.utiles;

import com.example.servercode.utils.ValidateCodeUtils;
import org.junit.jupiter.api.Test;

public class ValidateCodeUtilsTest {
    @Test
    public void generateValidateCodeStringTest(){
        System.out.println(ValidateCodeUtils.generateValidateCodeString(4));
    }
}

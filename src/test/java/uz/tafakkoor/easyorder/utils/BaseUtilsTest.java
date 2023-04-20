package uz.tafakkoor.easyorder.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseUtilsTest {

    @Test
    void generateOTP() {
        String otp = null; //BaseUtils.generateOTP();
        System.out.println(otp);
        assertTrue(otp.length() != 0);
    }
}
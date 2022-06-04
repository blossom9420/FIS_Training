package vn.fis.training.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void checkName() {
        Customer cus = new Customer();
        String s = "nGuyen van a";
        int kq = cus.checkName(s);
        System.out.println(s);
        assertEquals(1,kq);
    }

    @Test
    void standardizeName() {
        Customer cus = new Customer();
        String s = "  NgUyen van a";
        String rs = cus.standardizeName(s);
        System.out.println(rs);
        assertEquals("Nguyen Van A",rs);
    }

    @Test
    void checkBirthDay() {
        Customer cus = new Customer();
        LocalDate birthdate = LocalDate.of(2012, 6, 5);
        int kq = cus.checkBirthDay(birthdate); // vì chưa đủ 10 tuổi nên return 0 - không đủ tuổi
        System.out.println("Có đủ 10 tuổi chưa: "+kq);

        LocalDate now = LocalDate.now(); // today is: 2022 - 6 - 4 ( thiếu 1 ngày nên chưa tròn 1 năm )
        long years = ChronoUnit.YEARS.between(birthdate, now);
        System.out.println("Age: "+years);
        assertEquals(-0,0);
    }

    @Test
    void checkMobile(){
        Customer cus = new Customer();
        String mobile = "  0128 1228 2 1 ";
        int kq = cus.checkMobile(mobile);
        System.out.println(kq);
        assertEquals(1,kq);
    }

    @Test
    void CheckStatus(){
        Customer cus = new Customer();
        CustomerStatus c =  CustomerStatus.ACTIVE;
        int kq = cus.CheckStatus(c);
        System.out.println(c);
    }

    @Test
    void standardizeMobile() {
        Customer cus = new Customer();
        String mobile = " 1515 251";
        String standardize = cus.standardizeMobile(mobile);
        System.out.println(standardize);
        assertEquals("1515251", standardize);
    }
}
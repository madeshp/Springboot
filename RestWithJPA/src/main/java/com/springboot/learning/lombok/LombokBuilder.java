package com.springboot.learning.lombok;


public class LombokBuilder {



    public static void main(String[] str)
    {

        LombokData lombokData = LombokData.builder().age(12).firstName("Test").lastName("Test").build();
        System.out.println(lombokData.toString());
    }
}

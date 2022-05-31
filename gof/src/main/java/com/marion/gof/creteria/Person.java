package com.marion.gof.creteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Marion
 * @date 2022/5/31 10:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private int id;
    private int age;
    private String gender;

}

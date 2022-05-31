package com.marion.gof.creteria;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marion
 * @date 2022/5/31 13:52
 */
public class MaleCriteria implements CriteriaFactory {
    @Override
    public List<Person> filter(List<Person> list) {
        return list.stream().filter(v -> v.getGender().equals("male")).collect(Collectors.toList());
    }
}

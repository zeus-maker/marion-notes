package com.marion.gof.creteria;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 和条件过滤
 * @author Marion
 * @date 2022/5/31 10:45
 */
public class OrCriteria implements CriteriaFactory {

    private CriteriaFactory criteria1;
    private CriteriaFactory criteria2;

    public OrCriteria(CriteriaFactory criteria1, CriteriaFactory criteria2) {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }

    @Override
    public List<Person> filter(List<Person> list) {
        List<Person> filter1 = criteria1.filter(list);
        List<Person> filter2 = criteria2.filter(list);
        return Stream.of(filter1, filter2)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}

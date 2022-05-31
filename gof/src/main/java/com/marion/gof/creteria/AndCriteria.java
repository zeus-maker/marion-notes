package com.marion.gof.creteria;

import java.util.List;

/**
 * 和条件过滤
 * @author Marion
 * @date 2022/5/31 10:45
 */
public class AndCriteria implements CriteriaFactory {

    private CriteriaFactory criteria1;
    private CriteriaFactory criteria2;

    public AndCriteria(CriteriaFactory criteria1, CriteriaFactory criteria2) {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }

    @Override
    public List<Person> filter(List<Person> list) {
        return criteria2.filter(criteria1.filter(list));
    }
}

package com.marion.gof.creteria;

import java.util.List;

/**
 * @author Marion
 * @date 2022/5/31 10:44
 */
public interface CriteriaFactory {

    List<Person> filter(List<Person> list);

}

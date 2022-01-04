package com.marion.mybatis3.common.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
@Slf4j
public class PageFormatter implements Formatter<PageRequest> {

    @Override
    public PageRequest parse(String text, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(PageRequest object, Locale locale) {
        return null;
    }
}

package com.zero.scvzerng.logger.filter;

import com.alibaba.fastjson.serializer.PropertyFilter;

import java.util.List;

public class ValueFilter implements PropertyFilter {
    List<Class> valueClasses ;

    public ValueFilter(List<Class> valueClasses) {
        this.valueClasses = valueClasses;
    }

    @Override
    public boolean apply(Object object, String name, Object value) {
        if(value==null) return true;
        return !valueClasses.contains(value.getClass());
    }
}

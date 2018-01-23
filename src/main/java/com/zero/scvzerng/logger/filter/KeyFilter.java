package com.zero.scvzerng.logger.filter;

import com.alibaba.fastjson.serializer.PropertyFilter;

import java.util.List;

public class KeyFilter implements PropertyFilter {
    private List<String> keys;

    public KeyFilter(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public boolean apply(Object object, String name, Object value) {
        if(value==null) return true;
        return !keys.contains(name);
    }
}

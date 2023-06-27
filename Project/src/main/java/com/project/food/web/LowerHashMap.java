package com.project.food.web;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

public class LowerHashMap extends HashMap {
	private static final long serialVersionUID = -7700790403928325865L;

    @SuppressWarnings("unchecked")
    @Override
    public Object put(Object key, Object value) {
        return super.put(StringUtils.lowerCase((String) key), value);
    }
}

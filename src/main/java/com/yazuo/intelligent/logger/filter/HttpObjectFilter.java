package com.yazuo.intelligent.logger.filter;

import com.yazuo.intelligent.logger.filter.LoggerParamsFilter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * 过滤servlet相关对象和文件对象
 */
public class HttpObjectFilter implements LoggerParamsFilter {

    @Override
    public boolean apply(Object object, String name, Object value) {
        return  !(value instanceof HttpServletRequest)||
                !(value instanceof HttpServletResponse)||
                !(value instanceof File) ||
                !(value instanceof HttpSession) ||
                !(value instanceof MultipartFile);
    }


}

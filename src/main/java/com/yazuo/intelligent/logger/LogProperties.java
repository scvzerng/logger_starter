package com.yazuo.intelligent.logger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cache.log")
public class LogProperties {
    /**
     * 缓存日志启用
     */
    private Enable enable ;

    public static class Enable{
        private boolean key = true;
        private boolean value = true;
        private boolean name = true;

        public boolean isKey() {
            return key;
        }

        public void setKey(boolean key) {
            this.key = key;
        }

        public boolean isValue() {
            return value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }

        public boolean isName() {
            return name;
        }

        public void setName(boolean name) {
            this.name = name;
        }
    }

    public Enable getEnable() {
        return enable;
    }

    public void setEnable(Enable enable) {
        this.enable = enable;
    }
}

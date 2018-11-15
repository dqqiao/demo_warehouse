package com.nov.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 需要三个pom依赖：slf4j-api , log4j , slf4j-log4j12(这个可以替换,多了少了都会报错)
 * classpath下需要一个配置文件：log4j.properties.缺少会报错:"No appenders could be found for logger"
 * 详见：https://www.slf4j.org/codes.html#StaticLoggerBinder
 * @author HONY
 * @date 2018/11/15
 */
public class Slf4jWithLog4j {

    private static final Logger logger = LoggerFactory.getLogger(Slf4jWithLog4j.class);

    public static void main(String[] args) {
        logger.debug("I'm Slf4j With log4j");
    }
}

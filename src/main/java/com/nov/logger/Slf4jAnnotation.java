package com.nov.logger;

import lombok.extern.slf4j.Slf4j;

/**
 * 只需要引入lombok的依赖即可，直接使用@Slf4j注解。(需要安装lombok插件)
 * Idea ==> settings - Plugins - Browse repositories - 搜索lombok - install
 * eclipse ==> 双击lombok.jar安装一下
 * @author HONY
 * @date 2018/11/15
 */
@Slf4j
public class Slf4jAnnotation {

    public static void main(String[] args) {
        log.debug("I'm @Slf4j.");
    }
}

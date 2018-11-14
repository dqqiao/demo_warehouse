package com.nov.util;

/**
 * @author HONY
 * @date 2018/11/10
 */
public class MailConstants {

    /**
     * PropertyUtil读取配置
     * 直接使用MailConstants.EMAIL_PROTOCOL...替换硬编码
     */
    public static final String EMAIL_PROTOCOL = PropertyUtil.getProperties("mail.transport.protocol");
    public static final String EMAIL_QQ_FROM = PropertyUtil.getProperties("qq.from");
    public static final String EMAIL_QQ_AUTH = PropertyUtil.getProperties("qq.auth");
    public static final String EMAIL_QQ_RECIPIENT_ADDR = PropertyUtil.getProperties("qq.recipient_address");
    public static final String EMAIL_QQ_SMTP_HOST = PropertyUtil.getProperties("qq.mail.smtp.host");
    public static final String EMAIL_QQ_SMTP_PORT = PropertyUtil.getProperties("qq.mail.smtp.port");
    public static final String EMAIL_QQ_SMTP_AUTH = PropertyUtil.getProperties("qq.mail.smtp.auth");
    public static final String EMAIL_QQ_TIMEOUT = PropertyUtil.getProperties("qq.mail.smtp.timeout");
    public static final String EMAIL_QQ_SSL_ENABLE = PropertyUtil.getProperties("qq.mail.smtp.ssl.enable");

}

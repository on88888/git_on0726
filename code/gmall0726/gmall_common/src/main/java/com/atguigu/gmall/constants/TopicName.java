package com.atguigu.gmall.constants;

/**
 * Created by Smexy on 2021/11/27
 *
 *      使用接口而不是类！
 *
 *          想存储常量！类有实例化的风险！
 */
public interface TopicName {

    String STARTUP_LOG = "STARTUP_LOG";
    String ERROR_LOG = "ERROR_LOG";
    String DISPLAY_LOG = "DISPLAY_LOG";
    String PAGE_LOG = "PAGE_LOG";
    String ACTIONS_LOG = "ACTIONS_LOG";

    String GMALL_ORDER_INFO = "GMALL_ORDER_INFO";
    String GMALL_ORDER_DETAIL = "GMALL_ORDER_DETAIL";
    String GMALL_USER_INFO = "GMALL_USER_INFO";

}

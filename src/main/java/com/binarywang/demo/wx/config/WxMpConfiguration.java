package com.binarywang.demo.wx.config;

import com.binarywang.demo.wx.handler.*;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts.EventType;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static me.chanjar.weixin.common.api.WxConsts.EventType.SUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.EventType.UNSUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType.EVENT;
import static me.chanjar.weixin.mp.constant.WxMpEventConstants.CustomerService.*;
import static me.chanjar.weixin.mp.constant.WxMpEventConstants.POI_CHECK_NOTIFY;

/**
 * wechat mp configuration
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
@Configuration
public class WxMpConfiguration {
    private final WxMpLogHandler logHandler;
    private final WxMpNullHandler nullHandler;
    private final WxMpKfSessionHandler kfSessionHandler;
    private final WxMpStoreCheckNotifyHandler storeCheckNotifyHandler;
    private final WxMpLocationHandler locationHandler;
    private final WxMpMenuHandler menuHandler;
    private final WxMpMsgHandler msgHandler;
    private final WxMpUnsubscribeHandler unsubscribeHandler;
    private final WxMpSubscribeHandler subscribeHandler;
    private final WxMpScanHandler scanHandler;

    @Bean
    public WxMpMessageRouter wxMpMessageRouter(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(this.logHandler).next();

        // 接收客服会话管理事件
        newRouter.rule().async(false).msgType(EVENT).event(KF_CREATE_SESSION)
                .handler(this.kfSessionHandler).end();
        newRouter.rule().async(false).msgType(EVENT).event(KF_CLOSE_SESSION)
                .handler(this.kfSessionHandler).end();
        newRouter.rule().async(false).msgType(EVENT).event(KF_SWITCH_SESSION)
                .handler(this.kfSessionHandler).end();

        // 门店审核事件
        newRouter.rule().async(false).msgType(EVENT).event(POI_CHECK_NOTIFY)
                .handler(this.storeCheckNotifyHandler).end();

        // 自定义菜单事件
        newRouter.rule().async(false).msgType(EVENT).event(EventType.CLICK).handler(this.menuHandler).end();

        // 点击菜单连接事件
        newRouter.rule().async(false).msgType(EVENT).event(EventType.VIEW).handler(this.nullHandler).end();

        // 关注事件
        newRouter.rule().async(false).msgType(EVENT).event(SUBSCRIBE).handler(this.subscribeHandler).end();

        // 取消关注事件
        newRouter.rule().async(false).msgType(EVENT).event(UNSUBSCRIBE).handler(this.unsubscribeHandler).end();

        // 上报地理位置事件
        newRouter.rule().async(false).msgType(EVENT).event(EventType.LOCATION).handler(this.locationHandler)
                .end();

        // 接收地理位置消息
        newRouter.rule().async(false).msgType(XmlMsgType.LOCATION).handler(this.locationHandler).end();

        // 扫码事件
        newRouter.rule().async(false).msgType(EVENT).event(EventType.SCAN).handler(this.scanHandler).end();

        // 默认
        newRouter.rule().async(false).handler(this.msgHandler).end();

        return newRouter;
    }

}

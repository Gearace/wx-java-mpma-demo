package com.binarywang.demo.wx.mp.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.binarywang.demo.wx.mp.handler.WxMaLogHandler;
import com.binarywang.demo.wx.mp.handler.WxMaPicHandler;
import com.binarywang.demo.wx.mp.handler.WxMaQrcodeHandler;
import com.binarywang.demo.wx.mp.handler.WxMaSubscribeHandler;
import com.binarywang.demo.wx.mp.handler.WxMaTextHandler;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.message.WxMaMessageRouter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(WxMaService.class)
public class WxMaConfiguration {

  private final WxMaSubscribeHandler subscribeMsgHandler;
  private final WxMaLogHandler logHandler;
  private final WxMaTextHandler textHandler;
  private final WxMaPicHandler picHandler;
  private final WxMaQrcodeHandler qrcodeHandler;

  @Bean
  public WxMaMessageRouter wxMaMessageRouter(WxMaService wxMaService) {
    final WxMaMessageRouter router = new WxMaMessageRouter(wxMaService);
    router
        .rule().handler(this.logHandler).next()
        .rule().async(false).content("订阅消息").handler(this.subscribeMsgHandler).end()
        .rule().async(false).content("文本").handler(this.textHandler).end()
        .rule().async(false).content("图片").handler(this.picHandler).end()
        .rule().async(false).content("二维码").handler(this.qrcodeHandler).end();
    return router;
  }

}

package com.binarywang.demo.wx.mp.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.message.WxMaMessageHandler;
import cn.binarywang.wx.miniapp.message.WxMaXmlOutMessage;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;

@Slf4j
@Component
public class WxMaLogHandler implements WxMaMessageHandler {

  @Override
  public WxMaXmlOutMessage handle(WxMaMessage message, Map<String, Object> context, WxMaService service,
      WxSessionManager sessionManager) throws WxErrorException {
    log.info("收到消息：" + message.toString());
    service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("收到信息为：" + message.toJson())
        .toUser(message.getFromUser()).build());
    return null;
  }

}

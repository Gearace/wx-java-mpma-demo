package com.binarywang.demo.wx.mp.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.binarywang.wx.miniapp.message.WxMaMessageHandler;
import cn.binarywang.wx.miniapp.message.WxMaXmlOutMessage;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;

@Component
public class WxMaSubscribeHandler implements WxMaMessageHandler {

  @Override
  public WxMaXmlOutMessage handle(WxMaMessage message, Map<String, Object> context, WxMaService service,
      WxSessionManager sessionManager) throws WxErrorException {
    service.getMsgService().sendSubscribeMsg(WxMaSubscribeMessage.builder()
        .templateId("此处更换为自己的模板id")
        .data(Lists.newArrayList(
            new WxMaSubscribeMessage.MsgData("keyword1", "339208499")))
        .toUser(message.getFromUser())
        .build());
    return null;
  }

}

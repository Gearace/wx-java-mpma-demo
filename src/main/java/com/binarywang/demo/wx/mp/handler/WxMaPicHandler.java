package com.binarywang.demo.wx.mp.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.message.WxMaMessageHandler;
import cn.binarywang.wx.miniapp.message.WxMaXmlOutMessage;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;

@Component
public class WxMaPicHandler implements WxMaMessageHandler {

  @Override
  public WxMaXmlOutMessage handle(WxMaMessage message, Map<String, Object> context, WxMaService service,
      WxSessionManager sessionManager) throws WxErrorException {
    try {
      WxMediaUploadResult uploadResult = service.getMediaService()
          .uploadMedia("image", "png",
              ClassLoader.getSystemResourceAsStream("tmp.png"));
      service.getMsgService().sendKefuMsg(
          WxMaKefuMessage
              .newImageBuilder()
              .mediaId(uploadResult.getMediaId())
              .toUser(message.getFromUser())
              .build());
    } catch (WxErrorException e) {
      e.printStackTrace();
    }

    return null;
  }

}

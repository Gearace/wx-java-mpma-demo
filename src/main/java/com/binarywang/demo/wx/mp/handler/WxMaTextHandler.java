package com.binarywang.demo.wx.mp.handler;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.message.WxMaMessageHandler;
import cn.binarywang.wx.miniapp.message.WxMaXmlOutMessage;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WxMaTextHandler implements WxMaMessageHandler {

    @Override
    public WxMaXmlOutMessage handle(WxMaMessage message, Map<String, Object> context, WxMaService service,
                                    WxSessionManager sessionManager) throws WxErrorException {
        service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("回复文本消息")
                .toUser(message.getFromUser()).build());
        return null;
    }

}

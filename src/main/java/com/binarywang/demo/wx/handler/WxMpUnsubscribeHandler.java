package com.binarywang.demo.wx.handler;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.binarywang.demo.wx.model.WxMpSubscribeLog;
import com.binarywang.demo.wx.model.WxUser;
import com.binarywang.demo.wx.service.WxMpSubscribeLogService;
import com.binarywang.demo.wx.service.WxUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WxMpUnsubscribeHandler implements WxMpMessageHandler {

    private final WxUserService wxUserService;
    private final WxMpSubscribeLogService wxMpSubscribeLogService;
    
    /**
     * 处理取消关注用户事件
     *
     * @param wxMessage      the message received from the user
     * @param context        the context of the message
     * @param wxMpService    the WeChat service used to handle the unSubscribe
     *                       message
     * @param sessionManager the session manager for the message
     * @return the response message to be sent back to the user
     */
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        String openId = wxMessage.getFromUser();
        log.info("取消关注用户 OPENID: " + openId);
        WxMpSubscribeLog wxMpSubscribeLog = new WxMpSubscribeLog();
        WxUser wxUser = wxUserService
                .getOne(Wrappers.lambdaQuery(WxUser.class).eq(WxUser::getMpOpenid, openId));
        if (ObjectUtils.isNotEmpty(wxUser)) {
            wxUser.setMpSubscribe("0");
            wxUser.setMpCancelSubscribeTime(new Date());
            wxUserService.updateById(wxUser);
            wxMpSubscribeLog.setUserId(wxUser.getId());
            wxMpSubscribeLog.setUnionid(wxUser.getUnionid());
        }
        wxMpSubscribeLog.setOpenid(openId);
        wxMpSubscribeLog.setSubscribe("0");
        wxMpSubscribeLog.setOperTime(new Date());
        wxMpSubscribeLogService.save(wxMpSubscribeLog);
        return null;
    }

}

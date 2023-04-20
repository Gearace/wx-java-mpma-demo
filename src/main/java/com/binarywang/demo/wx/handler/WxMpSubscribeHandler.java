package com.binarywang.demo.wx.handler;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.binarywang.demo.wx.builder.TextBuilder;
import com.binarywang.demo.wx.model.WxMpSubscribeLog;
import com.binarywang.demo.wx.model.WxUser;
import com.binarywang.demo.wx.service.WxMpSubscribeLogService;
import com.binarywang.demo.wx.service.WxUserService;
import com.binarywang.demo.wx.utils.JsonUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WxMpSubscribeHandler implements WxMpMessageHandler {

    private final WxUserService wxUserService;
    private final WxMpSubscribeLogService wxMpSubscribeLogService;

    /**
     * 处理新关注用户事件
     *
     * @param wxMessage      the message received from the user
     * @param context        the context of the message
     * @param weixinService  the WeChat service used to handle the subscribe message
     * @param sessionManager the session manager for the message
     * @return the response message to be sent back to the user
     * @throws WxErrorException if there is an error with the WeChat API
     */
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
            Map<String, Object> context, WxMpService weixinService,
            WxSessionManager sessionManager) throws WxErrorException {

        log.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        try {
            WxMpUser userWxInfo = weixinService.getUserService()
                    .userInfo(wxMessage.getFromUser(), null);
            if (userWxInfo != null) {
                WxUser wxUser = wxUserService
                        .getOne(Wrappers.lambdaQuery(WxUser.class).eq(WxUser::getMpOpenid, userWxInfo.getOpenId()));
                if (ObjectUtils.isEmpty(wxUser)) {
                    wxUser = new WxUser();
                    setWxUser(wxUser, userWxInfo);
                    wxUser.setMpSubscribeNum(1);
                    wxUserService.save(wxUser);
                } else {
                    setWxUser(wxUser, userWxInfo);
                    wxUser.setMpSubscribeNum(wxUser.getMpSubscribeNum() + 1);
                    wxUserService.updateById(wxUser);
                }
                WxMpSubscribeLog wxMpSubscribeLog = new WxMpSubscribeLog();
                wxMpSubscribeLog.setUserId(wxUser.getId());
                wxMpSubscribeLog.setUnionid(userWxInfo.getUnionId());
                wxMpSubscribeLog.setOpenid(userWxInfo.getOpenId());
                wxMpSubscribeLog.setSubscribe("1");
                wxMpSubscribeLog.setSubscribeScene(userWxInfo.getSubscribeScene());
                wxMpSubscribeLog.setOperTime(new Date(userWxInfo.getSubscribeTime() * 1000));
                wxMpSubscribeLogService.save(wxMpSubscribeLog);
            }
        } catch (WxErrorException e) {
            if (e.getError().getErrorCode() == 48001) {
                log.info("该公众号没有获取用户信息权限！");
            }
        }

        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(wxMessage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build("感谢关注", wxMessage, weixinService);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
            throws Exception {
        // TODO
        return null;
    }

    private void setWxUser(WxUser wxUser, WxMpUser userWxInfo) {
        wxUser.setUnionid(userWxInfo.getUnionId());
        wxUser.setMpOpenid(userWxInfo.getOpenId());
        wxUser.setMpSubscribe("1");
        wxUser.setMpSubscribeScene(userWxInfo.getSubscribeScene());
        wxUser.setMpSubscribeTime(new Date(userWxInfo.getSubscribeTime() * 1000));
        wxUser.setLanguage(userWxInfo.getLanguage());
        wxUser.setMpRemark(userWxInfo.getRemark());
        wxUser.setMpTagidList(JsonUtils.toJson(userWxInfo.getTagIds()));
    }

}

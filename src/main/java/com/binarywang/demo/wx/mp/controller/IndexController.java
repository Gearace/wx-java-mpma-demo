package com.binarywang.demo.wx.mp.controller;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class IndexController {

    private final WxMpService mpService;


    @GetMapping("/")
    public String index() {
        String appId = this.mpService.getWxMpConfigStorage().getAppId();
        return appId;
    }

    @GetMapping("/test")
    public String test() throws WxErrorException {
        return this.mpService.getAccessToken();
    }

    @GetMapping("/sendTempalteMessage")
    public String sendTempalteMessage() throws WxErrorException {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder().toUser("openid")
                .templateId("templateId").url("mp.weixin.qq.com").build();
        templateMessage.addData(new WxMpTemplateData("openid", "openid", "#d9d9d9"));
        templateMessage.addData(new WxMpTemplateData("content", "content"));
        templateMessage.addData(new WxMpTemplateData("timestamp", "2023-04-01 10:12:22"));
        mpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        return "success";
    }
}

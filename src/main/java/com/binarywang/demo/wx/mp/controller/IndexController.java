package com.binarywang.demo.wx.mp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

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
}

package com.binarywang.demo.wx.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.binarywang.demo.wx.mapper.WxUserMapper;
import com.binarywang.demo.wx.model.WxUser;

@Service
public class WxUserService extends ServiceImpl<WxUserMapper, WxUser> {

}

package com.binarywang.demo.wx.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
    * 微信关注日志表
    */
@Data
@TableName(value = "wx_mp_subscribe_log")
public class WxMpSubscribeLog {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * sys_user表id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
     */
    @TableField(value = "unionid")
    private String unionid;

    /**
     * 微信公众号或小程序用户的标识
     */
    @TableField(value = "openid")
    private String openid;

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    @TableField(value = "subscribe")
    private String subscribe;

    /**
     * 返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENE_PROFILE_LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_WECHAT_ADVERTISEMENT 微信广告，ADD_SCENE_REPRINT 他人转载 ,ADD_SCENE_LIVESTREAM 视频号直播，ADD_SCENE_CHANNELS 视频号 , ADD_SCENE_OTHERS 其他
     */
    @TableField(value = "subscribe_scene")
    private String subscribeScene;

    /**
     * 用户关注时间或取消关注时间
     */
    @TableField(value = "oper_time")
    private Date operTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
}
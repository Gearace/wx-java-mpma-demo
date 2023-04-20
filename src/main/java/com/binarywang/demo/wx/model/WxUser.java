package com.binarywang.demo.wx.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 微信用户表
 */
@Data
@TableName(value = "wx_user")
public class WxUser {
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
     * 手机号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
     */
    @TableField(value = "unionid")
    private String unionid;

    /**
     * 微信小程序id
     */
    @TableField(value = "ma_openid")
    private String maOpenid;

    /**
     * 微信公众号用户的标识，对当前公众号唯一
     */
    @TableField(value = "mp_openid")
    private String mpOpenid;

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    @TableField(value = "mp_subscribe")
    private String mpSubscribe;

    /**
     * 返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENE_PROFILE_LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_WECHAT_ADVERTISEMENT 微信广告，ADD_SCENE_REPRINT 他人转载 ,ADD_SCENE_LIVESTREAM 视频号直播，ADD_SCENE_CHANNELS 视频号 , ADD_SCENE_OTHERS 其他
     */
    @TableField(value = "mp_subscribe_scene")
    private String mpSubscribeScene;

    /**
     * 用户关注时间，如果用户曾多次关注，则取最后关注时间
     */
    @TableField(value = "mp_subscribe_time")
    private Date mpSubscribeTime;

    /**
     * 取消关注时间
     */
    @TableField(value = "mp_cancel_subscribe_time")
    private Date mpCancelSubscribeTime;

    /**
     * 关注次数
     */
    @TableField(value = "mp_subscribe_num")
    private Integer mpSubscribeNum;

    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    @TableField(value = "mp_remark")
    private String mpRemark;

    /**
     * 用户被打上的标签ID列表
     */
    @TableField(value = "mp_tagid_list")
    private String mpTagidList;

    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 国家，如中国为CN
     */
    @TableField(value = "county")
    private String county;

    /**
     * 用户个人资料填写的省份
     */
    @TableField(value = "province")
    private String province;

    /**
     * 普通用户个人资料填写的城市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 用户的语言，简体中文为zh_CN
     */
    @TableField(value = "`language`")
    private String language;

    /**
     * 小程序会话密钥
     */
    @TableField(value = "session_key")
    private String sessionKey;

    /**
     * 网页授权接口调用凭证
     */
    @TableField(value = "access_token")
    private String accessToken;

    /**
     * 用户刷新access_token
     */
    @TableField(value = "refresh_token")
    private String refreshToken;

    /**
     * access_token接口调用凭证超时时间
     */
    @TableField(value = "expire_date")
    private Date expireDate;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
}

package com.gzs.learn.elasticsearch.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

/**
 * @author guanzhisong
 * @date 2017-06-25 16:16:23
 */
@Data
@Document(indexName = "jkx_order", type = "order", createIndex = true, shards = 6, replicas = 2)
public class JkxOrder implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单id，由系统按一定规则生成
     */
    private String id;

    /**
     * 合作商id
     */
    private Integer cpId;

    /**
     * 返修订单中保存的原始订单ID
     */
    private String fid;

    /**
     * 下单用户id
     */
    private Long userId;

    /**
     * 订单标题
     */
    private String title;

    /**
     * 订单类型： 0：邮寄维修 1：上门取货...
     */
    private Integer type;

    /**
     * 订单来源 详见t_setting配置
     */
    private Integer source;

    /**
     * 订单渠道
     */
    private Integer origin;

    /**
     * 订单分类：0普通订单，1返修订单，2后结算订单，3兑保订单
     */
    private Integer orderCategory;

    /**
     * 维修设备id
     */
    private Integer deviceId;

    /**
     * 设备序列号，若为手机，即为IMEI码
     */
    private String deviceSn;

    /**
     * 维修历史,0:无 1:有(非主板维修) 2：有(主板维修)
     */
    private Integer repairedHis;

    /**
     * 维修工程师id
     */
    private Integer engineerId;

    /**
     * 客户留言
     */
    private String message;

    /**
     * 订单总额 -1表示待检测
     */
    private BigDecimal priceTotal;

    /**
     *
     */
    private Date createTime;

    /**
     * 订单跟踪人id
     */
    private Integer followBy;

    /**
     * 订单状态 详见OrderStatusConstant类说明（0，2600无效订单）
     */
    private Integer status;

    /**
     * 订单回寄地址id
     */
    private Integer userAddrId;

    /**
     * 故障描述
     */
    private String malfunctionDescribe;

    /**
     * 0：邮寄，1：上门维修 2：到店维修
     */
    private Integer repairMethod;

    /**
     * 维修中心ID
     */
    private Integer serviceCenterId;

    /**
     * 价格修改次数
     */
    private Integer priceModifyNum;

    /**
     * 订单支付状态 0：未支付 1：已支付 2：已退款 3:待结算
     */
    private Integer payStatus;

    /**
     * 支付时间，未支付则留null
     */
    private Date paymentTime;

    /**
     * 退款时间，未退款则留null
     */
    private Date refundTime;

    /**
     *
     */
    private Integer oldSource;

    /**
     * 指派状态 0默认 未指派，1 已指派未响应 2，已接受
     */
    private Integer assignState;

    /**
     * 指派时间（用于定时器重置订单指派状态）
     */
    private Date assignTime;

    /**
     * 修改版本号
     */
    private Date version;

    /**
     *
     */
    private Integer u8Flag;

    /**
     *
     */
    private Integer costFinishState;

    /**
     * 第一指派客服
     */
    private Integer firstAssignOperator;

    /**
     * 第一指派时间
     */
    private Date firstAssignTime;

    /**
     * 员工下单时绑定的员工编号
     */
    private String employeeNo;

    /**
     * 员工下单时绑定的员工名称
     */
    private String employeeName;
}

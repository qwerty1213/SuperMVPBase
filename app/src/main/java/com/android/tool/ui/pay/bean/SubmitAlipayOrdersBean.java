package com.android.tool.ui.pay.bean;

/**
 * class ：提交支付寶订单
 * author：York(wuchunyuan)
 * time  : 2018/4/11 13:56
 */
public class SubmitAlipayOrdersBean {

    /**
     * payStr : _input_charset=utf-8&it_b_pay=30m&notify_url=http%3A%2F%2Fwanglei.sx1211.ea-crm.com%2FPayment%2FalipayNotify.html&out_trade_no=1953000&partner=2088121617445984&payment_type=1&seller_id=422538022%40qq.com&service=mobile.securitypay.pay&subject=%E8%B4%AD%E4%B9%B0%E8%B4%AD%E4%B9%B017%E5%AE%89%E5%BE%BD+%E4%B8%AD%E5%AD%A6%E6%95%99%E8%82%B2%E5%AD%A6%EF%BC%88%E9%A2%84%E5%94%AE%E4%B8%AD%EF%BC%89%E7%AD%89&total_fee=1380.00&sign=dbd218ca10d65c6921e0b3d8b02b0fca&sign_type=MD5
     * orderId : 1953000
     */

    private String payStr;
    private String orderId;

    public String getPayStr() {
        return payStr;
    }

    public void setPayStr(String payStr) {
        this.payStr = payStr;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

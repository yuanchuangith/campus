package com.example.server.service.impl;

//import com.alipay.easysdk.factory.Factory;
//import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;

import cn.hutool.json.JSONObject;
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayClient;
//import com.alipay.api.AlipayConfig;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
//import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;

import com.alipay.api.response.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.config.zfbPayConfig;
import com.example.server.mapper.aplyMapper;
import com.example.server.model.entity.Aply;
import com.example.server.model.entity.RechargeTrade;
import com.example.server.model.vo.rechage.reapply;
import com.example.server.service.aplySeve;
import com.example.server.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
//40070
@Service
public class aplyImpl extends ServiceImpl<aplyMapper, Aply> implements aplySeve {

    @Autowired
    private com.example.server.mapper.ReChargeTradeMapper reChargeTradeMapper;
    @Autowired
    private com.example.server.mapper.reChargeMoneyMapper reChargeMoneyMapper;
    @Override
    public ResultUtil chargeMoney(HttpServletResponse httpResponse , reapply reapply) throws IOException, AlipayApiException {
//        AlipayTradePagePayResponse response;
//        try {
//            //  发起API调用（以创建当面付收款二维码为例）
//            response = Factory.Payment.Page()
//                    .pay(URLEncoder.encode("test", "UTF-8"), UUID.randomUUID().toString().replace("-",""), "20", "");
//        } catch (Exception e) {
//            System.err.println("调用遭遇异常，原因：" + e.getMessage());
//            throw new RuntimeException(e.getMessage(), e);
//        }
        if( Objects.isNull(reapply) || reapply.getUserName()== null|| reapply.getUserID()==null||reapply.getTotalAmount()==null)
            return ResultUtil.fail(40071,"请登录");
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setAppId(zfbPayConfig.appID);
        alipayConfig.setAlipayPublicKey(zfbPayConfig.publicKey);
        alipayConfig.setPrivateKey(zfbPayConfig.privateKey);
        //设置网关地址
        alipayConfig.setServerUrl(zfbPayConfig.url);
        alipayConfig.setFormat(zfbPayConfig.format);
//设置字符集
        alipayConfig.setCharset(zfbPayConfig.charset);
//设置签名类型
        alipayConfig.setSignType(zfbPayConfig.signType);

        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(zfbPayConfig.notify_url);
        request.setReturnUrl(zfbPayConfig.return_url);

        JSONObject bizContent = new JSONObject();
        String out_trade_no =UUID.randomUUID().toString().replace("-","");
        bizContent.set("out_trade_no",out_trade_no);  // 我们自己生成的订单编号
        bizContent.set("total_amount", reapply.getTotalAmount()); // 订单的总金额
        bizContent.set("subject",URLEncoder.encode(reapply.getUserName()+"充值"+String.valueOf(reapply.getTotalAmount())+"元", "UTF-8"));   // 支付的名称
        bizContent.set("product_code", "FAST_INSTANT_TRADE_PAY");  // 固定配置

        request.setBizContent(bizContent.toString());

        AlipayTradePagePayResponse aliresponse = alipayClient.pageExecute(request);
//调用成功，则处理业务逻辑
        if(aliresponse.isSuccess()){
            RechargeTrade rechargeTrade = new RechargeTrade();
            rechargeTrade.setAppId(zfbPayConfig.appID);
            rechargeTrade.setCreateTime(LocalDateTime.now());
            rechargeTrade.setOutTradeNo(out_trade_no);
            rechargeTrade.setTotalAmount(reapply.getTotalAmount());
            rechargeTrade.setUserID(reapply.getUserID());
            rechargeTrade.setTradeModel(0);
            rechargeTrade.setTradeName(reapply.getUserName()+"充值"+String.valueOf(reapply.getTotalAmount())+"元");
            reChargeTradeMapper.insert(rechargeTrade);
            return ResultUtil.success(200,"",aliresponse.getBody());
//            httpResponse.setContentType("text/html;charset="+zfbPayConfig.charset);
//            httpResponse.getWriter().write(aliresponse.getBody());
//            httpResponse.getWriter().flush();
//            httpResponse.getWriter().close();


        }
         return null;
    }

    @Override
    public ResultUtil notify(HttpServletRequest request) throws Exception {
//        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
//            System.out.println("=========支付宝异步回调========");
//
//            Map<String, String> params = new HashMap<>();
//            Map<String, String[]> requestParams = request.getParameterMap();
//            for (String name : requestParams.keySet()) {
//                params.put(name, request.getParameter(name));
//                // System.out.println(name + " = " + request.getParameter(name));
//            }
//
//            String tradeNo = params.get("out_trade_no");
//            String gmtPayment = params.get("gmt_payment");
//            String alipayTradeNo = params.get("trade_no");
//            // 支付宝验签
//            if (Factory.Payment.Common().verifyNotify(params)) {
//                // 验签通过
//                System.out.println("交易名称: " + params.get("subject"));
//                System.out.println("交易状态: " + params.get("trade_status"));
//                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
//                System.out.println("商户订单号: " + params.get("out_trade_no"));
//                System.out.println("交易金额: " + params.get("total_amount"));
//                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
//                System.out.println("买家付款时间: " + params.get("gmt_payment"));
//                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
//
//                // 更新订单未已支付
//
//            }
//        }
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            String tradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");

            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, zfbPayConfig.publicKey, "UTF-8"); // 验证签名
            // 支付宝验签
            if (checkSignature) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

//                RechargeTrade rechargeTrade=reChageMoneMapper.getoreder(params.get("out_trade_no"));
//                rechargeTrade.setTradeModel(1);
//                rechargeTrade.setTradeNo(params.get("trade_no"));
//                rechargeTrade.setBuyerId(params.get("buyer_id"));
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//                rechargeTrade.setTimeStampT(LocalDateTime.parse(params.get("gmt_payment")));
//                reChageMoneMapper.updateById(rechargeTrade);
                RechargeTrade rechargeTrade=reChargeTradeMapper.getoreder(params.get("out_trade_no"));
                if(params.get("out_trade_no")!=null && !Objects.isNull(rechargeTrade))

                {

                    reChargeTradeMapper.updateRechargeTrade(1,params.get("trade_no"),params.get("buyer_id"),params.get("gmt_payment"),rechargeTrade.getOutTradeNo());
                    reChargeMoneyMapper.updateRechargemoney(rechargeTrade.getUserID(),Float.valueOf(params.get("buyer_pay_amount")));
                }

                return ResultUtil.success(200,"成功",tradeNo);
            }
        }


        return null;
    }
}

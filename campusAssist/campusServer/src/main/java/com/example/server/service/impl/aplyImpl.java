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
//            //  ??????API???????????????????????????????????????????????????
//            response = Factory.Payment.Page()
//                    .pay(URLEncoder.encode("test", "UTF-8"), UUID.randomUUID().toString().replace("-",""), "20", "");
//        } catch (Exception e) {
//            System.err.println("??????????????????????????????" + e.getMessage());
//            throw new RuntimeException(e.getMessage(), e);
//        }
        if( Objects.isNull(reapply) || reapply.getUserName()== null|| reapply.getUserID()==null||reapply.getTotalAmount()==null)
            return ResultUtil.fail(40071,"?????????");
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setAppId(zfbPayConfig.appID);
        alipayConfig.setAlipayPublicKey(zfbPayConfig.publicKey);
        alipayConfig.setPrivateKey(zfbPayConfig.privateKey);
        //??????????????????
        alipayConfig.setServerUrl(zfbPayConfig.url);
        alipayConfig.setFormat(zfbPayConfig.format);
//???????????????
        alipayConfig.setCharset(zfbPayConfig.charset);
//??????????????????
        alipayConfig.setSignType(zfbPayConfig.signType);

        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
//???????????????API?????????request???,??????????????????????????????,???????????????????????????alipay.open.public.template.message.industry.modify
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(zfbPayConfig.notify_url);
        request.setReturnUrl(zfbPayConfig.return_url);

        JSONObject bizContent = new JSONObject();
        String out_trade_no =UUID.randomUUID().toString().replace("-","");
        bizContent.set("out_trade_no",out_trade_no);  // ?????????????????????????????????
        bizContent.set("total_amount", reapply.getTotalAmount()); // ??????????????????
        bizContent.set("subject",URLEncoder.encode(reapply.getUserName()+"??????"+String.valueOf(reapply.getTotalAmount())+"???", "UTF-8"));   // ???????????????
        bizContent.set("product_code", "FAST_INSTANT_TRADE_PAY");  // ????????????

        request.setBizContent(bizContent.toString());

        AlipayTradePagePayResponse aliresponse = alipayClient.pageExecute(request);
//????????????????????????????????????
        if(aliresponse.isSuccess()){
            RechargeTrade rechargeTrade = new RechargeTrade();
            rechargeTrade.setAppId(zfbPayConfig.appID);
            rechargeTrade.setCreateTime(LocalDateTime.now());
            rechargeTrade.setOutTradeNo(out_trade_no);
            rechargeTrade.setTotalAmount(reapply.getTotalAmount());
            rechargeTrade.setUserID(reapply.getUserID());
            rechargeTrade.setTradeModel(0);
            rechargeTrade.setTradeName(reapply.getUserName()+"??????"+String.valueOf(reapply.getTotalAmount())+"???");
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
//            System.out.println("=========?????????????????????========");
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
//            // ???????????????
//            if (Factory.Payment.Common().verifyNotify(params)) {
//                // ????????????
//                System.out.println("????????????: " + params.get("subject"));
//                System.out.println("????????????: " + params.get("trade_status"));
//                System.out.println("????????????????????????: " + params.get("trade_no"));
//                System.out.println("???????????????: " + params.get("out_trade_no"));
//                System.out.println("????????????: " + params.get("total_amount"));
//                System.out.println("????????????????????????id: " + params.get("buyer_id"));
//                System.out.println("??????????????????: " + params.get("gmt_payment"));
//                System.out.println("??????????????????: " + params.get("buyer_pay_amount"));
//
//                // ????????????????????????
//
//            }
//        }
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========?????????????????????========");

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
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, zfbPayConfig.publicKey, "UTF-8"); // ????????????
            // ???????????????
            if (checkSignature) {
                // ????????????
                System.out.println("????????????: " + params.get("subject"));
                System.out.println("????????????: " + params.get("trade_status"));
                System.out.println("????????????????????????: " + params.get("trade_no"));
                System.out.println("???????????????: " + params.get("out_trade_no"));
                System.out.println("????????????: " + params.get("total_amount"));
                System.out.println("????????????????????????id: " + params.get("buyer_id"));
                System.out.println("??????????????????: " + params.get("gmt_payment"));
                System.out.println("??????????????????: " + params.get("buyer_pay_amount"));

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

                return ResultUtil.success(200,"??????",tradeNo);
            }
        }


        return null;
    }
}

package com.example.server.controller;

//import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayApiException;
import com.example.server.model.vo.rechage.reapply;
import com.example.server.service.aplySeve;
import com.example.server.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/aply")
public class AplyController {

    @Autowired
    private aplySeve aplySever;

    @PostMapping("alipay")
    public ResultUtil  addalipay(HttpServletResponse response, @RequestBody reapply reapply) throws IOException, AlipayApiException {

        return aplySever.chargeMoney(response,reapply);
    }

    @PostMapping("/notify")
    public ResultUtil payNotify(HttpServletRequest request) throws Exception {

        return aplySever.notify(request);
    }


}

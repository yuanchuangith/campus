package com.example.server.controller;



import com.example.server.model.vo.conpany.conpanyRequstInfo;
import com.example.server.model.vo.obedient.getObedient;
import com.example.server.service.obedientServe;
import com.example.server.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/obedient")
public class obedientController {
    @Autowired
    private com.example.server.service.obedientServe obedientServe;

    @PostMapping("/getObedient")
    public ResultUtil getObedient(@RequestBody getObedient getObedient)
    {
        return obedientServe.getObedient(getObedient);
    }
    @PostMapping("/getplate")
    public ResultUtil getplate(@RequestParam(name="key",required = false)  String key,@RequestParam(name="ID",required = false)  String ID)
    {
        return obedientServe.getplate( ID,key);
    }
    @PostMapping("/getplateObedient")
    public ResultUtil getplateObedient(@RequestBody conpanyRequstInfo  conpanyRequstInfo)
    {
        return obedientServe.getplateObedient( conpanyRequstInfo);
    }
 @PostMapping("/getObedientDetial")
 public ResultUtil getObedientDetial(@RequestBody conpanyRequstInfo  conpanyRequstInfo)
 {
     return obedientServe.getObedientDetial(conpanyRequstInfo);
 }

}

package com.cetc.web.rest;

//import brave.Tracer;
import com.cetc.service.ISleuthFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sleuthFeign")
public class SleuthFeignResource {

    @Autowired
    private ISleuthFeignService feignService;

//    @Autowired
//    private Tracer tracer;

    @GetMapping("/getPort")
    public Integer getPort() {
//        tracer.currentSpan().tag("operator", "feign");
//        System.out.println(tracer.currentSpan().context().traceId());
        return feignService.getPort();
    }
}

package com.example.apimonitor.controller;

import com.example.apimonitor.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/monitor")
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @GetMapping("/health")
    public String health() {
        return "🚀 API监控服务运行正常! " + System.currentTimeMillis();
    }

    @GetMapping("/test")
    public String test() {
        return "测试接口访问成功! 当前时间: " + java.time.LocalDateTime.now();
    }

    @GetMapping("/check")
    public String checkSingleApi(@RequestParam String url) {
        return monitorService.checkApiHealth(url);
    }

    @GetMapping("/check-detailed")
    public Map<String, Object> checkApiDetailed(@RequestParam String url) {
        return monitorService.checkApiHealthDetailed(url);
    }
}
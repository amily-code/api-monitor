package com.example.apimonitor.task;

import com.example.apimonitor.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ScheduledTask {

    @Autowired
    private MonitorService monitorService;

    private final List<String> apiList = Arrays.asList(
            "https://jsonplaceholder.typicode.com/posts/1",
            "https://jsonplaceholder.typicode.com/users/1",
            "https://www.baidu.com",
            "https://www.taobao.com"
    );

    @Scheduled(fixedRate = 120000)
    public void scheduledApiCheck() {
        System.out.println("=== 开始执行API健康检查 ===");
        System.out.println("检查时间: " + java.time.LocalDateTime.now());

        for (String api : apiList) {
            String result = monitorService.checkApiHealth(api);
            System.out.println(result);

            if (result.contains("❌") || result.contains("🚨")) {
                System.out.println("⚠️  发现异常API: " + api);
            }
        }
        System.out.println("=== API健康检查完成 ===\n");
    }

    @Scheduled(fixedRate = 30000)
    public void quickTest() {
        String result = monitorService.checkApiHealth("https://jsonplaceholder.typicode.com/posts/1");
        System.out.println("[快速检查] " + result);
    }
}
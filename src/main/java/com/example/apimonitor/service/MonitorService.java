package com.example.apimonitor.service;

import org.springframework.stereotype.Service;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class MonitorService {

    public String checkApiHealth(String apiUrl) {
        StringBuilder result = new StringBuilder();
        long startTime = System.currentTimeMillis();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            long responseTime = System.currentTimeMillis() - startTime;

            if (responseCode == HttpURLConnection.HTTP_OK) {
                result.append("✅ SUCCESS: ").append(apiUrl)
                        .append(" - 状态: 健康, 响应时间: ").append(responseTime).append("ms");
            } else {
                result.append("❌ FAILED: ").append(apiUrl)
                        .append(" - HTTP状态码: ").append(responseCode)
                        .append(", 响应时间: ").append(responseTime).append("ms");
            }
            connection.disconnect();
        } catch (Exception e) {
            long responseTime = System.currentTimeMillis() - startTime;
            result.append("🚨 ERROR: ").append(apiUrl)
                    .append(" - 检查失败: ").append(e.getMessage())
                    .append(", 响应时间: ").append(responseTime).append("ms");
        }
        return result.toString();
    }

    public Map<String, Object> checkApiHealthDetailed(String apiUrl) {
        Map<String, Object> result = new HashMap<>();
        long startTime = System.currentTimeMillis();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            long responseTime = System.currentTimeMillis() - startTime;

            result.put("url", apiUrl);
            result.put("status", responseCode == 200 ? "HEALTHY" : "UNHEALTHY");
            result.put("responseCode", responseCode);
            result.put("responseTime", responseTime);
            result.put("timestamp", System.currentTimeMillis());
            result.put("message", responseCode == 200 ? "服务健康" : "服务异常");

            connection.disconnect();
        } catch (Exception e) {
            result.put("url", apiUrl);
            result.put("status", "ERROR");
            result.put("error", e.getMessage());
            result.put("responseTime", System.currentTimeMillis() - startTime);
            result.put("timestamp", System.currentTimeMillis());
            result.put("message", "检查失败: " + e.getMessage());
        }

        return result;
    }
}
# API健康监控平台

## 项目简介
基于Spring Boot开发的API健康监控系统，支持定时监控多个服务的可用性和性能。

## 功能特性
- ✅ 多API健康状态监控
- ✅ 定时自动巡检（每2分钟）
- ✅ RESTful API接口
- ✅ 响应时间统计
- ✅ 异常状态检测

## 技术栈
- Spring Boot 4.0.0
- Java 17
- Maven
- Spring Scheduling

## 快速开始
1. 运行 `ApiMonitorApplication.java`
2. 访问 `http://localhost:8081/api/monitor/health`
3. 查看控制台监控日志

## API接口
- GET `/api/monitor/health` - 服务健康检查
- GET `/api/monitor/test` - 测试接口
- GET `/api/monitor/check?url={url}` - 简单API监控
- GET `/api/monitor/check-detailed?url={url}` - 详细API监控(JSON格式)
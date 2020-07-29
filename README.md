# hzero-monitor

## 特征
提供监控审计功能。监控包括服务监控、日志监控、调用链路追踪等监控功能。审计包括数据审计和操作审计。

## 服务配置

- `操作审计`

 ```yaml
    hzero:
      audit:
        operation:
          enable: false     # 全局开关，默认 false
          api-audit:
            enable: true    # API 审计开关，默认 true，如果全局开关关闭，此值无效
          annotation-audit:
            enable: true    # 注解审计（在Bean的方法上添加@OperationalAudit）开关，默认 true，如果全局开关关闭，此值无效
 ```
- '数据审计'
 ```yaml
   # application.yml
   hzero:
     transfer:
       monitor:
         enabled: true # 是否启用数据变更监控功能
       dataAudit:
         enabled: true # 是否启用数据变更审计功能
  ```




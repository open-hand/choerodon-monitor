# hzero-monitor
监控审计服务

## Features
提供监控审计功能。监控包括服务监控、日志监控、调用链路追踪等监控功能。审计包括数据审计和操作审计。

## Data initialization

- 创建数据库，本地创建 `hzero_monitor` 数据库和默认用户，示例如下：

  ```sql
  CREATE USER 'choerodon'@'%' IDENTIFIED BY "123456";
  CREATE DATABASE hzero_monitor DEFAULT CHARACTER SET utf8;
  GRANT ALL PRIVILEGES ON hzero_monitor.* TO choerodon@'%';
  FLUSH PRIVILEGES;
  ```

- 初始化 `hzero_monitor` 数据库，运行项目根目录下的 `init-database.sh`，该脚本默认初始化数据库的地址为 `localhost`，若有变更需要修改脚本文件

  ```sh
  sh init-database.sh
  ```
  

## Changelog

- [更新日志](./CHANGELOG.zh-CN.md)


## Contributing

欢迎参与项目贡献！比如提交PR修复一个bug，或者新建Issue讨论新特性或者变更。

Copyright (c) 2020-present, CHOERODON





# lab-实验室项目

#### sudo ./startup.sh -m standalone 启动nacos
#### ./consul agent -dev -ui -node=consul-dev -client=0.0.0.0
#### nohup ./consul agent -dev -ui -client=0.0.0.0 -data-dir=./data >> ./logs/consul.log &

## 项目进度信息
### 2020-07-13
服务注册中心整体切换为consul
### 2020-07-12
完成第一个分布式项目，当前架构雏形为，服务注册发现为阿里nacos，整体架构为spring cloud。项目启动方式为shell脚本启动，下个版本计划，服务注册中心整体切换为consul，服务部署更换为docker。
### 2020-01-19
创建项目，搭建项目架构

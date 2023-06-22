# spring-cloud-handbook
基于仓库内另一个pangu模块，来完成spring-cloud项目的使用入门手册.

# 版本选择
    spring-cloud-alibaba    spring-cloud                spring-boot
    2021.0.5.0*             Spring Cloud 2021.0.5       2.6.13

    同时要注意的是安装的spring-cloud-alibaba的组件版本要对应上，关系如下，否则不保证可用
    Spring Cloud Alibaba Version	Sentinel Version	Nacos Version	RocketMQ Version	Dubbo Version	Seata Version
    2021.0.5.0                      1.8.6               2.2.0           4.9.4                 ~              1.6.1

# 环境依赖与构建
1. 首先克隆项目https://github.com/dongfangding/ddf-common， 这个是项目的脚手架，执行`mvn clean install`
2. 再克隆项目https://github.com/dongfangding/pangu， 执行`mvn clean install`, 这个是用来维护spring-cloud的版本关系
3. 本项目内的`doc`目录内`docker-compose`直接拷贝然后安装所需中间件

# 数据库脚本


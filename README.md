# micromall

![JDK](https://img.shields.io/badge/JDK-1.8-green.svg?style=flat-square)
[![LICENSE](https://img.shields.io/github/license/micromall-team/micromall.svg?style=flat-square)](https://github.com/micromall-team/micromall/blob/main/LICENSE)
[![star](https://img.shields.io/github/stars/micromall-team/micromall.svg?label=Stars&style=social)](https://github.com/micromall-team/micromall)
[![star](https://gitee.com/micromall-team/micromall/badge/star.svg?theme=white)](https://gitee.com/micromall-team/micromall) 
<p align="center">
	<a href="https://jq.qq.com/?_wv=1027&k=QI1J90T9"><img src="https://img.shields.io/badge/QQ群-894959829-orange"/></a>
</p>

用Spring Boot + Mybatis-Plus后端 + Vue后台管理系统 + uni-app 开发的小商城

目前第一版本的只做基本的权限，下单售后流程

## 快速启动

### 1.环境

| 工具  |  说明  |                             官网                             |
| :---: | :----: | :----------------------------------------------------------: |
|  JDK  |  1.8   | [链接](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) |
| Maven | 3.5.3  |               [链接](http://maven.apache.org/)               |
| MySQL | 5.7.22 |                [链接](https://www.mysql.com/)                |

### 2.下载代码

```
git clone https://github.com/micromall-team/micromall.git
```

### 3.运行

项目采用 [Flyway](https://flywaydb.org/) 来对数据库进行版本管理，导入IDE以后,修改yml配置文件中的数据库配置运行micromall-all下的Application.Java文件；

当然你也可以

```
cd micromall
mvn install
mvn clean package
java -Dfile.encoding=UTF-8 -jar micromall-all/target/micromall-all.jar
```

此时，浏览器打开，输入网址`http://localhost:7879/admin`可以看到`hello admin`
## 接口文档
micromall没有采用swagger来生成文档，而是使用[smart-doc](https://gitee.com/smart-doc-team/smart-doc)来生成文档,
访问`http://localhost:7879/static/doc/index.html` 可以查看API接口文档


## 后台功能

- [ ] 用户管理
  - [ ] 会员管理
  - [ ] 收货地址
  - [ ] 会员收藏
  - [ ] 会员足迹
  - [ ] 搜索历史
  - [ ] 意见反馈
- [ ] 商场管理
  - [x] 品牌管理
  - [x] 分类管理
  - [ ] 订单管理
  - [ ] 售后订单
  - [x] 通用问题
- [ ] 商品管理
  - [ ] 商品列表
  - [ ] 商品评论
  - [ ] 商品上架
- [ ] 推广管理
  - [ ] 优惠券管理
  - [ ] 轮播图管理
- [ ] 系统管理
  - [x] 角色管理
  - [ ] 操作日志
  - [x] 对象存储
  - [x] 管理员
- [x] 配置管理
- [ ] 统计报表

## 移动端功能

- [ ] 首页
- [ ] 分类列表、分类详情
- [ ] 品牌列表、品牌详情
- [ ] 新品首发、人气推荐
- [ ] 优惠券列表、优惠券选择
- [ ] 搜索
- [ ] 商品详情、商品评价、商品分享
- [ ] 购物车
- [ ] 下单
- [ ] 支付宝，微信支付
- [ ] 订单列表、订单详情、订单售后
- [ ] 地址、收藏、足迹、意见反馈

## 警告

1. 本项目仅用于学习练习
2. 本项目还不完善，仍处在开发中，不承担任何使用后果

## 致谢

本项目参考以下项目

1. [litemall](https://github.com/linlinjava/litemall)

   项目介绍 :Spring Boot后端 + Vue管理员前端 + 微信小程序用户前端 + Vue用户移动端 

## 问题

开发者有问题或者好的建议可以用Issues反馈交流，请给出详细信息

QQ群：894959829

## 贡献指南

任何形式的贡献都欢迎，包括：

- Issue里面报告的BUG
- Issue里面对业务或技术的讨论
- Pull Request
- 对文档的意见或补充
- 其他任何有意义本项目的行为

个人能力有限，欢迎一起开发。
## License

[Apache License 2.0](https://github.com/micromall-team/micromall/blob/main/LICENSE)

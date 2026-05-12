# 企鹅蛋糕 v2 架构说明

## 设计目标

- 官网前台与后台运营分离：未登录用户看产品，登录用户进入总部/门店运营后台。
- 外卖平台“外卖”作为内部外卖聚合层，对接京东外卖、淘宝闪购和 Mock 通道。
- 地图大屏前端内嵌高德地图 JS API，未配置 Key 时自动降级为离线珠三角 12 城展示。
- 引入“蛋糕解耦中间件”，把支付、验券、外卖、仓库、采购、配送之间的直接调用改为事件驱动。

## 分层架构

```text
interfaces/rest  → application services → domain model/events → cake decoupling middleware → infrastructure adapters
```

## 核心模式

- DDD 聚合：`OrderAggregate`、`CakeLifecycleStatus`
- 六边形架构：`PaymentGateway`、`VoucherAdapter`、`TakeoutAdapter`
- Outbox 思路：`EventEnvelope`、`CakeDecouplingMiddleware`
- 幂等键：`topic + aggregateId`
- 地理位置上报：`/api/geo/location-reports`
- 外卖接口别名：`/api/takeout/**` 与 `/api/takeout/**`

## 生产化建议

当前项目默认走 Mock 与内存 Outbox，真实生产建议：

1. 用 MySQL `integration_outbox` 表落库事件。
2. 用 RabbitMQ/Kafka 承载事件路由。
3. 用 Redis 做幂等锁和防重复提交。
4. 支付/验券/外卖接入官方 SDK 后增加签名验签。
5. 高德地图 Key 与安全密钥放入 CI/CD 环境变量。


## 三端架构优化

新版将系统拆为三层产品界面：

```text
前台 Frontstage
  - 官网、商城、详情、订单状态、登录
中台 Middle Platform
  - 订单中台、支付网关、验券中心、外卖聚合、事件总线、配送调度
后台 Backstage
  - 经营报表、门店、仓库、货损、进货、原材料、配送、权限
```

后端继续使用领域分层和适配器模式：

```text
Controller → Application Service → Domain Model / Domain Event → Port → Adapter / Gateway
```

支付、验券、外卖、配送、仓储都通过 `CakeDecouplingMiddleware` 发布标准化事件，避免业务模块直接互相调用。

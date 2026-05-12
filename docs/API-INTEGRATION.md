# 第三方平台接入说明

## 外卖平台：外卖

“外卖”是本项目内部的外卖聚合平台，不是一个外部品牌。它统一封装：

- 京东外卖：`JdTakeoutAdapter`
- 淘宝闪购：`TaobaoShangouAdapter`
- 本地演示：`MockTakeoutAdapter`

接口别名：

```http
GET  /api/takeout/platforms
POST /api/takeout/orders/sync
POST /api/takeout/orders/status
GET  /api/takeout/platforms
POST /api/takeout/orders/sync
POST /api/takeout/orders/status
```

真实接入时需要替换适配器中的 Mock 逻辑，加入官方 SDK、签名、回调验签和幂等处理。

## 高德地图

前端组件：`frontend/src/components/AMapStoreDashboard.vue`

配置：

```env
VITE_AMAP_KEY=你的高德Web端Key
VITE_AMAP_SECURITY_JS_CODE=你的安全密钥
```

未配置时会自动显示离线珠三角 12 城模拟地图，便于演示。

## 蛋糕解耦中间件

核心类：

- `CakeDecouplingMiddleware`
- `CakeEventBus`
- `EventEnvelope`
- `CakeEventType`

业务系统只发布事件，不直接调用下游，降低订单、仓库、配送、支付之间的耦合。

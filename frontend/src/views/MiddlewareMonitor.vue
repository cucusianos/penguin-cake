<template>
  <section class="oryzo-page">
    <div class="oryzo-eyebrow">Cake Decoupling Middleware</div>
    <h1 class="section-title">蛋糕解耦中间件</h1>
    <p class="page-desc">
      统一承接支付、验券、外卖、仓库、配送事件，使用事件信封、幂等键、Outbox 与通道适配器降低业务耦合。
    </p>

    <div class="oryzo-grid cols-4 metric-row">
      <div v-for="metric in metrics" :key="metric.label" class="oryzo-card metric">
        <span>{{ metric.label }}</span>
        <b>{{ metric.value }}</b>
        <span>{{ metric.desc }}</span>
      </div>
    </div>

    <table class="table-dark">
      <thead>
        <tr>
          <th>事件 ID</th>
          <th>类型</th>
          <th>聚合 ID</th>
          <th>状态</th>
          <th>通道</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="event in events" :key="event.id">
          <td>{{ event.id }}</td>
          <td>{{ event.type }}</td>
          <td>{{ event.aggregate }}</td>
          <td><span class="status-pill good">{{ event.status }}</span></td>
          <td>{{ event.channel }}</td>
        </tr>
      </tbody>
    </table>
  </section>
</template>

<script setup lang="ts">
type MiddlewareMetric = {
  label: string
  value: string
  desc: string
}

type MiddlewareEvent = {
  id: string
  type: string
  aggregate: string
  status: string
  channel: string
}

const metrics: MiddlewareMetric[] = [
  { label: '事件主题', value: '18', desc: 'Payment / Takeout / Delivery' },
  { label: '今日事件', value: '19,248', desc: '成功率 99.93%' },
  { label: '积压', value: '42', desc: 'RabbitMQ 队列' },
  { label: '适配器', value: '11', desc: '可插拔网关' }
]

const events: MiddlewareEvent[] = [
  { id: 'EVT-8912', type: 'ORDER_PAID', aggregate: 'PC202605110001', status: '已路由', channel: 'warehouse.reserve' },
  { id: 'EVT-8913', type: 'TAKEOUT_ORDER_SYNCED', aggregate: 'WM20260511001', status: '已投递', channel: 'order.ingest' },
  { id: 'EVT-8914', type: 'DELIVERY_GEO_REPORTED', aggregate: 'DL20260511001', status: '已消费', channel: 'map.realtime' }
]
</script>

<style scoped>
.page-desc {
  color: var(--color-faint-hazel);
  line-height: 1.8;
}

.metric-row {
  margin: 24px 0;
}
</style>

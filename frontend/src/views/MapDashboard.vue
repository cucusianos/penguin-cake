<template>
  <section class="oryzo-page">
    <div class="header-row">
      <div>
        <div class="oryzo-eyebrow">AMap Embedded Big Screen</div>
        <h1 class="section-title">珠三角 12 城地图大屏</h1>
        <p>前端内嵌高德地图 JS API；未配置 Key 时自动降级到离线模拟地图层。</p>
      </div>
      <span class="status-pill">实时刷新 · 30s</span>
    </div>

    <div class="oryzo-grid cols-4 metric-row">
      <div v-for="metric in metrics" :key="metric.label" class="oryzo-card metric">
        <span>{{ metric.label }}</span>
        <b>{{ metric.value }}</b>
        <span>{{ metric.desc }}</span>
      </div>
    </div>

    <AMapStoreDashboard />
  </section>
</template>

<script setup lang="ts">
import AMapStoreDashboard from '../components/AMapStoreDashboard.vue'

type MapMetric = {
  label: string
  value: string
  desc: string
}

const metrics: MapMetric[] = [
  { label: '今日销售额', value: '¥126.8w', desc: '同比 +18.4%' },
  { label: '销量', value: '4,927', desc: '官网 / 外卖 / 团购' },
  { label: '配送中', value: '386', desc: '冷链温控正常 97%' },
  { label: '货损率', value: '0.72%', desc: '低于阈值 1.2%' }
]
</script>

<style scoped>
.header-row {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  gap: 18px;
  margin-bottom: 18px;
}

.header-row p {
  color: var(--color-faint-hazel);
}

.metric-row {
  margin-bottom: 18px;
}
</style>

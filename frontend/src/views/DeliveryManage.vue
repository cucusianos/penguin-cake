<template>
  <section class="oryzo-page">
    <div class="oryzo-eyebrow">Delivery & Geo Report</div>
    <h1 class="section-title">配送管理 / 地理位置上报</h1>

    <div class="oryzo-grid cols-3 metric-row">
      <div v-for="metric in metrics" :key="metric.label" class="oryzo-card metric">
        <span>{{ metric.label }}</span>
        <b>{{ metric.value }}</b>
        <span>{{ metric.desc }}</span>
      </div>
    </div>

    <table class="table-dark">
      <thead>
        <tr>
          <th>配送单</th>
          <th>骑手</th>
          <th>门店</th>
          <th>GPS</th>
          <th>温度</th>
          <th>状态</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="row in rows" :key="row.id">
          <td>{{ row.id }}</td>
          <td>{{ row.rider }}</td>
          <td>{{ row.store }}</td>
          <td>{{ row.lng }}, {{ row.lat }}</td>
          <td>{{ row.temp }}°C</td>
          <td><span class="status-pill good">{{ row.status }}</span></td>
        </tr>
      </tbody>
    </table>
  </section>
</template>

<script setup lang="ts">
type DeliveryMetric = {
  label: string
  value: string
  desc: string
}

type DeliveryRow = {
  id: string
  rider: string
  store: string
  lng: number
  lat: number
  temp: number
  status: string
}

const metrics: DeliveryMetric[] = [
  { label: '配送中', value: '386', desc: '骑手轨迹上报' },
  { label: '平均送达', value: '32min', desc: '珠三角同城' },
  { label: '冷链异常', value: '3', desc: '需人工处理' }
]

const rows: DeliveryRow[] = [
  { id: 'DL20260511001', rider: '粤A-小林', store: '广州天河店', lng: 113.33, lat: 23.13, temp: 4.1, status: '配送中' },
  { id: 'DL20260511002', rider: '粤B-阿敏', store: '深圳南山店', lng: 113.93, lat: 22.52, temp: 3.9, status: '已取货' }
]
</script>

<style scoped>
.metric-row {
  margin-bottom: 18px;
}
</style>

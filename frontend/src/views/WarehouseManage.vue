<template>
  <section class="oryzo-page">
    <div class="oryzo-eyebrow">Warehouse & Cake State</div>
    <h1 class="section-title">仓库管理 / 货损上报 / 蛋糕状态</h1>

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
          <th>批次</th>
          <th>蛋糕状态</th>
          <th>仓库</th>
          <th>保质期</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="row in rows" :key="row.batch">
          <td>{{ row.batch }}</td>
          <td><span class="status-pill">{{ row.status }}</span></td>
          <td>{{ row.warehouse }}</td>
          <td>{{ row.expire }}</td>
          <td><button class="oryzo-button secondary">上报货损</button></td>
        </tr>
      </tbody>
    </table>
  </section>
</template>

<script setup lang="ts">
type WarehouseMetric = {
  label: string
  value: string
  desc: string
}

type CakeBatchRow = {
  batch: string
  status: string
  warehouse: string
  expire: string
}

const metrics: WarehouseMetric[] = [
  { label: '中心仓库存', value: '18,260', desc: '件' },
  { label: '临期批次', value: '23', desc: '需调拨' },
  { label: '货损上报', value: '7', desc: '今日' },
  { label: '冷库温度', value: '3.8°C', desc: '正常' }
]

const rows: CakeBatchRow[] = [
  { batch: 'CAKE-GZ-0511-A', status: '冷藏待售', warehouse: '广州中心仓', expire: '48h' },
  { batch: 'CAKE-SZ-0511-C', status: '配送锁定', warehouse: '深圳前置仓', expire: '36h' },
  { batch: 'CAKE-FS-0510-B', status: '临期促销', warehouse: '佛山仓', expire: '12h' }
]
</script>

<style scoped>
.metric-row {
  margin-bottom: 18px;
}
</style>

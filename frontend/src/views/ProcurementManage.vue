<template>
  <section class="oryzo-page">
    <div class="oryzo-eyebrow">Procurement & Raw Materials</div>
    <h1 class="section-title">进货管理 / 原材料展示</h1>

    <div class="oryzo-grid cols-3 material-grid">
      <div v-for="material in materials" :key="material.name" class="oryzo-card card-pad">
        <h3>{{ material.name }}</h3>
        <p>库存 {{ material.stock }} · 供应商 {{ material.supplier }}</p>
        <span class="status-pill good">{{ material.qc }}</span>
      </div>
    </div>

    <table class="table-dark">
      <thead>
        <tr>
          <th>采购单</th>
          <th>原料</th>
          <th>数量</th>
          <th>状态</th>
          <th>预计到货</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="order in orders" :key="order.id">
          <td>{{ order.id }}</td>
          <td>{{ order.item }}</td>
          <td>{{ order.qty }}</td>
          <td><span class="status-pill">{{ order.status }}</span></td>
          <td>{{ order.eta }}</td>
        </tr>
      </tbody>
    </table>
  </section>
</template>

<script setup lang="ts">
type Material = {
  name: string
  stock: string
  supplier: string
  qc: string
}

type PurchaseOrder = {
  id: string
  item: string
  qty: string
  status: string
  eta: string
}

const materials: Material[] = [
  { name: '动物奶油', stock: '1,280L', supplier: '广乳冷链', qc: '温控合格' },
  { name: '莓果组合', stock: '860kg', supplier: '珠海鲜果', qc: '抽检合格' },
  { name: '低筋面粉', stock: '4,600kg', supplier: '湾区粮仓', qc: '批次可追溯' }
]

const orders: PurchaseOrder[] = [
  { id: 'PO20260511001', item: '动物奶油', qty: '500L', status: '运输中', eta: '今日 16:30' },
  { id: 'PO20260511002', item: '可可粉', qty: '220kg', status: '待质检', eta: '明日 09:00' }
]
</script>

<style scoped>
.material-grid {
  margin-bottom: 18px;
}

.material-grid p {
  color: var(--color-faint-hazel);
}
</style>

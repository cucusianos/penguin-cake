<template>
  <section class="oryzo-page" :style="embedded ? 'padding: 0; width: 100%' : ''">
    <div class="oryzo-eyebrow">Order Lifecycle</div>
    <h1 class="section-title">订单状态中心</h1>

    <div class="oryzo-card query-card">
      <input v-model="orderNo" class="input-dark" placeholder="输入订单号，例如 PC202605110001" />
      <button class="oryzo-button" @click="query">查询</button>
    </div>

    <div class="oryzo-grid cols-3">
      <div v-for="state in states" :key="state.code" class="oryzo-card card-pad">
        <span class="status-pill">{{ state.code }}</span>
        <h3>{{ state.title }}</h3>
        <p>{{ state.desc }}</p>
      </div>
    </div>
  </section>
</template>

<script setup lang="ts">
import { computed, ref } from 'vue'

type OrderState = {
  code: string
  title: string
  desc: string
}

const embedded = computed(() => location.pathname.startsWith('/admin'))
const orderNo = ref('PC202605110001')

const states = ref<OrderState[]>([
  { code: 'PENDING_PAYMENT', title: '待支付', desc: '已创建订单，等待用户支付' },
  { code: 'PAID', title: '已支付', desc: '支付回调验签成功' },
  { code: 'BAKING', title: '制作中', desc: '门店或中央厨房开始制作' },
  { code: 'COLD_CHAIN_READY', title: '冷链待发', desc: '已入冷链包装，等待骑手' },
  { code: 'DELIVERING', title: '配送中', desc: '骑手 GPS 与温控上报中' },
  { code: 'COMPLETED', title: '已完成', desc: '签收完成并进入对账' }
])

function query() {
  states.value = [...states.value]
}
</script>

<style scoped>
.query-card {
  display: flex;
  gap: 18px;
  align-items: center;
  margin-bottom: 24px;
}

.oryzo-card p {
  color: var(--color-faint-hazel);
}
</style>

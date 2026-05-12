<template>
  <section class="oryzo-page">
    <div class="oryzo-eyebrow">Payment Gateway</div>
    <h1 class="section-title">支付网关状态</h1>
    <p class="page-desc">中台支付网关用于统一管理微信支付、支付宝、Stripe、虚拟币支付和 Mock 通道，前台收银台会通过这里创建支付单。</p>

    <div class="oryzo-grid cols-4 payment-cards">
      <div v-for="gateway in gateways" :key="gateway.name" class="oryzo-card card-pad gateway-card">
        <strong>{{ gateway.icon }}</strong>
        <h2>{{ gateway.name }}</h2>
        <span :class="['status-pill', gateway.warn ? 'warn' : 'good']">{{ gateway.status }}</span>
        <p>成功率 {{ gateway.rate }}</p>
        <p>今日交易 {{ gateway.count }} 笔</p>
      </div>
    </div>

    <div class="oryzo-card card-pad log-card">
      <h2>最近支付事件</h2>
      <table class="table-dark compact-table">
        <thead>
          <tr>
            <th>时间</th>
            <th>支付单号</th>
            <th>渠道</th>
            <th>金额</th>
            <th>状态</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="log in logs" :key="log.no">
            <td>{{ log.time }}</td>
            <td>{{ log.no }}</td>
            <td>{{ log.channel }}</td>
            <td>¥{{ log.amount }}</td>
            <td><span class="status-pill good">{{ log.status }}</span></td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
</template>

<script setup lang="ts">
const gateways = [
  { icon: '🟢', name: '微信支付', status: '正常', rate: '98.73%', count: 862 },
  { icon: '🔵', name: '支付宝', status: '正常', rate: '98.54%', count: 534 },
  { icon: '💜', name: 'Stripe', status: '正常', rate: '99.21%', count: 213 },
  { icon: '₿', name: '虚拟币支付', status: '维护中', rate: '--', count: 37, warn: true }
]

const logs = [
  { time: '10:21:37', no: '#PAY-20250518-77123', channel: '微信支付', amount: '198.00', status: '支付成功' },
  { time: '10:18:06', no: '#PAY-20250518-77116', channel: '支付宝', amount: '268.00', status: '支付成功' },
  { time: '10:11:42', no: '#PAY-20250518-77089', channel: 'Stripe', amount: '288.00', status: '支付成功' },
  { time: '10:03:18', no: '#PAY-20250518-77021', channel: 'Mock 支付', amount: '238.00', status: '支付成功' }
]
</script>

<style scoped>
.page-desc {
  max-width: 900px;
  margin-bottom: 18px;
  line-height: 1.8;
  color: var(--color-faint-hazel);
}

.payment-cards {
  margin-bottom: 18px;
}

.gateway-card {
  display: grid;
  gap: 10px;
}

.gateway-card strong {
  display: grid;
  width: 48px;
  height: 48px;
  font-size: 22px;
  background: rgba(255, 237, 215, 0.06);
  border: 1px solid var(--color-border);
  border-radius: 12px;
  place-items: center;
}

.gateway-card h2,
.gateway-card p {
  margin: 0;
}

.gateway-card p {
  color: var(--color-faint-hazel);
}
</style>

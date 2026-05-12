<template>
  <section class="oryzo-page">
    <div class="oryzo-eyebrow">Payment Gateway</div>
    <h1 class="section-title">统一收银台</h1>

    <div class="checkout-steps">
      <span class="active">1 加入购物车</span>
      <span class="active">2 填写个人信息与地址</span>
      <span class="active">3 支付</span>
    </div>

    <div v-if="!cart.hasItems || !cart.hasCustomer" class="oryzo-card card-pad blocked-card">
      <h2>请先完成购物车与配送信息</h2>
      <p>为保证订单履约，必须先选择蛋糕，并填写收货人、手机号、城市和详细地址，才能进入支付页面。</p>
      <RouterLink class="oryzo-button" to="/cart">返回购物车填写信息</RouterLink>
    </div>

    <div v-else class="checkout-layout">
      <section class="oryzo-card card-pad">
        <div class="panel-title-row">
          <div>
            <p class="muted">订单号</p>
            <h2>{{ orderNo }}</h2>
          </div>
          <span class="status-pill warn">待支付</span>
        </div>

        <div class="order-items">
          <article v-for="item in cart.items" :key="item.id" class="order-item">
            <img :src="item.image" :alt="item.name" />
            <div>
              <h3>{{ item.name }}</h3>
              <p>{{ item.quantity }} 件 · ¥{{ item.price }} / 件</p>
            </div>
            <b>¥{{ item.price * item.quantity }}</b>
          </article>
        </div>

        <div class="summary-box">
          <span>应付金额</span>
          <b>¥{{ cart.totalAmount }}</b>
        </div>
      </section>

      <aside class="oryzo-card card-pad">
        <h2>收货信息</h2>
        <div class="info-grid">
          <span>收货人</span><b>{{ cart.customer.name }}</b>
          <span>手机号</span><b>{{ cart.customer.phone }}</b>
          <span>配送城市</span><b>{{ cart.customer.city }}</b>
          <span>详细地址</span><b>{{ cart.customer.address }}</b>
          <span>送达时间</span><b>{{ cart.customer.deliveryTime }}</b>
          <span>备注</span><b>{{ cart.customer.note || '无' }}</b>
        </div>

        <div class="ghost-divider"></div>
        <h2>选择支付方式</h2>
        <div class="provider-grid">
          <n-button
            v-for="provider in providers"
            :key="provider.value"
            type="primary"
            @click="pay(provider.value)"
          >
            {{ provider.label }}
          </n-button>
        </div>
      </aside>
    </div>

    <pre v-if="result" class="result-box">{{ result }}</pre>
  </section>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { NButton, useMessage } from 'naive-ui'
import { createPayment } from '../api/modules'
import { useCartStore } from '../stores/cart'

const message = useMessage()
const cart = useCartStore()
const orderNo = `QG${Date.now()}`
const result = ref<unknown>()

const providers = [
  { label: '微信支付', value: 'WECHAT_PAY' },
  { label: '支付宝', value: 'ALIPAY' },
  { label: 'Stripe', value: 'STRIPE' },
  { label: '虚拟币支付', value: 'CRYPTO' },
  { label: 'Mock 支付', value: 'MOCK' }
]

async function pay(provider: string) {
  if (!cart.hasItems || !cart.hasCustomer) {
    message.warning('请先完成购物车与配送信息')
    return
  }

  result.value = await createPayment({
    orderNo,
    provider,
    amount: cart.totalAmount,
    subject: `企鹅蛋糕订单 ${cart.totalQuantity} 件`
  })
  message.success('支付单已创建')
}
</script>

<style scoped>
.checkout-steps {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  margin-bottom: 18px;
}

.checkout-steps span {
  padding: 10px 16px;
  color: var(--color-faint-hazel);
  background: rgba(255, 237, 215, 0.05);
  border: 1px solid var(--color-border);
  border-radius: 999px;
}

.checkout-steps span.active {
  color: var(--color-light-cork);
  background: rgba(220, 80, 0, 0.16);
  border-color: rgba(220, 80, 0, 0.48);
}

.checkout-layout {
  display: grid;
  grid-template-columns: 1.25fr 0.75fr;
  gap: 18px;
}

.panel-title-row,
.summary-box,
.order-item {
  display: flex;
  gap: 14px;
  align-items: center;
  justify-content: space-between;
}

.panel-title-row h2,
.order-item h3 {
  margin: 0;
}

.order-items {
  display: grid;
  gap: 12px;
  margin: 18px 0;
}

.order-item {
  padding: 12px;
  background: rgba(255, 237, 215, 0.04);
  border: 1px solid var(--color-border);
  border-radius: 12px;
}

.order-item img {
  width: 84px;
  height: 64px;
  object-fit: cover;
  border-radius: 10px;
}

.order-item div {
  flex: 1;
}

.order-item p,
.muted,
.blocked-card p {
  color: var(--color-faint-hazel);
}

.summary-box {
  padding: 16px;
  background: rgba(220, 80, 0, 0.12);
  border: 1px solid rgba(220, 80, 0, 0.38);
  border-radius: 12px;
}

.summary-box b {
  font-size: 30px;
}

.provider-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(120px, 1fr));
  gap: 12px;
}

.info-grid {
  display: grid;
  grid-template-columns: 92px 1fr;
  gap: 12px;
  color: var(--color-faint-hazel);
}

.info-grid b {
  color: var(--color-light-cork);
}

.result-box {
  padding: 16px;
  margin-top: 18px;
  overflow: auto;
  color: var(--color-cork-dust);
  white-space: pre-wrap;
  background: rgba(255, 237, 215, 0.04);
  border: 1px solid var(--color-border);
  border-radius: 12px;
}

.blocked-card {
  display: grid;
  gap: 12px;
  max-width: 720px;
}

@media (max-width: 960px) {
  .checkout-layout {
    grid-template-columns: 1fr;
  }
}
</style>

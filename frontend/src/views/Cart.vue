<template>
  <div class="oryzo-shell">
    <header class="oryzo-nav">
      <RouterLink to="/" class="brand-mark">
        <span class="brand-penguin">🐧</span>
        <span>企鹅蛋糕</span>
      </RouterLink>
      <nav class="nav-links">
        <RouterLink to="/">继续选购</RouterLink>
        <RouterLink to="/order-status">订单状态</RouterLink>
        <RouterLink to="/login">登录后台</RouterLink>
      </nav>
    </header>

    <main class="oryzo-page">
      <div class="oryzo-eyebrow">SHOPPING CART</div>
      <h1 class="section-title">购物车与配送信息</h1>

      <div class="checkout-steps">
        <span class="active">1 加入购物车</span>
        <span class="active">2 填写个人信息与地址</span>
        <span>3 进入支付页面</span>
      </div>

      <div class="cart-layout">
        <section class="oryzo-card card-pad">
          <div class="panel-title-row">
            <h2>已选蛋糕</h2>
            <span class="status-pill">{{ cart.totalQuantity }} 件</span>
          </div>

          <div v-if="!cart.hasItems" class="empty-cart">
            <p>购物车还是空的，先回到官网前台挑选蛋糕。</p>
            <RouterLink class="oryzo-button" to="/">去选购蛋糕</RouterLink>
          </div>

          <div v-else class="cart-list">
            <article v-for="item in cart.items" :key="item.id" class="cart-item">
              <img :src="item.image" :alt="item.name" />
              <div>
                <h3>{{ item.name }}</h3>
                <p>{{ item.description }}</p>
                <span class="status-pill">{{ item.category }}</span>
              </div>
              <div class="qty-box">
                <button type="button" @click="cart.decrease(item.id)">−</button>
                <strong>{{ item.quantity }}</strong>
                <button type="button" @click="cart.increase(item.id)">+</button>
              </div>
              <b>¥{{ item.price * item.quantity }}</b>
              <button class="remove-btn" type="button" @click="cart.removeItem(item.id)">移除</button>
            </article>
          </div>
        </section>

        <aside class="oryzo-card card-pad">
          <h2>收货人与配送地址</h2>
          <p class="muted">必须填写完整个人信息和地址后，才能进入支付页面。</p>

          <form class="address-form" @submit.prevent="goPay">
            <label>
              收货人姓名
              <input v-model="form.name" class="input-dark" placeholder="例如：张先生" />
            </label>
            <label>
              手机号码
              <input v-model="form.phone" class="input-dark" placeholder="例如：13800000000" />
            </label>
            <label>
              配送城市
              <select v-model="form.city" class="input-dark">
                <option v-for="city in cities" :key="city" :value="city">{{ city }}</option>
              </select>
            </label>
            <label>
              详细地址
              <input v-model="form.address" class="input-dark" placeholder="区 / 街道 / 门牌号" />
            </label>
            <label>
              期望送达时间
              <select v-model="form.deliveryTime" class="input-dark">
                <option>今天 18:00-20:00</option>
                <option>今天 20:00-22:00</option>
                <option>明天 10:00-12:00</option>
                <option>明天 14:00-16:00</option>
              </select>
            </label>
            <label>
              备注 / 祝福语
              <textarea v-model="form.note" class="input-dark textarea-dark" placeholder="例如：生日快乐，注意冷链配送"></textarea>
            </label>

            <div class="summary-box">
              <span>商品小计</span>
              <b>¥{{ cart.totalAmount }}</b>
            </div>
            <button class="oryzo-button submit-btn" type="submit" :disabled="!cart.hasItems">确认信息，进入支付 →</button>
          </form>
        </aside>
      </div>
    </main>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useMessage } from 'naive-ui'
import { useCartStore } from '../stores/cart'

const router = useRouter()
const message = useMessage()
const cart = useCartStore()

const cities = ['广州', '深圳', '佛山', '东莞', '珠海', '中山', '江门', '惠州', '肇庆', '清远', '香港', '澳门']

const form = reactive({ ...cart.customer })

function goPay() {
  if (!cart.hasItems) {
    message.warning('请先选择蛋糕')
    return
  }

  if (!form.name.trim() || !form.phone.trim() || !form.city.trim() || !form.address.trim()) {
    message.warning('请完整填写收货人、电话、城市和详细地址')
    return
  }

  cart.updateCustomer(form)
  router.push('/checkout')
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

.cart-layout {
  display: grid;
  grid-template-columns: minmax(0, 1.25fr) minmax(360px, 0.75fr);
  gap: 18px;
}

.panel-title-row,
.summary-box {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
}

.panel-title-row h2,
.cart-item h3 {
  margin: 0;
}

.empty-cart {
  display: grid;
  min-height: 240px;
  place-items: center;
  text-align: center;
  color: var(--color-faint-hazel);
}

.cart-list {
  display: grid;
  gap: 12px;
}

.cart-item {
  display: grid;
  grid-template-columns: 92px minmax(0, 1fr) 112px 90px 72px;
  gap: 14px;
  align-items: center;
  padding: 12px;
  background: rgba(255, 237, 215, 0.04);
  border: 1px solid var(--color-border);
  border-radius: 12px;
}

.cart-item img {
  width: 92px;
  height: 72px;
  object-fit: cover;
  border-radius: 10px;
}

.cart-item p,
.muted {
  color: var(--color-faint-hazel);
}

.qty-box {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
}

.qty-box button,
.remove-btn {
  padding: 8px 10px;
  color: var(--color-cork-dust);
  background: rgba(255, 237, 215, 0.06);
  border: 1px solid var(--color-border);
  border-radius: 999px;
}

.address-form {
  display: grid;
  gap: 14px;
}

.address-form label {
  display: grid;
  gap: 8px;
  color: var(--color-faint-hazel);
}

.textarea-dark {
  min-height: 96px;
  border-radius: 18px;
  resize: vertical;
}

.summary-box {
  padding: 16px;
  margin-top: 4px;
  background: rgba(220, 80, 0, 0.12);
  border: 1px solid rgba(220, 80, 0, 0.38);
  border-radius: 12px;
}

.summary-box b {
  font-size: 28px;
  color: var(--color-light-cork);
}

.submit-btn {
  width: 100%;
}

.submit-btn:disabled {
  cursor: not-allowed;
  opacity: 0.45;
}

@media (max-width: 1120px) {
  .cart-layout {
    grid-template-columns: 1fr;
  }

  .cart-item {
    grid-template-columns: 84px 1fr;
  }
}
</style>

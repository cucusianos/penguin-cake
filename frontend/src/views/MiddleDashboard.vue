<template>
  <section class="middle-dashboard">
    <header class="stage-header">
      <h1>中台运营中心</h1>
      <input class="input-dark search" placeholder="搜索订单 / 门店 / 用户 / 商品  ⌘K" />
      <div class="stage-actions">
        <span class="status-pill orange">🔔 12</span>
        <span class="status-pill">运营专员</span>
        <span class="status-pill">张店长 · 超级管理员</span>
      </div>
    </header>

    <div class="middle-grid">
      <section class="dashboard-card card-pad orders-card">
        <div class="panel-title-row">
          <h2>今日订单概览</h2>
          <span class="muted">更新于 10:30:00 ›</span>
        </div>
        <div class="mini-kpi-row">
          <div><span>订单总量(单)</span><b>1,842</b><small>较昨日 +12.6%</small></div>
          <div><span>营业额(元)</span><b>286,560.00</b><small>较昨日 +8.3%</small></div>
          <div><span>客单价(元)</span><b>155.70</b><small class="down">较昨日 -1.2%</small></div>
          <div><span>退款/售后(单)</span><b>28</b><small class="down">较昨日 -9.7%</small></div>
        </div>
        <div class="fake-chart"><i class="line"></i></div>
      </section>

      <section class="dashboard-card card-pad flow-card">
        <div class="panel-title-row">
          <h2>渠道订单流转</h2>
          <span>更多 ›</span>
        </div>
        <div class="flow-steps">
          <div v-for="step in flowSteps" :key="step.name">
            <span>{{ step.name }}</span>
            <b>{{ step.value }}</b>
            <small>{{ step.change }}</small>
          </div>
        </div>
        <div class="flow-lower">
          <div>
            <h3>渠道分布</h3>
            <div class="donut" data-label="1,842"></div>
          </div>
          <div>
            <h3>订单趋势（近7天）</h3>
            <div class="bar-row">
              <i v-for="height in [72, 82, 76, 90, 85, 96, 88]" :key="height" class="bar" :style="{ height: `${height}%` }"></i>
              <i v-for="height in [54, 67, 62, 74, 68, 70, 64]" :key="`g-${height}`" class="bar olive" :style="{ height: `${height}%` }"></i>
            </div>
          </div>
        </div>
      </section>

      <aside class="right-stack">
        <section class="dashboard-card card-pad">
          <div class="panel-title-row"><h2>外卖平台聚合</h2><span>更多 ›</span></div>
          <div class="platform-list">
            <div v-for="item in platformRows" :key="item.name" class="platform-card">
              <strong>{{ item.name }}</strong>
              <span class="status-pill green">{{ item.status }}</span>
              <b>{{ item.orders }}</b>
              <small>营业额 {{ item.amount }}</small>
            </div>
          </div>
        </section>
        <section class="dashboard-card card-pad">
          <div class="panel-title-row"><h2>支付网关状态</h2><span>更多 ›</span></div>
          <div class="gateway-grid">
            <div v-for="pay in paymentRows" :key="pay.name">
              <b>{{ pay.name }}</b>
              <span :class="['status-pill', pay.status === '正常' ? 'green' : 'orange']">{{ pay.status }}</span>
              <small>成功率 {{ pay.rate }}</small>
            </div>
          </div>
        </section>
        <section class="dashboard-card card-pad">
          <div class="panel-title-row"><h2>验券中心</h2><span>更多 ›</span></div>
          <div class="coupon-list">
            <div><b>美团券</b><span class="status-pill green">正常</span><small>今日核销 126</small></div>
            <div><b>口碑券</b><span class="status-pill green">正常</span><small>今日核销 58</small></div>
            <div><b>抖音券</b><span class="status-pill green">正常</span><small>今日核销 92</small></div>
          </div>
        </section>
      </aside>

      <section class="dashboard-card card-pad inventory-card">
        <div class="panel-title-row"><h2>库存预警</h2><span>更多 ›</span></div>
        <div class="inventory-list">
          <div v-for="product in cakeProducts" :key="product.id" class="mini-product">
            <img :src="product.image" :alt="product.name" />
            <span>{{ product.name }}<small>{{ product.sku }}</small></span>
            <b>库存 {{ product.sales % 20 + 6 }}</b>
            <em>预警</em>
          </div>
        </div>
      </section>

      <section class="dashboard-card card-pad product-center-card">
        <div class="panel-title-row"><h2>商品中心</h2><span>更多 ›</span></div>
        <div class="product-center-kpis">
          <div><span>商品总数</span><b>128</b></div>
          <div><span>在售商品</span><b>102</b></div>
          <div><span>已下架</span><b>18</b></div>
          <div><span>新品待上架</span><b>8</b></div>
        </div>
        <h3>热销商品 TOP5</h3>
        <div class="hot-cards">
          <article v-for="product in cakeProducts" :key="product.id">
            <img :src="product.image" :alt="product.name" />
            <b>{{ product.name }}</b>
            <span>月销 {{ product.sales.toLocaleString() }}</span>
            <strong>¥{{ product.price }}</strong>
          </article>
        </div>
      </section>

      <section class="dashboard-card card-pad delivery-card">
        <div class="panel-title-row"><h2>配送调度</h2><span>更多 ›</span></div>
        <div class="mini-kpi-row four">
          <div><span>待调度订单</span><b>326</b></div>
          <div><span>配送中</span><b>278</b></div>
          <div><span>异常配送</span><b class="danger">12</b></div>
          <div><span>今日已送达</span><b>612</b></div>
        </div>
        <div class="prd-map small-map">
          <span v-for="city in prdCities.slice(0, 7)" :key="city.city" class="map-marker" :style="{ left: `${city.x}%`, top: `${city.y}%` }"></span>
        </div>
      </section>

      <section class="dashboard-card service-card card-pad">
        <div class="panel-title-row"><h2>服务健康状态</h2><span>查看全部 ›</span></div>
        <div class="service-grid">
          <div v-for="service in serviceHealth" :key="service[0]">
            <b>{{ service[0] }} <i></i></b>
            <strong>{{ service[1] }}</strong>
            <span>响应时间 {{ service[2] }}</span>
          </div>
        </div>
        <footer>
          <span>系统运行时间 155天 8小时 32分</span>
          <span>最后一次部署 2025-05-24 02:15:33</span>
          <span>当前环境 生产环境</span>
          <span>告警中心：0 紧急 · 2 重要 · 5 次要</span>
        </footer>
      </section>
    </div>
  </section>
</template>

<script setup lang="ts">
import { cakeProducts, paymentRows, platformRows, prdCities, serviceHealth } from '../data/mock';

const flowSteps = [
  { name: '新订单', value: 256, change: '较昨 23' },
  { name: '接单中', value: 128, change: '+18' },
  { name: '制作中', value: 512, change: '+34' },
  { name: '待配送', value: 326, change: '-6' },
  { name: '已完成', value: 612, change: '+52' },
  { name: '售后/退款', value: 28, change: '-9' }
];
</script>

<style scoped>
.middle-dashboard { min-width: 0; }
.search { max-width: 420px; margin-left: auto; }
.middle-grid { display: grid; grid-template-columns: 1.05fr 1.25fr 0.9fr; gap: 12px; }
.orders-card { min-height: 370px; }
.flow-card { min-height: 370px; }
.right-stack { display: grid; gap: 12px; }
.mini-kpi-row { display: grid; grid-template-columns: repeat(4, 1fr); gap: 0; margin-bottom: 18px; border: 1px solid var(--color-line); border-radius: 12px; overflow: hidden; }
.mini-kpi-row > div { padding: 14px; border-right: 1px solid var(--color-line); }
.mini-kpi-row > div:last-child { border-right: 0; }
.mini-kpi-row span { display: block; color: var(--color-stone); font-size: 12px; }
.mini-kpi-row b { display: block; margin: 8px 0; color: var(--color-light-cork); font-size: 26px; }
.mini-kpi-row small { color: #83b36c; }
.mini-kpi-row small.down { color: #8ec07c; }
.flow-steps { display: grid; grid-template-columns: repeat(6, 1fr); gap: 8px; padding: 14px; border: 1px solid var(--color-line); border-radius: 12px; }
.flow-steps div { position: relative; text-align: center; }
.flow-steps div:not(:last-child)::after { position: absolute; right: -8px; top: 28px; color: var(--color-rust); content: "→"; }
.flow-steps span, .flow-steps small { color: var(--color-hazel); font-size: 12px; }
.flow-steps b { display: block; margin: 8px 0; font-size: 25px; }
.flow-lower { display: grid; grid-template-columns: 0.8fr 1.2fr; gap: 12px; margin-top: 12px; }
.flow-lower > div { padding: 14px; border: 1px solid var(--color-line); border-radius: 12px; }
.platform-list { display: grid; grid-template-columns: repeat(2, 1fr); gap: 10px; }
.platform-card, .gateway-grid > div, .coupon-list > div { display: grid; gap: 8px; padding: 12px; background: rgba(255, 237, 215, 0.035); border: 1px solid var(--color-line); border-radius: 10px; }
.platform-card b { font-size: 22px; }
.platform-card small, .gateway-grid small, .coupon-list small { color: var(--color-hazel); }
.gateway-grid { display: grid; grid-template-columns: repeat(2, 1fr); gap: 10px; }
.coupon-list { display: grid; gap: 8px; }
.inventory-card { min-height: 255px; }
.inventory-list { display: grid; gap: 10px; }
.mini-product span { color: var(--color-light-cork); }
.mini-product span small { display: block; color: var(--color-stone); }
.mini-product b { color: var(--color-hazel); font-size: 12px; }
.mini-product em { color: var(--color-rust); font-style: normal; font-weight: 700; }
.product-center-card { min-height: 255px; }
.product-center-kpis { display: grid; grid-template-columns: repeat(4, 1fr); gap: 8px; padding: 10px; border: 1px solid var(--color-line); border-radius: 12px; }
.product-center-kpis span { display: block; color: var(--color-stone); font-size: 12px; }
.product-center-kpis b { display: block; margin-top: 8px; font-size: 20px; }
.hot-cards { display: grid; grid-template-columns: repeat(5, 1fr); gap: 8px; }
.hot-cards article { padding: 8px; border: 1px solid var(--color-line); border-radius: 10px; }
.hot-cards img { width: 100%; height: 70px; object-fit: cover; border-radius: 8px; }
.hot-cards b, .hot-cards span, .hot-cards strong { display: block; margin-top: 4px; font-size: 11px; }
.hot-cards span { color: var(--color-stone); }
.delivery-card { min-height: 255px; }
.mini-kpi-row.four { grid-template-columns: repeat(4, 1fr); }
.danger { color: var(--color-rust) !important; }
.small-map { min-height: 150px; margin-top: 12px; }
.service-card { grid-column: 1 / -1; }
.service-grid { display: grid; grid-template-columns: repeat(8, 1fr); gap: 12px; }
.service-grid div { padding: 14px; background: rgba(255, 237, 215, 0.035); border: 1px solid var(--color-line); border-radius: 10px; }
.service-grid b, .service-grid strong, .service-grid span { display: block; }
.service-grid i { display: inline-block; width: 7px; height: 7px; background: #82b65f; border-radius: 50%; }
.service-grid strong { margin: 8px 0 4px; color: #86cf6d; }
.service-grid span { color: var(--color-stone); font-size: 12px; }
.service-card footer { display: flex; gap: 28px; margin-top: 14px; color: var(--color-stone); font-size: 12px; }
</style>

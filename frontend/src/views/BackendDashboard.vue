<template>
  <section class="backend-dashboard">
    <header class="stage-header">
      <h1>管理驾驶舱</h1>
      <span class="muted">数据更新时间：2025-05-24 10:30:00 ↻</span>
      <div class="stage-actions">
        <span class="status-pill orange">消息 12</span>
        <span class="status-pill">管理员 · 超级管理员</span>
      </div>
    </header>

    <div class="kpi-grid">
      <article v-for="card in kpis" :key="card.title" class="dashboard-card kpi-card">
        <span>{{ card.title }}</span>
        <b>{{ card.value }}</b>
        <small>{{ card.change }}</small>
      </article>
    </div>

    <div class="cockpit-grid">
      <section class="dashboard-card card-pad store-rank">
        <div class="panel-title-row"><h2>门店表现（今日）</h2><span>更多 ›</span></div>
        <table class="table">
          <thead><tr><th>门店</th><th>销售额</th><th>订单量</th></tr></thead>
          <tbody>
            <tr v-for="city in prdCities.slice(0, 5)" :key="city.city">
              <td><b>{{ city.city }}核心店</b></td>
              <td>{{ city.gmv.toLocaleString() }}</td>
              <td>{{ city.orders }}</td>
            </tr>
          </tbody>
        </table>
      </section>

      <section class="dashboard-card card-pad main-map">
        <div class="panel-title-row"><h2>珠三角门店与配送地图</h2><div><span class="status-pill">图层</span><span class="status-pill">全屏</span></div></div>
        <div class="prd-map cockpit-map">
          <div v-for="city in prdCities" :key="city.city" :class="['map-marker', city.orders < 500 ? 'green' : '']" :style="{ left: `${city.x}%`, top: `${city.y}%` }">
            {{ city.stores }}
            <span class="map-label">{{ city.city }}</span>
          </div>
          <div class="map-legend">
            <span>▣ 门店</span>
            <span>▰ 仓库</span>
            <span>▱ 配送中</span>
            <span>▱ 损耗点</span>
          </div>
        </div>
      </section>

      <aside class="right-status">
        <section class="dashboard-card card-pad mini-status">
          <h2>仓库状态</h2>
          <div class="status-boxes"><div><b>5</b><span>正常</span></div><div><b>1</b><span>预警</span></div><div><b>0</b><span>异常</span></div></div>
        </section>
        <section class="dashboard-card card-pad"><h2>货损上报（今日）</h2><b class="big-num">6 单</b><p class="muted">损失金额 2,530 元</p></section>
        <section class="dashboard-card card-pad"><h2>原材料库存预警</h2><b class="big-num">7 种</b><p class="muted">低于安全库存</p></section>
        <section class="dashboard-card card-pad"><h2>采购进货（今日）</h2><b class="big-num">12 单</b><p class="muted">入库金额 58,760 元</p></section>
        <section class="dashboard-card card-pad"><h2>冷链温度（实时）</h2><b class="big-num green-text">3.2°C</b><p class="muted">监控点 36 个 · 正常</p></section>
        <section class="dashboard-card card-pad"><h2>员工在线（门店）</h2><b class="big-num">238/312</b><p class="muted">在线率 76.3%</p></section>
      </aside>

      <section class="dashboard-card card-pad sales-trend">
        <div class="panel-title-row"><h2>月度销售额趋势（元）</h2><span class="status-pill">月 / 年</span></div>
        <div class="bar-row">
          <i v-for="height in [62, 70, 82, 78, 90, 96]" :key="height" class="bar" :style="{ height: `${height}%` }"></i>
          <i v-for="height in [42, 54, 63, 58, 70, 75]" :key="`last-${height}`" class="bar olive" :style="{ height: `${height}%` }"></i>
        </div>
      </section>

      <section class="dashboard-card card-pad hot-ranking">
        <div class="panel-title-row"><h2>畅销蛋糕 TOP5（今日）</h2><span>查看全部 ›</span></div>
        <ol class="rank-list">
          <li v-for="product in cakeProducts" :key="product.id">
            <img :src="product.image" :alt="product.name" />
            <b>{{ product.name }}</b>
            <span>{{ product.sales }}</span>
            <strong>{{ product.price.toLocaleString() }}</strong>
          </li>
        </ol>
      </section>

      <section class="dashboard-card card-pad pay-share">
        <div class="panel-title-row"><h2>支付渠道占比（今日）</h2></div>
        <div class="donut" data-label="支付总额"></div>
        <ul class="legend-list">
          <li>微信支付 54.3%</li><li>支付宝 28.7%</li><li>会员余额 9.6%</li><li>储值卡 4.8%</li><li>其他 2.6%</li>
        </ul>
      </section>

      <section class="dashboard-card card-pad city-rank">
        <div class="panel-title-row"><h2>城市销售额排名（今日）</h2><span>查看全部城市 ›</span></div>
        <div v-for="city in prdCities.slice(0, 5)" :key="city.city" class="city-bar">
          <span>{{ city.city }}</span>
          <i><em :style="{ width: `${Math.min(100, city.gmv / 2800)}%` }"></em></i>
          <b>{{ city.gmv.toLocaleString() }}</b>
        </div>
      </section>

      <section class="dashboard-card card-pad order-source">
        <div class="panel-title-row"><h2>订单来源占比（今日）</h2></div>
        <div class="donut" data-label="8,732"></div>
        <ul class="legend-list"><li>小程序 62.4%</li><li>美团外卖 17.6%</li><li>饿了么 9.8%</li><li>抖音团购 6.3%</li><li>其他 3.9%</li></ul>
      </section>

      <section class="dashboard-card card-pad warehouse-table">
        <div class="panel-title-row"><h2>仓库管理</h2><span>查看全部仓库 ›</span></div>
        <table class="table"><tbody><tr v-for="row in warehouses" :key="row[0]"><td><b>{{ row[0] }}</b></td><td><span class="status-pill green">{{ row[1] }}</span></td><td>{{ row[2] }}</td><td>{{ row[3] }}</td></tr></tbody></table>
      </section>

      <section class="dashboard-card card-pad loss-table">
        <div class="panel-title-row"><h2>货损记录（近期）</h2><span>查看更多货损 ›</span></div>
        <table class="table"><tbody><tr v-for="row in losses" :key="row[0]"><td>{{ row[0] }}</td><td>{{ row[1] }}</td><td><b>{{ row[2] }}</b></td><td class="rust-text">{{ row[3] }}</td></tr></tbody></table>
      </section>

      <section class="dashboard-card card-pad cake-state">
        <div class="panel-title-row"><h2>蛋糕状态（今日）</h2><span>查看全部状态 ›</span></div>
        <div v-for="state in states" :key="state.name" class="state-row"><span>{{ state.name }}</span><b>{{ state.value }}</b><i :style="{ width: state.width }"></i></div>
      </section>

      <section class="dashboard-card card-pad materials-table">
        <div class="panel-title-row"><h2>原材料预警</h2><span>查看全部预警 ›</span></div>
        <table class="table"><tbody><tr v-for="row in materials" :key="row[0]"><td>{{ row[0] }}</td><td>{{ row[1] }}</td><td>{{ row[2] }}</td><td><span :class="['status-pill', row[3] === '正常' ? 'green' : 'orange']">{{ row[3] }}</span></td></tr></tbody></table>
      </section>

      <section class="dashboard-card card-pad purchase-table">
        <div class="panel-title-row"><h2>采购单（待处理）</h2><span>查看全部采购单 ›</span></div>
        <table class="table"><tbody><tr v-for="row in purchases" :key="row[0]"><td>{{ row[0] }}</td><td>{{ row[1] }}</td><td>{{ row[2] }}</td><td><span class="status-pill orange">{{ row[3] }}</span></td></tr></tbody></table>
      </section>

      <section class="dashboard-card card-pad delivery-table">
        <div class="panel-title-row"><h2>配送任务（今日）</h2><span>查看全部任务 ›</span></div>
        <table class="table"><tbody><tr v-for="row in deliveries" :key="row[0]"><td>{{ row[0] }}</td><td>{{ row[1] }}</td><td>{{ row[2] }}</td><td>{{ row[3] }}</td></tr></tbody></table>
      </section>
    </div>
  </section>
</template>

<script setup lang="ts">
import { cakeProducts, prdCities } from '../data/mock';

const kpis = [
  { title: '销售额（今日）', value: '¥ 1,248,560.00', change: '较昨日 +12.6%' },
  { title: '订单量（单）', value: '8,732', change: '较昨日 +9.3%' },
  { title: '客单价（元）', value: '143.10', change: '较昨日 +5.7%' },
  { title: '新增会员（人）', value: '1,268', change: '较昨日 +18.4%' },
  { title: '配送中订单（单）', value: '512', change: '较昨日 +6.8%' },
  { title: '骑手在线', value: '128/186', change: '在线率 68.8%' }
];

const warehouses = [
  ['广州中心仓', '正常', '128/96', '3.1℃'],
  ['深圳冷链仓', '正常', '96/72', '2.8℃'],
  ['佛山仓', '预警', '62/48', '4.2℃'],
  ['东莞仓', '正常', '54/36', '3.5℃'],
  ['珠海仓', '正常', '38/28', '3.0℃']
];

const losses = [
  ['05-24', '深圳南山店', '运输破损', '680.00'],
  ['05-24', '广州天河店', '过期报废', '420.00'],
  ['05-23', '东莞南城店', '包装破损', '350.00'],
  ['05-23', '佛山千灯湖店', '保存不当', '280.00']
];

const states = [
  { name: '待制作', value: '1,286', width: '22%' },
  { name: '制作中', value: '2,153', width: '38%' },
  { name: '待配送', value: '1,042', width: '18%' },
  { name: '配送中', value: '512', width: '9%' },
  { name: '已完成', value: '1,739', width: '13%' }
];

const materials = [
  ['动物奶油', '28.5kg', '50kg', '预警'],
  ['草莓', '15.2kg', '30kg', '预警'],
  ['可可粉', '8.6kg', '20kg', '预警'],
  ['黄油', '12.3kg', '20kg', '正常'],
  ['抹茶粉', '6.1kg', '10kg', '正常']
];

const purchases = [
  ['CG250524001', '新峰鲜乳业', '12,560', '待确认'],
  ['CG250524002', '安琪酵母', '6,380', '待审核'],
  ['CG250524003', '恒禾食品', '8,920', '待确认'],
  ['CG250523004', '展艺烘焙', '5,760', '已到货']
];

const deliveries = [
  ['00:00-06:00', '312', '294', '94.2%'],
  ['06:00-12:00', '1,286', '1,152', '89.6%'],
  ['12:00-18:00', '2,153', '1,986', '92.2%'],
  ['18:00-24:00', '1,739', '1,612', '92.7%']
];
</script>

<style scoped>
.backend-dashboard { min-width: 0; }
.cockpit-grid { display: grid; grid-template-columns: 1.05fr 2.65fr 1.25fr; gap: 12px; margin-top: 12px; }
.store-rank { min-height: 344px; }
.main-map { min-height: 344px; }
.cockpit-map { min-height: 276px; }
.right-status { display: grid; grid-template-columns: repeat(2, minmax(0, 1fr)); gap: 12px; }
.right-status section { min-height: 100px; }
.right-status h2 { margin: 0 0 12px; font-size: 15px; }
.status-boxes { display: grid; grid-template-columns: repeat(3, 1fr); gap: 8px; }
.status-boxes div { padding: 10px; text-align: center; background: rgba(255, 237, 215, 0.05); border-radius: 10px; }
.status-boxes b, .big-num { display: block; color: var(--color-light-cork); font-size: 26px; }
.green-text { color: #93c86e !important; }
.sales-trend, .hot-ranking, .pay-share, .city-rank, .order-source, .warehouse-table, .loss-table, .cake-state, .materials-table, .purchase-table, .delivery-table { min-height: 245px; }
.rank-list { display: grid; gap: 8px; padding: 0; margin: 0; list-style: none; }
.rank-list li { display: grid; grid-template-columns: 24px 44px 1fr 54px 60px; gap: 8px; align-items: center; }
.rank-list li::before { display: grid; width: 22px; height: 22px; place-items: center; color: #fff; content: counter(list-item); background: var(--color-rust); border-radius: 50%; font-size: 11px; }
.rank-list img { width: 44px; height: 34px; object-fit: cover; border-radius: 6px; }
.rank-list span { color: var(--color-hazel); }
.rank-list strong { color: var(--color-light-cork); }
.legend-list { display: grid; gap: 8px; margin: 12px 0 0; padding-left: 16px; color: var(--color-hazel); }
.city-bar { display: grid; grid-template-columns: 70px 1fr 82px; gap: 10px; align-items: center; margin-bottom: 12px; }
.city-bar i { height: 5px; background: rgba(255, 237, 215, 0.08); border-radius: 999px; }
.city-bar em { display: block; height: 100%; background: var(--color-rust); border-radius: 999px; }
.city-bar b { text-align: right; }
.state-row { position: relative; display: grid; grid-template-columns: 86px 1fr; gap: 12px; padding: 9px 12px; margin-bottom: 8px; overflow: hidden; background: rgba(255, 237, 215, 0.045); border-radius: 9px; }
.state-row i { position: absolute; inset: 0 auto 0 0; z-index: 0; background: rgba(220, 80, 0, 0.26); }
.state-row span, .state-row b { position: relative; z-index: 1; }
.rust-text { color: var(--color-rust-2) !important; }
</style>

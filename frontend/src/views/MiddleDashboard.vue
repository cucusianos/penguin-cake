<template>
  <section>
    <div class="oryzo-grid cols-6">
      <div v-for="item in metrics" :key="item.label" class="oryzo-card metric"><span>{{ item.label }}</span><b>{{ item.value }}</b><span :class="item.warn ? 'trend-warn' : 'trend-up'">{{ item.delta }}</span></div>
    </div>

    <div class="oryzo-grid" style="grid-template-columns:1.45fr 1fr;margin-top:18px">
      <div class="oryzo-card card-pad">
        <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:12px"><h2 style="margin:0">珠三角配送全局地图</h2><span class="status-pill good">● 实时</span></div>
        <div class="mock-map" style="min-height:360px">
          <span v-for="pin in pins" :key="pin.city" class="map-pin" :class="pin.green ? 'green' : ''" :style="{left: pin.x+'%', top: pin.y+'%'}">{{ pin.city }} {{ pin.count }}</span>
        </div>
      </div>
      <div class="oryzo-grid">
        <div class="oryzo-card card-pad"><h2>平台聚合（歪买聚合）</h2><div class="oryzo-grid cols-3"><div class="metric mini"><span>京东外卖</span><b>1,842</b><span class="trend-up">在线</span></div><div class="metric mini"><span>淘宝闪购</span><b>1,326</b><span class="trend-up">在线</span></div><div class="metric mini"><span>歪买聚合</span><b>674</b><span class="trend-up">在线</span></div></div></div>
        <div class="oryzo-card card-pad"><h2>支付网关状态</h2><div class="oryzo-grid cols-4"><span class="status-pill good">微信支付 98.73%</span><span class="status-pill good">支付宝 98.54%</span><span class="status-pill good">Stripe 99.21%</span><span class="status-pill good">虚拟币 12 笔</span></div></div>
      </div>
    </div>

    <div class="oryzo-grid" style="grid-template-columns:1fr 1fr;margin-top:18px">
      <div class="oryzo-card card-pad">
        <div style="display:flex;justify-content:space-between;align-items:center"><h2>蛋糕解耦中间件（事件总线）</h2><span class="status-pill good">2,341 events/s</span></div>
        <div class="event-bus">
          <div v-for="node in bus" :key="node.name" class="bus-node"><span>{{ node.icon }}</span><b>{{ node.name }}</b><small>{{ node.desc }}</small></div>
        </div>
        <table class="table-dark"><tbody><tr v-for="e in events" :key="e.time"><td>{{ e.time }}</td><td>{{ e.event }}</td><td>{{ e.id }}</td><td><span class="status-pill good">成功</span></td></tr></tbody></table>
      </div>
      <div class="oryzo-card card-pad">
        <h2>订单状态看板</h2>
        <div class="chip-row"><span v-for="s in status" :key="s" class="status-pill warn">{{ s }}</span></div>
        <table class="table-dark"><thead><tr><th>订单号</th><th>商品</th><th>渠道</th><th>门店</th><th>状态</th><th>金额</th></tr></thead><tbody><tr v-for="o in orders" :key="o.no"><td>{{ o.no }}</td><td><img :src="o.img" class="product-thumb"/> {{ o.name }}</td><td>{{ o.channel }}</td><td>{{ o.store }}</td><td><span class="status-pill warn">{{ o.status }}</span></td><td>¥{{ o.amount }}</td></tr></tbody></table>
      </div>
    </div>
  </section>
</template>
<script setup lang="ts">
const metrics = [
  { label:'待处理订单', value:'1,268 单', delta:'较昨日 +12.8%' }, { label:'支付成功率', value:'98.62%', delta:'较昨日 +0.35%' }, { label:'验券成功率', value:'97.31%', delta:'较昨日 -0.21%', warn:true }, { label:'外卖平台订单', value:'3,842 单', delta:'较昨日 +15.6%' }, { label:'仓储预警', value:'12 项', delta:'较昨日 +2', warn:true }, { label:'配送进行中', value:'1,075 单', delta:'较昨日 +18.7%' }
]
const pins = [{city:'广州',count:33,x:48,y:22},{city:'深圳',count:31,x:68,y:68},{city:'佛山',count:23,x:42,y:36},{city:'东莞',count:21,x:61,y:42},{city:'珠海',count:14,x:55,y:82,green:true},{city:'中山',count:16,x:48,y:66,green:true},{city:'惠州',count:12,x:78,y:34},{city:'江门',count:23,x:35,y:62,green:true}]
const bus = [{icon:'🛍️',name:'订单服务',desc:'创建订单'}, {icon:'📦',name:'库存服务',desc:'库存预占'}, {icon:'💳',name:'支付服务',desc:'支付成功'}, {icon:'🎟️',name:'验券服务',desc:'验券核销'}, {icon:'🚚',name:'配送服务',desc:'派单调度'}]
const events = [{time:'10:21:38', event:'order.created', id:'#ORD-89421'}, {time:'10:21:37', event:'payment.succeeded', id:'#PAY-77123'}, {time:'10:21:36', event:'voucher.redeemed', id:'#VCH-33177'}, {time:'10:21:35', event:'inventory.reserved', id:'#INV-55211'}]
const status = ['全部 1,268','待支付 326','制作中 412','待配送 198','配送中 214','已完成 1,984']
const orders = [
  {no:'#ORD-89421', name:'浓情巧克力 6寸', channel:'淘宝闪购', store:'深圳南山店', status:'待支付', amount:'168.00', img:'/images/cake-realistic-2.png'},
  {no:'#ORD-89420', name:'草莓奶油 6寸', channel:'京东外卖', store:'广州天河店', status:'制作中', amount:'198.00', img:'/images/cake-strawberry.png'},
  {no:'#ORD-89419', name:'提拉米苏 6寸', channel:'歪买聚合', store:'佛山南海店', status:'待配送', amount:'168.00', img:'/images/cake-hazelnut.png'},
  {no:'#ORD-89418', name:'巴斯克芝士 6寸', channel:'淘宝闪购', store:'东莞万江店', status:'配送中', amount:'188.00', img:'/images/cake-hero.png'}
]
</script>
<style scoped>
.mini { padding: 12px; background:rgba(255,237,215,.04); border-radius:12px; border:1px solid var(--color-border); }
.mini b { font-size:24px; }
.event-bus { display:grid; grid-template-columns:repeat(5,1fr); gap:12px; margin:16px 0; }
.bus-node { border:1px solid var(--color-border); border-radius:12px; padding:14px; background:rgba(255,237,215,.04); display:flex; flex-direction:column; gap:8px; text-align:center; }
.bus-node small { color:var(--color-faint-hazel); }
.chip-row { display:flex; gap:10px; flex-wrap:wrap; margin:12px 0; }
@media (max-width: 1200px) { .event-bus { grid-template-columns:1fr 1fr; } }
</style>

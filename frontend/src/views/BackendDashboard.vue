<template>
  <section>
    <div class="oryzo-grid cols-6">
      <div v-for="item in kpis" :key="item.label" class="oryzo-card metric"><span>{{ item.label }}</span><b>{{ item.value }}</b><span :class="item.warn ? 'trend-warn' : 'trend-up'">{{ item.delta }}</span></div>
    </div>
    <div class="oryzo-grid" style="grid-template-columns:1fr 1fr 1fr;margin-top:18px">
      <div class="oryzo-card card-pad"><h2>销售金额趋势</h2><div class="chart-line"><svg viewBox="0 0 600 210"><polyline points="0,160 80,120 160,138 240,88 320,112 400,58 500,98 600,52" fill="none" stroke="#dc5000" stroke-width="4"/><polygon points="0,210 0,160 80,120 160,138 240,88 320,112 400,58 500,98 600,52 600,210" fill="rgba(220,80,0,.22)"/></svg></div></div>
      <div class="oryzo-card card-pad"><h2>热销蛋糕排行</h2><table class="table-dark"><tbody><tr v-for="(p,i) in products" :key="p.name"><td>{{ i+1 }}</td><td><img :src="p.img" class="product-thumb"/> {{ p.name }}</td><td>销量 {{ p.sale }}</td><td>¥{{ p.price }}</td></tr></tbody></table></div>
      <div class="oryzo-card card-pad"><h2>支付方式占比</h2><div class="donut"><span>¥1,256,800<br/><small>成交金额</small></span></div><div class="legend"><span>微信支付 62.3%</span><span>支付宝 21.7%</span><span>会员余额 8.6%</span><span>银行卡 5.1%</span></div></div>
    </div>
    <div class="oryzo-grid" style="grid-template-columns:1.1fr .9fr 1fr;margin-top:18px">
      <div class="oryzo-card card-pad"><h2>珠三角12城门店分布</h2><div class="mock-map" style="min-height:300px"><span v-for="pin in pins" :key="pin.city" class="map-pin" :class="pin.green ? 'green' : ''" :style="{left:pin.x+'%',top:pin.y+'%'}">{{ pin.city }} {{ pin.store }}</span></div></div>
      <div class="oryzo-card card-pad"><h2>蛋糕状态总览</h2><div class="state-list"><div v-for="s in cakeStates" :key="s.name" :class="['state-row', s.bad ? 'bad' : '']"><span>{{ s.name }}</span><b>{{ s.count }}</b></div></div></div>
      <div class="oryzo-card card-pad"><h2>原材料库存预警</h2><table class="table-dark"><thead><tr><th>原材料</th><th>当前库存</th><th>安全库存</th><th>状态</th></tr></thead><tbody><tr v-for="m in materials" :key="m.name"><td>{{ m.name }}</td><td>{{ m.stock }}</td><td>{{ m.safe }}</td><td><span :class="['status-pill', m.low?'warn':'good']">{{ m.low?'偏低':'充足' }}</span></td></tr></tbody></table></div>
    </div>
    <div class="oryzo-grid" style="grid-template-columns:1fr 1fr;margin-top:18px">
      <div class="oryzo-card card-pad"><h2>近期货损记录</h2><table class="table-dark"><tbody><tr v-for="d in damages" :key="d.time"><td>{{ d.time }}</td><td>{{ d.store }}</td><td>{{ d.product }}</td><td class="trend-warn">{{ d.reason }}</td><td>¥{{ d.amount }}</td></tr></tbody></table></div>
      <div class="oryzo-card card-pad"><h2>今日进货记录</h2><table class="table-dark"><tbody><tr v-for="p in purchases" :key="p.no"><td>{{ p.time }}</td><td>{{ p.supplier }}</td><td>{{ p.material }}</td><td>{{ p.qty }}</td><td><span class="status-pill good">已入库</span></td></tr></tbody></table></div>
    </div>
  </section>
</template>
<script setup lang="ts">
const kpis = [{label:'销售额',value:'¥1,256,800',delta:'较昨日 +12.6%'},{label:'销量',value:'8,642',delta:'较昨日 +8.1%'},{label:'门店数',value:'128',delta:'较昨日 +2'},{label:'毛利率',value:'62.4%',delta:'较上月 +1.8%'},{label:'货损率',value:'1.35%',delta:'较上月 -0.25%',warn:true},{label:'配送准时率',value:'97.6%',delta:'较昨日 +1.3%'}]
const products = [{name:'浓情巧克力',sale:1268,price:268,img:'/images/cake-realistic-2.png'},{name:'草莓奶油蛋糕',sale:1025,price:268,img:'/images/cake-strawberry.png'},{name:'提拉米苏',sale:842,price:288,img:'/images/cake-hazelnut.png'},{name:'巴斯克芝士',sale:621,price:238,img:'/images/cake-hero.png'},{name:'抹茶千层蛋糕',sale:532,price:258,img:'/images/cake-realistic-1.png'}]
const pins = [{city:'广州',store:18,x:50,y:24},{city:'深圳',store:24,x:68,y:68},{city:'珠海',store:6,x:50,y:82,green:true},{city:'佛山',store:12,x:40,y:38},{city:'东莞',store:14,x:60,y:42,green:true},{city:'中山',store:6,x:48,y:62},{city:'惠州',store:8,x:78,y:34},{city:'江门',store:4,x:34,y:62,green:true},{city:'肇庆',store:8,x:24,y:28,green:true}]
const cakeStates = [{name:'待制作',count:1256},{name:'冷藏中',count:2350},{name:'待出库',count:1128},{name:'已配送',count:5842},{name:'报损',count:86,bad:true}]
const materials = [{name:'奶油',stock:'1,286 kg',safe:'600',low:false},{name:'面粉',stock:'856 kg',safe:'500',low:false},{name:'可可粉',stock:'256 kg',safe:'300',low:true},{name:'草莓',stock:'128 kg',safe:'200',low:true}]
const damages = [{time:'05-18 09:21',store:'深圳福田店',product:'草莓奶油蛋糕',reason:'破损',amount:536},{time:'05-18 08:44',store:'广州天河店',product:'提拉米苏',reason:'过期',amount:288},{time:'05-17 21:36',store:'东莞南城店',product:'巧克力蛋糕',reason:'破损',amount:268}]
const purchases = [{no:'CG250524001',time:'07:30',supplier:'安佳乳业',material:'奶油',qty:'500kg'},{no:'CG250524002',time:'07:20',supplier:'金龙鱼',material:'面粉',qty:'1000kg'},{no:'CG250524003',time:'07:15',supplier:'可可之家',material:'可可粉',qty:'200kg'}]
</script>
<style scoped>
.donut { width:180px; height:180px; border-radius:50%; margin:16px auto; display:grid; place-items:center; background: conic-gradient(#dc5000 0 62%, #6b8a52 62% 84%, #bbac97 84% 92%, #40372e 92% 100%); position:relative; }
.donut::after { content:''; position:absolute; width:96px; height:96px; border-radius:50%; background:#100904; }
.donut span { position:relative; z-index:1; text-align:center; font-weight:700; }
.donut small { color:var(--color-faint-hazel); }
.legend { display:grid; gap:8px; color:var(--color-faint-hazel); }
.state-list { display:grid; gap:10px; }
.state-row { display:flex; justify-content:space-between; align-items:center; padding:14px 16px; border-radius:12px; background:rgba(93,108,73,.34); }
.state-row.bad { background:rgba(150,48,34,.44); }
.state-row b { font-size:24px; }
</style>

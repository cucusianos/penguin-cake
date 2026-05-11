<template>
  <section>
    <div class="oryzo-eyebrow">Takeout Aggregator</div><h1 class="section-title">外卖平台：歪买</h1><p style="color:#bbac97">歪买是企鹅蛋糕内部外卖聚合层，对接京东外卖与淘宝闪购，统一接单、状态回传、库存锁定与配送协同。</p>
    <div class="oryzo-grid cols-3" style="margin:24px 0">
      <div class="oryzo-card" v-for="p in platforms" :key="p.code"><h3>{{ p.name }}</h3><p style="color:#bbac97">{{ p.desc }}</p><button class="oryzo-button" @click="sync(p.code)">模拟接单</button></div>
    </div>
    <table class="table-dark"><thead><tr><th>平台订单</th><th>来源</th><th>门店</th><th>状态</th><th>回传</th></tr></thead><tbody><tr v-for="row in rows" :key="row.id"><td>{{row.id}}</td><td>{{row.platform}}</td><td>{{row.store}}</td><td><span class="status-pill">{{row.status}}</span></td><td><button class="oryzo-button secondary">推送制作中</button></td></tr></tbody></table>
  </section>
</template>
<script setup lang="ts">
import { ref } from 'vue'
const platforms = [
  { code:'JD_WAIMAI', name:'京东外卖', desc:'到家订单、商品同步、状态回传适配器' },
  { code:'TAOBAO_SHANGOU', name:'淘宝闪购', desc:'即时零售订单、库存、履约状态适配器' },
  { code:'MOCK', name:'歪买 Mock', desc:'本地演示通道，不依赖平台密钥' }
]
const rows = ref([{id:'WM20260511001', platform:'京东外卖', store:'深圳南山店', status:'已接单'}, {id:'WM20260511002', platform:'淘宝闪购', store:'广州天河店', status:'制作中'}])
function sync(code:string){ rows.value.unshift({id:'WM'+Date.now(), platform:code, store:'广州天河店', status:'已接单'}) }
</script>

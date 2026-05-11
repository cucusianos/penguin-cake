<template>
  <section>
    <div style="display:flex;justify-content:space-between;gap:18px;align-items:flex-end;margin-bottom:18px">
      <div><div class="oryzo-eyebrow">AMap Embedded Big Screen</div><h1 class="section-title">珠三角 12 城地图大屏</h1><p style="color:#bbac97">前端内嵌高德地图 JS API；未配置 Key 时自动降级到离线模拟地图层。</p></div>
      <span class="status-pill">实时刷新 · 30s</span>
    </div>
    <div class="oryzo-grid cols-4" style="margin-bottom:18px">
      <div class="oryzo-card metric"><span>今日销售额</span><b>¥126.8w</b><span>同比 +18.4%</span></div>
      <div class="oryzo-card metric"><span>销量</span><b>4,927</b><span>官网/外卖/团购</span></div>
      <div class="oryzo-card metric"><span>配送中</span><b>386</b><span>冷链温控正常 97%</span></div>
      <div class="oryzo-card metric"><span>货损率</span><b>0.72%</b><span>低于阈值 1.2%</span></div>
    </div>
    <AMapStoreDashboard />
  </section>
</template>
<script setup lang="ts">
import AMapStoreDashboard from '../components/AMapStoreDashboard.vue'
</script>

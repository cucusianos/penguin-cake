<template>
  <div class="oryzo-shell sidebar-layout">
    <aside class="sidebar">
      <RouterLink to="/" class="brand-mark" style="margin-bottom:24px"><span class="brand-penguin">🐧</span><span>企鹅蛋糕</span></RouterLink>
      <RouterLink to="/admin/map">高德地图大屏</RouterLink>
      <RouterLink to="/admin/orders">订单状态</RouterLink>
      <RouterLink to="/admin/warehouse">仓库 / 货损 / 蛋糕状态</RouterLink>
      <RouterLink to="/admin/procurement">进货 / 原材料</RouterLink>
      <RouterLink to="/admin/delivery">配送 / 位置上报</RouterLink>
      <RouterLink to="/admin/waimai">外卖平台聚合</RouterLink>
      <RouterLink to="/admin/voucher">验券核销</RouterLink>
      <RouterLink to="/admin/middleware">蛋糕解耦中间件</RouterLink>
      <RouterLink to="/admin/checkout">支付接口</RouterLink>
    </aside>
    <main class="admin-main"><RouterView /></main>
  </div>
</template>

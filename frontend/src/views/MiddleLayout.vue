<template>
  <div class="stage-shell">
    <aside class="stage-sidebar">
      <RouterLink to="/" class="brand-logo">
        <span class="mark">🐧</span>
        <span>企鹅蛋糕<small>PENGUIN CAKE</small></span>
      </RouterLink>
      <RouterLink class="side-link active" to="/middle">⌂ 首页概览</RouterLink>
      <RouterLink class="side-link" to="/middle/orders">▣ 订单中心 <span>⌄</span></RouterLink>
      <RouterLink class="side-link" to="/middle/payment">▤ 支付网关</RouterLink>
      <RouterLink class="side-link" to="/middle/voucher">▧ 验券中心</RouterLink>
      <RouterLink class="side-link" to="/middle/takeout">☍ 外卖聚合</RouterLink>
      <RouterLink class="side-link" to="/middle/middleware">⟲ 仓储联动</RouterLink>
      <RouterLink class="side-link" to="/middle/delivery">▰ 配送调度</RouterLink>
      <RouterLink class="side-link" to="/back">◇ 门店运营</RouterLink>
      <div class="side-footer">
        <RouterLink class="side-link" to="/back">进入后台</RouterLink>
        <RouterLink class="side-link" to="/">返回前台</RouterLink>
        <p>企鹅蛋糕中台<br />V 5.0.0</p>
      </div>
    </aside>
    <main class="stage-main">
      <RouterView />
    </main>
  </div>
</template>

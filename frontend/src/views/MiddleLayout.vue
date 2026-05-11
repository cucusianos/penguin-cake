<template>
  <div class="oryzo-shell stage-layout">
    <aside class="stage-sidebar">
      <RouterLink to="/" class="brand-mark" style="margin-bottom:24px"><span class="brand-penguin">🐧</span><span>企鹅蛋糕中台</span></RouterLink>
      <div class="side-section">运营编排</div>
      <RouterLink to="/middle">🏠 订单中台</RouterLink>
      <RouterLink to="/middle/payment">💳 支付网关</RouterLink>
      <RouterLink to="/middle/voucher">🎟️ 验券中心</RouterLink>
      <RouterLink to="/middle/waimai">🛵 歪买外卖</RouterLink>
      <RouterLink to="/middle/middleware">🔗 蛋糕解耦中间件</RouterLink>
      <RouterLink to="/middle/delivery">🚚 配送调度</RouterLink>
      <div class="side-section">切换</div>
      <RouterLink to="/back">📊 进入后台</RouterLink>
      <RouterLink to="/">🌐 返回前台</RouterLink>
    </aside>
    <main class="stage-main">
      <div class="stage-topbar">
        <input class="input-dark" placeholder="搜索订单 / 门店 / 用户 / 商品  ⌘K" />
        <div style="display:flex;gap:12px;align-items:center"><span class="status-pill warn">🔔 8</span><span class="status-pill">中台管理员</span></div>
      </div>
      <RouterView />
    </main>
  </div>
</template>

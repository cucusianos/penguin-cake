<template>
  <div class="oryzo-shell stage-layout">
    <aside class="stage-sidebar">
      <RouterLink to="/" class="brand-mark" style="margin-bottom:24px"><span class="brand-penguin">🐧</span><span>企鹅蛋糕后台</span></RouterLink>
      <div class="side-section">数据与管理</div>
      <RouterLink to="/back">🔥 仪表盘</RouterLink>
      <RouterLink to="/back/orders">📄 订单管理</RouterLink>
      <RouterLink to="/back/map">🗺️ 地图大屏</RouterLink>
      <RouterLink to="/back/warehouse">🏬 仓库管理</RouterLink>
      <RouterLink to="/back/procurement">🧾 进货管理</RouterLink>
      <RouterLink to="/back/delivery">🚚 配送管理</RouterLink>
      <div class="side-section">切换</div>
      <RouterLink to="/middle">🧩 进入中台</RouterLink>
      <RouterLink to="/">🌐 返回前台</RouterLink>
    </aside>
    <main class="stage-main">
      <div class="stage-topbar">
        <input class="input-dark" placeholder="搜索功能、订单、门店、商品名" />
        <div style="display:flex;gap:12px;align-items:center"><span class="status-pill warn">消息 12</span><span class="status-pill">管理员</span></div>
      </div>
      <RouterView />
    </main>
  </div>
</template>

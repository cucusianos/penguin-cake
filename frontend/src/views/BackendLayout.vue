<template>
  <div class="stage-shell backend-shell">
    <aside class="stage-sidebar">
      <RouterLink to="/" class="brand-logo">
        <span class="mark">🐧</span>
        <span>企鹅蛋糕后台</span>
      </RouterLink>
      <RouterLink class="side-link active" to="/back">◉ 管理驾驶舱</RouterLink>
      <RouterLink class="side-link" to="/back/orders">▤ 订单管理</RouterLink>
      <RouterLink class="side-link" to="/back">▦ 商品管理</RouterLink>
      <RouterLink class="side-link" to="/back">⌂ 门店管理</RouterLink>
      <RouterLink class="side-link" to="/back/warehouse">▣ 库存管理</RouterLink>
      <RouterLink class="side-link" to="/back/warehouse">△ 货损上报</RouterLink>
      <RouterLink class="side-link" to="/back/warehouse">◌ 蛋糕状态</RouterLink>
      <RouterLink class="side-link" to="/back/procurement">▧ 进货管理</RouterLink>
      <RouterLink class="side-link" to="/back/procurement">◫ 原材料</RouterLink>
      <RouterLink class="side-link" to="/back/delivery">▰ 配送管理</RouterLink>
      <RouterLink class="side-link" to="/back">⚙ 权限设置</RouterLink>
      <div class="side-footer">
        <RouterLink class="side-link" to="/middle">进入中台</RouterLink>
        <RouterLink class="side-link" to="/">返回前台</RouterLink>
      </div>
    </aside>
    <main class="stage-main">
      <RouterView />
    </main>
  </div>
</template>

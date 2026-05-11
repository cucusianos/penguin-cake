<template>
  <div class="oryzo-shell login-page">
    <RouterLink to="/" class="brand-mark login-brand"><span class="brand-penguin">🐧</span><span>企鹅蛋糕</span></RouterLink>
    <section class="login-panel oryzo-card">
      <div class="login-visual">
        <p class="oryzo-eyebrow">TRI-STAGE PRODUCT SUITE</p>
        <h1 class="section-title">前台 · 中台 · 后台</h1>
        <p>前台面向消费者展示真实蛋糕与下单体验；中台负责订单、支付、验券、外卖与事件解耦；后台负责报表、仓储、进货、配送与门店管理。</p>
        <div class="entry-grid">
          <RouterLink to="/" class="entry-card"><b>前台官网</b><span>未登录产品展示</span></RouterLink>
          <RouterLink to="/middle" class="entry-card"><b>中台运营</b><span>订单与平台聚合</span></RouterLink>
          <RouterLink to="/back" class="entry-card"><b>后台管理</b><span>报表与仓储配送</span></RouterLink>
        </div>
      </div>
      <div class="login-form">
        <h2>系统登录</h2>
        <p>演示账号：admin / 123456</p>
        <input class="input-dark" value="admin" />
        <input class="input-dark" value="123456" type="password" />
        <RouterLink class="oryzo-button" to="/middle">进入中台</RouterLink>
        <RouterLink class="oryzo-button secondary" to="/back">进入后台</RouterLink>
      </div>
    </section>
  </div>
</template>
<style scoped>
.login-page { min-height:100vh; display:grid; place-items:center; padding:34px; }
.login-brand { position:absolute; left:34px; top:28px; }
.login-panel { width:min(1080px, 100%); min-height:620px; display:grid; grid-template-columns: 1.15fr .85fr; background: radial-gradient(circle at 20% 20%, rgba(220,80,0,.18), transparent 34%), rgba(30,20,13,.76); }
.login-visual { padding:54px; background: linear-gradient(160deg, rgba(56,36,22,.70), rgba(16,9,4,.28)); }
.login-visual p { color:var(--color-faint-hazel); line-height:1.7; }
.entry-grid { display:grid; grid-template-columns:repeat(3,1fr); gap:14px; margin-top:34px; }
.entry-card { border:1px solid var(--color-border); border-radius:12px; padding:16px; background:rgba(255,237,215,.06); }
.entry-card b { display:block; margin-bottom:8px; }
.entry-card span { color:var(--color-faint-hazel); font-size:13px; }
.login-form { padding:54px; display:flex; flex-direction:column; justify-content:center; gap:16px; }
.login-form h2 { margin:0; font-size:34px; }
.login-form p { margin:0 0 10px; color:var(--color-faint-hazel); }
@media (max-width: 860px) { .login-panel { grid-template-columns:1fr; } .entry-grid { grid-template-columns:1fr; } }
</style>

<template>
  <div class="oryzo-shell">
    <header class="oryzo-nav">
      <RouterLink to="/" class="brand-mark"><span class="brand-penguin">🐧</span><span>企鹅蛋糕</span></RouterLink>
      <nav class="nav-links">
        <a class="active" href="#hero">首页</a>
        <a href="#products">蛋糕商城</a>
        <a href="#custom">生日蛋糕</a>
        <a href="#custom">婚礼蛋糕</a>
        <a href="#delivery">门店配送</a>
        <RouterLink to="/order-status">订单状态</RouterLink>
      </nav>
      <div class="oryzo-actions" style="margin:0">
        <RouterLink class="oryzo-button secondary" to="/login">登录</RouterLink>
        <RouterLink class="oryzo-button ghost" to="/checkout">🛒</RouterLink>
      </div>
    </header>

    <main class="oryzo-page">
      <section id="hero" class="home-hero oryzo-card">
        <div class="hero-copy">
          <div class="oryzo-eyebrow">PENGUIN CAKE · REALISTIC PATISSERIE</div>
          <h1 class="oryzo-title">每一刻，<br/>值得用蛋糕庆祝</h1>
          <p class="oryzo-subtitle">甄选全球优质原料，手工匠心制作。摈弃马卡龙浅色风格，采用真实蛋糕摄影、深色软木质感与锈橙色行动焦点，呈现更高级的官网体验。</p>
          <div class="oryzo-actions">
            <RouterLink class="oryzo-button" to="/checkout">选购蛋糕 →</RouterLink>
            <RouterLink class="oryzo-button secondary" to="/order-status">查询订单状态</RouterLink>
          </div>
          <div class="trust-strip">
            <span>🍃 优质原料<br/><small>安心之选</small></span>
            <span>👨‍🍳 匠心手作<br/><small>新鲜现做</small></span>
            <span>🚚 全国门店<br/><small>新鲜配送</small></span>
          </div>
        </div>
        <div class="hero-visual">
          <div class="hero-cake-frame">
            <img src="/images/cake-realistic-1.png" alt="真实巧克力蛋糕" />
            <span class="cake-badge">企鹅蛋糕</span>
          </div>
        </div>
      </section>

      <section id="products" class="oryzo-card card-pad product-section">
        <div class="section-head">
          <div>
            <p class="oryzo-eyebrow">POPULAR SELECTION</p>
            <h2 class="section-title">人气甄选</h2>
          </div>
          <div class="chip-row">
            <button class="chip active">全部蛋糕</button><button class="chip">巧克力</button><button class="chip">芝士</button><button class="chip">水果</button><button class="chip">慕斯</button>
          </div>
          <RouterLink to="/checkout" class="ghost-link">查看全部 →</RouterLink>
        </div>
        <div class="product-row">
          <article v-for="item in products" :key="item.name" class="cake-card">
            <img :src="item.image" :alt="item.name" />
            <div class="cake-card-body">
              <h3>{{ item.name }}</h3>
              <p>{{ item.description }}</p>
              <div class="cake-card-foot"><b>¥{{ item.price }} <small>起</small></b><RouterLink class="cart-btn" to="/checkout">🛒</RouterLink></div>
            </div>
          </article>
        </div>
      </section>

      <section id="delivery" class="oryzo-grid cols-3" style="margin-top:18px">
        <RouterLink class="feature-card oryzo-card" to="/order-status">
          <div><h3>门店配送</h3><p>珠三角核心城市门店直送，新鲜送达每一刻。</p></div><span>→</span>
        </RouterLink>
        <RouterLink class="feature-card oryzo-card" to="/order-status">
          <div><h3>订单状态</h3><p>实时查看订单进度，掌握制作、冷链、配送动态。</p></div><span>→</span>
        </RouterLink>
        <RouterLink class="feature-card oryzo-card" to="/login">
          <div><h3>新品推荐</h3><p>季节水果、黑巧慕斯、巴斯克芝士全新上架。</p></div><span>→</span>
        </RouterLink>
      </section>

      <section id="custom" class="oryzo-grid cols-3" style="margin-top:18px">
        <div class="oryzo-card metric"><span>珠三角门店覆盖</span><b>12 城</b><span>广州 / 深圳 / 珠海 / 佛山 / 东莞 / 中山 / 江门 / 惠州 / 肇庆 / 清远 / 香港 / 澳门</span></div>
        <div class="oryzo-card metric"><span>今日履约订单</span><b>8,642</b><span>官网 + 外卖平台 + 团购验券统一流转</span></div>
        <div class="oryzo-card metric"><span>冷链准时率</span><b>97.6%</b><span>配送温控与地理位置实时上报</span></div>
      </section>
    </main>
  </div>
</template>

<script setup lang="ts">
const products = [
  { name:'浓情巧克力', description:'72% 黑巧慕斯，浓郁丝滑，口感醇厚', price:268, image:'/images/cake-realistic-2.png' },
  { name:'草莓奶油蛋糕', description:'新鲜草莓搭配北海道奶油，入口即融', price:268, image:'/images/cake-strawberry.png' },
  { name:'提拉米苏', description:'经典咖啡风味，绵密顺滑，回味悠长', price:288, image:'/images/cake-hazelnut.png' },
  { name:'巴斯克芝士蛋糕', description:'焦香表面，浓郁芝士，细腻顺滑', price:238, image:'/images/cake-hero.png' }
]
</script>

<style scoped>
.home-hero { position: relative; min-height: 620px; display:grid; grid-template-columns: .92fr 1.08fr; gap: 24px; padding: 60px; overflow:hidden; background: radial-gradient(circle at 72% 36%, rgba(220,80,0,.22), transparent 38%), linear-gradient(120deg, rgba(16,9,4,.94), rgba(56,36,22,.62)); }
.home-hero::after { content:''; position:absolute; inset:0; pointer-events:none; background: linear-gradient(90deg, rgba(16,9,4,.78), transparent 62%); }
.hero-copy { position: relative; z-index: 2; align-self:center; }
.hero-visual { position: relative; z-index: 1; display:grid; place-items:center; }
.hero-cake-frame { position:relative; width:min(620px,100%); aspect-ratio: 1.2/1; display:grid; place-items:center; }
.hero-cake-frame::before { content:''; position:absolute; width:70%; height:20%; bottom:10%; border-radius:50%; background:rgba(0,0,0,.54); filter: blur(22px); }
.hero-cake-frame img { position:relative; width:100%; height:100%; object-fit:contain; filter: drop-shadow(0 36px 60px rgba(0,0,0,.52)); }
.cake-badge { position:absolute; top:38%; left:50%; transform:translate(-50%,-50%); padding:8px 14px; border-radius:999px; border:1px solid rgba(255,237,215,.35); background:rgba(16,9,4,.70); color:var(--color-cork-dust); font-size:13px; }
.trust-strip { display:flex; gap:28px; margin-top:34px; color:var(--color-faint-hazel); font-size:14px; }
.trust-strip small { color:var(--color-aged-stone); }
.section-head { display:grid; grid-template-columns: 1fr auto auto; gap: 18px; align-items:end; margin-bottom: 16px; }
.chip-row { display:flex; flex-wrap:wrap; gap:10px; }
.chip { border:1px solid var(--color-border); border-radius:999px; padding:8px 14px; background:rgba(255,237,215,.06); color:var(--color-faint-hazel); }
.chip.active { background:rgba(93,108,73,.72); color:var(--color-cork-dust); }
.ghost-link { color:var(--color-rust-soft); }
.product-row { display:grid; grid-template-columns: repeat(4,minmax(0,1fr)); gap:16px; }
.cake-card { border:1px solid var(--color-border); border-radius:12px; overflow:hidden; background:rgba(16,9,4,.56); }
.cake-card img { width:100%; height:174px; object-fit:cover; display:block; }
.cake-card-body { padding:14px; }
.cake-card h3 { margin:0 0 8px; font-size:18px; }
.cake-card p { margin:0 0 14px; color:var(--color-faint-hazel); line-height:1.4; min-height:40px; font-size:13px; }
.cake-card-foot { display:flex; align-items:center; justify-content:space-between; }
.cake-card b { font-size:20px; color:var(--color-light-cork); }
.cart-btn { width:42px; height:42px; border-radius:50%; display:grid; place-items:center; background:var(--color-rust-accent); color:#fff; }
.feature-card { min-height: 130px; padding: 22px; display:flex; justify-content:space-between; align-items:center; background:linear-gradient(135deg, rgba(56,36,22,.82), rgba(16,9,4,.78)); }
.feature-card h3 { margin:0 0 8px; font-size:24px; }
.feature-card p { margin:0; color:var(--color-faint-hazel); line-height:1.5; }
.feature-card span { width:48px; height:48px; border-radius:50%; display:grid; place-items:center; background:rgba(255,237,215,.10); border:1px solid var(--color-border); font-size:24px; }
@media (max-width: 980px) { .home-hero { grid-template-columns: 1fr; padding: 32px; } .section-head { grid-template-columns:1fr; } .product-row { grid-template-columns: repeat(2,minmax(0,1fr)); } }
@media (max-width: 640px) { .product-row { grid-template-columns:1fr; } .trust-strip { flex-direction:column; gap:12px; } }
</style>

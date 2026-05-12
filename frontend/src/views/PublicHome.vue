<template>
  <div class="frontstage-shell">
    <header class="front-nav">
      <RouterLink to="/" class="brand-logo">
        <span class="mark">🐧</span>
        <span>
          企鹅蛋糕
          <small>PENGUIN CAKE</small>
        </span>
      </RouterLink>

      <nav>
        <a class="active" href="#hero">首页</a>
        <a href="#products">蛋糕系列</a>
        <a href="#season">定制服务</a>
        <RouterLink to="/back">门店</RouterLink>
        <RouterLink to="/middle">配送</RouterLink>
        <RouterLink to="/order-status">订单状态</RouterLink>
      </nav>

      <div class="nav-tools">
        <RouterLink class="login-pill" to="/login">♡ 登录</RouterLink>
        <RouterLink class="cart-pill" to="/cart">
          🛒
          <span v-if="cart.totalQuantity">{{ cart.totalQuantity }}</span>
        </RouterLink>
      </div>
    </header>

    <main>
      <section id="hero" class="front-hero">
        <aside class="hero-index">
          <button class="active">01</button>
          <button>02</button>
          <button>03</button>
          <button>04</button>
        </aside>

        <div class="hero-copy">
          <h1>用心造就<br />每一口<span>感动</span></h1>
          <p>甄选全球优质食材，融合匠心工艺与美学设计。为每一个值得庆祝的时刻，献上最美味的仪式感。</p>
          <div class="hero-actions">
            <a class="oryzo-button" href="#products">探索蛋糕系列 →</a>
            <RouterLink class="oryzo-button secondary" to="/cart">查看购物车</RouterLink>
          </div>
        </div>

        <div class="hero-media">
          <img src="/images/cake-hero.jpg" alt="真实巧克力蛋糕" />
          <button class="story-button">▷ 观看品牌故事</button>
        </div>
      </section>

      <section id="products" class="product-showcase">
        <div class="showcase-copy">
          <p class="oryzo-eyebrow">人气之选</p>
          <h2>蛋糕系列</h2>
          <p>每一款都是匠心之作</p>
          <a href="#products">查看全部 →</a>
        </div>

        <div class="showcase-list">
          <div class="chip-row">
            <button
              v-for="category in categories"
              :key="category"
              class="chip"
              :class="{ active: selectedCategory === category }"
              type="button"
              @click="selectedCategory = category"
            >
              {{ category }}
            </button>
          </div>

          <div class="cake-grid">
            <article v-for="product in filteredProducts" :key="product.id" class="front-cake-card">
              <img :src="product.image" :alt="product.name" />
              <div>
                <span class="tag-pill">{{ product.tag }}</span>
                <h3>{{ product.name }}</h3>
                <p>{{ product.description }}</p>
                <footer>
                  <b>¥{{ product.price }} <small>起</small></b>
                  <button type="button" @click="addToCart(product)">🛒</button>
                </footer>
              </div>
            </article>
          </div>
        </div>
      </section>

      <section id="season" class="front-feature-grid">
        <RouterLink class="front-feature wide" to="/cart">
          <img src="/images/cake-grape.jpg" alt="时令限定" />
          <div>
            <h3>时令限定</h3>
            <p>甄选当季食材，品尝自然的馈赠</p>
            <span>探索时令蛋糕 →</span>
          </div>
        </RouterLink>

        <RouterLink class="front-feature" to="/back">
          <img src="/images/cake-store.jpg" alt="门店体验" />
          <div>
            <h3>门店体验</h3>
            <p>精致空间，贴心服务，欢迎到店品尝</p>
            <span>查找附近门店 →</span>
          </div>
        </RouterLink>

        <RouterLink class="front-feature" to="/order-status">
          <img src="/images/cake-hero.jpg" alt="配送服务" />
          <div>
            <h3>配送服务</h3>
            <p>专业冷链配送，新鲜安心送达</p>
            <span>了解配送详情 →</span>
          </div>
        </RouterLink>
      </section>
    </main>

    <aside class="floating-tools">
      <RouterLink to="/cart">🎁<span>会员权益</span></RouterLink>
      <RouterLink to="/order-status">☎<span>联系客服</span></RouterLink>
      <a href="#hero">↑<span>回到顶部</span></a>
    </aside>

    <footer class="front-footer">
      <div class="brand-logo">
        <span class="mark">🐧</span>
        <span>企鹅蛋糕<small>PENGUIN CAKE</small></span>
      </div>
      <p>用心造就每一口感动 © 2018-2026 企鹅蛋糕</p>
      <div class="footer-col">
        <b>快速链接</b>
        <RouterLink to="/cart">购物车</RouterLink>
        <RouterLink to="/order-status">订单状态</RouterLink>
        <RouterLink to="/middle">中台运营</RouterLink>
      </div>
      <div class="footer-col">
        <b>帮助中心</b>
        <a>常见问题</a>
        <a>售后政策</a>
        <a>会员权益</a>
      </div>
      <div class="footer-subscribe">
        <b>订阅我们的资讯</b>
        <p>获取新品和优惠信息</p>
        <div><input placeholder="请输入您的邮箱" /><button>→</button></div>
      </div>
    </footer>
  </div>
</template>

<script setup lang="ts">
import { computed, ref } from 'vue';
import { useMessage } from 'naive-ui';
import { categories, cakeProducts, type CakeProduct } from '../data/mock';
import { useCartStore } from '../stores/cart';

const message = useMessage();
const cart = useCartStore();
const selectedCategory = ref('全部蛋糕');

const filteredProducts = computed(() => {
  if (selectedCategory.value === '全部蛋糕') return cakeProducts;
  return cakeProducts.filter((product) => product.category === selectedCategory.value);
});

function addToCart(product: CakeProduct) {
  cart.addItem({
    id: product.id,
    name: product.name,
    category: product.category,
    description: product.description,
    price: product.price,
    image: product.image
  });
  message.success(`${product.name} 已加入购物车`);
}
</script>

<style scoped>
.frontstage-shell {
  min-height: 100vh;
  background:
    radial-gradient(circle at 74% 20%, rgba(220, 80, 0, 0.14), transparent 32%),
    linear-gradient(180deg, rgba(255, 237, 215, 0.03), transparent 34%),
    #0b0806;
}

.front-nav {
  position: sticky;
  top: 0;
  z-index: 10;
  display: grid;
  grid-template-columns: 260px 1fr 220px;
  align-items: center;
  height: 76px;
  padding: 0 88px;
  background: rgba(9, 6, 4, 0.86);
  border-bottom: 1px solid var(--color-line);
  backdrop-filter: blur(16px);
}

.brand-logo small { display: block; margin-top: 2px; color: var(--color-stone); font-size: 10px; letter-spacing: 0.14em; }
.front-nav nav { display: flex; justify-content: center; gap: 54px; color: var(--color-hazel); }
.front-nav nav a { position: relative; padding: 28px 0; }
.front-nav nav .active { color: var(--color-rust-2); }
.front-nav nav .active::after { position: absolute; left: 0; right: 0; bottom: 17px; height: 2px; content: ""; background: var(--color-rust); }
.nav-tools { display: flex; justify-content: flex-end; gap: 14px; }
.login-pill, .cart-pill { display: inline-flex; align-items: center; gap: 8px; height: 42px; padding: 0 18px; border: 1px solid var(--color-line-strong); border-radius: 999px; }
.cart-pill span { color: #fff; background: var(--color-rust); border-radius: 999px; padding: 2px 7px; font-size: 11px; }

.front-hero {
  position: relative;
  display: grid;
  grid-template-columns: 88px minmax(440px, 0.9fr) minmax(620px, 1.1fr);
  min-height: 520px;
  padding: 72px 88px 48px;
  overflow: hidden;
  border-bottom: 1px solid var(--color-line);
}

.hero-index { display: grid; align-content: center; gap: 18px; }
.hero-index button { width: 40px; color: var(--color-stone); background: transparent; border: 0; text-align: left; }
.hero-index button.active { color: var(--color-rust-2); }
.hero-index button.active::after { display: block; width: 14px; height: 2px; margin-top: 8px; content: ""; background: var(--color-light-cork); }
.hero-copy { z-index: 1; align-self: center; }
.hero-copy h1 { margin: 0; font-size: 72px; line-height: 1.08; letter-spacing: -0.05em; }
.hero-copy h1 span { color: var(--color-rust); }
.hero-copy p { width: 540px; margin: 28px 0; color: var(--color-hazel); font-size: 16px; line-height: 1.85; }
.hero-actions { display: flex; gap: 16px; }
.hero-media { position: relative; display: flex; align-items: center; justify-content: center; min-height: 420px; }
.hero-media::before { position: absolute; inset: 8% 2%; content: ""; background: radial-gradient(circle, rgba(220, 80, 0, 0.18), transparent 58%); filter: blur(12px); }
.hero-media img { position: relative; width: min(760px, 100%); height: 420px; object-fit: cover; border-radius: 0 0 40px 40px; filter: contrast(1.08) saturate(1.05); mask-image: linear-gradient(90deg, transparent, #000 12%, #000 90%, transparent); }
.story-button { position: absolute; right: 30px; bottom: 28px; color: var(--color-light-cork); background: transparent; border: 0; }

.product-showcase {
  display: grid;
  grid-template-columns: 210px 1fr;
  gap: 28px;
  padding: 30px 88px;
}
.showcase-copy { padding-top: 18px; }
.showcase-copy h2 { margin: 8px 0; font-size: 34px; }
.showcase-copy p { color: var(--color-hazel); }
.showcase-copy a { display: inline-block; margin-top: 58px; color: var(--color-rust-2); }
.showcase-list { min-width: 0; }
.chip-row { display: flex; gap: 10px; margin-bottom: 16px; }
.chip { color: var(--color-hazel); }
.chip.active { color: var(--color-light-cork); background: rgba(94, 108, 73, 0.42); border-color: rgba(255, 237, 215, 0.18); }
.cake-grid { display: grid; grid-template-columns: repeat(5, minmax(0, 1fr)); gap: 16px; }
.front-cake-card { overflow: hidden; background: var(--color-panel); border: 1px solid var(--color-line); border-radius: var(--radius-card); }
.front-cake-card img { width: 100%; height: 142px; object-fit: cover; }
.front-cake-card > div { padding: 14px; }
.front-cake-card h3 { margin: 10px 0 6px; font-size: 16px; }
.front-cake-card p { height: 42px; margin: 0; overflow: hidden; color: var(--color-hazel); font-size: 12px; line-height: 1.6; }
.front-cake-card footer { display: flex; align-items: center; justify-content: space-between; margin-top: 12px; }
.front-cake-card b { font-size: 20px; color: var(--color-light-cork); }
.front-cake-card button { width: 38px; height: 38px; color: var(--color-light-cork); background: rgba(220, 80, 0, 0.18); border: 1px solid rgba(220, 80, 0, 0.6); border-radius: 50%; }

.front-feature-grid { display: grid; grid-template-columns: 1.1fr 1fr 1fr; gap: 16px; padding: 0 88px 28px; }
.front-feature { position: relative; min-height: 170px; overflow: hidden; border: 1px solid var(--color-line); border-radius: var(--radius-card); background: var(--color-panel); }
.front-feature img { position: absolute; inset: 0; width: 100%; height: 100%; object-fit: cover; opacity: 0.52; }
.front-feature::after { position: absolute; inset: 0; content: ""; background: linear-gradient(90deg, rgba(13, 8, 5, 0.88), rgba(13, 8, 5, 0.26)); }
.front-feature div { position: relative; z-index: 1; width: 56%; padding: 24px; }
.front-feature h3 { margin: 0 0 10px; font-size: 24px; }
.front-feature p { color: var(--color-hazel); line-height: 1.6; }
.front-feature span { color: var(--color-rust-2); }

.floating-tools { position: fixed; right: 28px; bottom: 64px; z-index: 15; display: grid; overflow: hidden; border: 1px solid var(--color-line); border-radius: 16px; background: rgba(26, 18, 12, 0.9); }
.floating-tools a { display: grid; width: 68px; min-height: 72px; place-items: center; padding: 8px; color: var(--color-hazel); border-bottom: 1px solid var(--color-line); font-size: 18px; text-align: center; }
.floating-tools span { font-size: 11px; }

.front-footer { display: grid; grid-template-columns: 1.6fr 1.1fr 1fr 1fr 1.4fr; gap: 28px; padding: 28px 88px; background: rgba(7, 5, 3, 0.88); border-top: 1px solid var(--color-line); }
.front-footer p, .front-footer a { color: var(--color-stone); }
.footer-col { display: grid; gap: 8px; }
.footer-subscribe div { display: flex; align-items: center; width: 100%; height: 42px; margin-top: 12px; border: 1px solid var(--color-line); border-radius: 999px; }
.footer-subscribe input { flex: 1; min-width: 0; padding: 0 16px; color: var(--color-cork); background: transparent; border: 0; outline: none; }
.footer-subscribe button { width: 42px; height: 42px; color: #fff; background: var(--color-rust); border: 0; border-radius: 50%; }
</style>

<template>
  <div class="product-card oryzo-card">
    <img :src="product.image" :alt="product.name" />
    <div class="content">
      <div class="heading-row">
        <h3>{{ product.name }}</h3>
        <span class="price">¥{{ product.price }}</span>
      </div>
      <p>{{ product.description }}</p>
      <div class="tag-row">
        <span v-for="tag in product.tags" :key="tag" class="status-pill">{{ tag }}</span>
      </div>
      <n-button type="primary" block @click="addProduct">加入购物车</n-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { NButton, useMessage } from 'naive-ui'
import { useCartStore } from '../stores/cart'

const props = defineProps<{
  product: {
    id?: string
    image: string
    name: string
    category?: string
    price: number | string
    description: string
    tags: string[]
  }
}>()

const cart = useCartStore()
const message = useMessage()

function addProduct() {
  cart.addItem({
    id: props.product.id || props.product.name,
    name: props.product.name,
    category: props.product.category || props.product.tags[0] || '蛋糕',
    description: props.product.description,
    price: Number(props.product.price),
    image: props.product.image
  })
  message.success(`${props.product.name} 已加入购物车`)
}
</script>

<style scoped>
.product-card {
  overflow: hidden;
  transition: transform 0.25s ease, border-color 0.25s ease;
}

.product-card:hover {
  border-color: rgba(220, 80, 0, 0.56);
  transform: translateY(-5px);
}

img {
  width: 100%;
  height: 220px;
  object-fit: cover;
  background: #100904;
}

.content {
  padding: 20px;
}

.heading-row {
  display: flex;
  gap: 12px;
  align-items: flex-start;
  justify-content: space-between;
}

h3 {
  margin: 0;
  font-size: 20px;
  color: var(--color-cork-dust);
}

.price {
  font-weight: 800;
  color: var(--color-rust-accent);
  white-space: nowrap;
}

p {
  min-height: 48px;
  color: var(--color-faint-hazel);
}

.tag-row {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  margin: 12px 0 16px;
}
</style>

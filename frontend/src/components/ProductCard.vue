<template>
  <div class="soft-card product-card">
    <img :src="product.image" :alt="product.name" />
    <div class="p-5">
      <div class="flex justify-between items-start gap-3"><h3>{{ product.name }}</h3><span class="price">¥{{ product.price }}</span></div>
      <p>{{ product.description }}</p>
      <div class="flex gap-2 flex-wrap my-3"><span v-for="t in product.tags" :key="t" class="tag">{{ t }}</span></div>
      <n-button type="primary" block @click="$router.push('/checkout')">立即订购</n-button>
    </div>
  </div>
</template>
<script setup lang="ts">
import { NButton } from 'naive-ui'
defineProps<{ product:any }>()
</script>
<style scoped>
.product-card{overflow:hidden;transition:.25s}.product-card:hover{transform:translateY(-5px)}img{width:100%;height:220px;object-fit:cover;background:#fff1f5}h3{margin:0;font-size:20px}.price{font-weight:800;color:#e96f91}p{color:#8b746c;min-height:48px}
</style>

<template>
  <div>
    <div ref="mapRef" class="amap-container" v-show="mapReady"></div>
    <div class="map-fallback" v-show="!mapReady">
      <div v-for="city in cities" :key="city.name" class="city-dot" :style="{ left: city.x + '%', top: city.y + '%' }">
        {{ city.name }} · ¥{{ city.sales }}w · {{ city.orders }}单
      </div>
      <div style="position:absolute;left:24px;bottom:22px;color:#887b6d;font-size:13px;max-width:560px;line-height:1.6">
        高德地图 Key 未配置或当前为离线演示模式：这里显示珠三角 12 城模拟位置层。配置 VITE_AMAP_KEY 与 VITE_AMAP_SECURITY_JS_CODE 后将自动加载 AMap JSAPI。
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import AMapLoader from '@amap/amap-jsapi-loader'

type City = { name: string; lng: number; lat: number; x: number; y: number; sales: number; orders: number }
const mapRef = ref<HTMLDivElement | null>(null)
const mapReady = ref(false)
const cities: City[] = [
  { name:'广州', lng:113.2644, lat:23.1291, x:48, y:36, sales:18.6, orders:1286 },
  { name:'深圳', lng:114.0579, lat:22.5431, x:66, y:66, sales:21.8, orders:1520 },
  { name:'珠海', lng:113.5767, lat:22.2707, x:45, y:72, sales:8.9, orders:486 },
  { name:'佛山', lng:113.1214, lat:23.0215, x:38, y:42, sales:9.8, orders:722 },
  { name:'东莞', lng:113.7518, lat:23.0205, x:59, y:48, sales:11.3, orders:801 },
  { name:'中山', lng:113.3926, lat:22.5176, x:45, y:61, sales:7.6, orders:390 },
  { name:'江门', lng:113.0819, lat:22.5787, x:32, y:60, sales:5.1, orders:277 },
  { name:'惠州', lng:114.4126, lat:23.0794, x:74, y:45, sales:6.8, orders:368 },
  { name:'肇庆', lng:112.4651, lat:23.0472, x:22, y:40, sales:4.6, orders:246 },
  { name:'清远', lng:113.0560, lat:23.6818, x:34, y:24, sales:3.9, orders:198 },
  { name:'香港', lng:114.1694, lat:22.3193, x:70, y:76, sales:12.6, orders:615 },
  { name:'澳门', lng:113.5439, lat:22.1987, x:44, y:79, sales:4.2, orders:188 }
]

onMounted(async () => {
  const key = import.meta.env.VITE_AMAP_KEY
  const securityJsCode = import.meta.env.VITE_AMAP_SECURITY_JS_CODE
  if (!key || key.includes('demo') || !mapRef.value) return
  ;(window as any)._AMapSecurityConfig = { securityJsCode }
  try {
    const AMap = await AMapLoader.load({ key, version: '2.0', plugins: ['AMap.Scale', 'AMap.ToolBar'] })
    const map = new AMap.Map(mapRef.value, {
      viewMode: '3D', zoom: 8, center: [113.55, 22.78], pitch: 42,
      mapStyle: 'amap://styles/darkblue'
    })
    map.addControl(new AMap.Scale())
    map.addControl(new AMap.ToolBar())
    cities.forEach(city => {
      const marker = new AMap.Marker({ position: [city.lng, city.lat], title: city.name, content: `<div class="city-dot">${city.name} · ¥${city.sales}w</div>` })
      map.add(marker)
    })
    mapReady.value = true
  } catch (e) {
    console.warn('AMap load failed, fallback is used.', e)
    mapReady.value = false
  }
})
</script>

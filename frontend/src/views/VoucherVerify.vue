<template>
  <section class="oryzo-page">
    <div class="oryzo-eyebrow">Voucher Center</div>
    <h1 class="section-title">门店验券核销</h1>
    <p class="page-desc">支持美团、口碑、抖音券码验券与核销，当前默认走 Mock 通道。</p>

    <div class="oryzo-card card-pad">
      <div class="verify-grid">
        <n-select v-model:value="form.channel" :options="channels" placeholder="选择渠道" />
        <n-input v-model:value="form.code" placeholder="输入券码" />
        <n-input v-model:value="form.storeId" placeholder="门店编码" />
        <div class="action-row">
          <n-button @click="verify">验券</n-button>
          <n-button type="primary" @click="consume">核销</n-button>
        </div>
      </div>

      <pre v-if="result" class="result-box">{{ result }}</pre>
    </div>
  </section>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { NButton, NInput, NSelect, useMessage } from 'naive-ui'
import { consumeVoucher, verifyVoucher, type VoucherPayload } from '../api/modules'

const message = useMessage()
const result = ref<unknown>()

const channels = [
  { label: '美团', value: 'MEITUAN' },
  { label: '口碑', value: 'KOUBEI' },
  { label: '抖音', value: 'DOUYIN' },
  { label: 'Mock', value: 'MOCK' }
]

const form = ref<VoucherPayload>({
  channel: 'MEITUAN',
  code: 'MT88886666',
  storeId: 'GZ-TIANHE',
  operatorId: 'OP001'
})

async function verify() {
  result.value = await verifyVoucher(form.value)
  message.success('验券完成')
}

async function consume() {
  result.value = await consumeVoucher(form.value)
  message.success('核销完成')
}
</script>

<style scoped>
.page-desc {
  color: var(--color-faint-hazel);
  margin-bottom: 18px;
}

.verify-grid {
  display: grid;
  grid-template-columns: 180px 1fr 180px auto;
  gap: 12px;
  align-items: center;
}

.action-row {
  display: flex;
  gap: 10px;
}

.result-box {
  margin-top: 18px;
  white-space: pre-wrap;
  border: 1px solid var(--color-border);
  border-radius: 12px;
  padding: 16px;
  background: rgba(255, 237, 215, 0.04);
  color: var(--color-cork-dust);
}

@media (max-width: 900px) {
  .verify-grid {
    grid-template-columns: 1fr;
  }
}
</style>

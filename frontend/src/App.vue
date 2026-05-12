<template>
  <n-config-provider :theme-overrides="themeOverrides">
    <n-message-provider>
      <router-view />
    </n-message-provider>
  </n-config-provider>
</template>

<script setup lang="ts">
import { NConfigProvider, NMessageProvider } from 'naive-ui';

const themeOverrides = {
  common: {
    primaryColor: '#dc5000',
    primaryColorHover: '#f06416',
    primaryColorPressed: '#9a3600',
    textColorBase: '#ffedd7',
    borderRadius: '12px',
    bodyColor: '#100904',
    cardColor: '#1e140d',
    modalColor: '#1e140d',
    borderColor: 'rgba(255,237,215,.14)'
  },
  Button: {
    borderRadiusMedium: '36px'
  },
  Card: {
    borderRadius: '12px'
  },
  Input: {
    borderRadius: '36px'
  }
};
</script>

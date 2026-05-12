// src/env.d.ts

interface ImportMetaEnv {
  readonly VITE_AMAP_KEY: string;
  readonly VITE_AMAP_SECURITY_JS_CODE: string;
  // 你可以在这里定义其他的环境变量
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}
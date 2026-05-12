import { computed, ref, watch } from 'vue';
import { defineStore } from 'pinia';

export interface CartProduct {
  id: string;
  name: string;
  category: string;
  description: string;
  price: number;
  image: string;
}

export interface CartItem extends CartProduct {
  quantity: number;
}

export interface CustomerInfo {
  name: string;
  phone: string;
  city: string;
  address: string;
  deliveryTime: string;
  note: string;
}

const CART_KEY = 'penguin-cake-cart';
const CUSTOMER_KEY = 'penguin-cake-customer';

const defaultCustomer: CustomerInfo = {
  name: '',
  phone: '',
  city: '深圳',
  address: '',
  deliveryTime: '今天 18:00-20:00',
  note: ''
};

function readJson<T>(key: string, fallback: T): T {
  try {
    const raw = window.localStorage.getItem(key);
    return raw ? (JSON.parse(raw) as T) : fallback;
  } catch {
    return fallback;
  }
}

export const useCartStore = defineStore('cart', () => {
  const items = ref<CartItem[]>(readJson<CartItem[]>(CART_KEY, []));
  const customer = ref<CustomerInfo>(readJson<CustomerInfo>(CUSTOMER_KEY, defaultCustomer));

  const totalQuantity = computed(() => items.value.reduce((sum, item) => sum + item.quantity, 0));
  const totalAmount = computed(() => items.value.reduce((sum, item) => sum + item.price * item.quantity, 0));
  const hasItems = computed(() => items.value.length > 0);
  const hasCustomer = computed(() => {
    const info = customer.value;
    return Boolean(info.name.trim() && info.phone.trim() && info.city.trim() && info.address.trim());
  });

  function addItem(product: CartProduct) {
    const matched = items.value.find((item) => item.id === product.id);
    if (matched) {
      matched.quantity += 1;
      return;
    }
    items.value.push({ ...product, quantity: 1 });
  }

  function increase(productId: string) {
    const matched = items.value.find((item) => item.id === productId);
    if (matched) matched.quantity += 1;
  }

  function decrease(productId: string) {
    const matched = items.value.find((item) => item.id === productId);
    if (!matched) return;
    matched.quantity -= 1;
    if (matched.quantity <= 0) removeItem(productId);
  }

  function removeItem(productId: string) {
    items.value = items.value.filter((item) => item.id !== productId);
  }

  function clearCart() {
    items.value = [];
  }

  function updateCustomer(payload: Partial<CustomerInfo>) {
    customer.value = { ...customer.value, ...payload };
  }

  watch(items, (value) => window.localStorage.setItem(CART_KEY, JSON.stringify(value)), { deep: true });
  watch(customer, (value) => window.localStorage.setItem(CUSTOMER_KEY, JSON.stringify(value)), { deep: true });

  return {
    items,
    customer,
    totalQuantity,
    totalAmount,
    hasItems,
    hasCustomer,
    addItem,
    increase,
    decrease,
    removeItem,
    clearCart,
    updateCustomer
  };
});

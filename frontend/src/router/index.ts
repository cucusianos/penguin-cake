import { createRouter, createWebHistory } from 'vue-router';
import PublicHome from '../views/PublicHome.vue';
import Login from '../views/Login.vue';
import OrderStatus from '../views/OrderStatus.vue';
import Checkout from '../views/Checkout.vue';
import PaymentGateway from '../views/PaymentGateway.vue';
import Cart from '../views/Cart.vue';
import MiddleLayout from '../views/MiddleLayout.vue';
import MiddleDashboard from '../views/MiddleDashboard.vue';
import BackendLayout from '../views/BackendLayout.vue';
import BackendDashboard from '../views/BackendDashboard.vue';
import MapDashboard from '../views/MapDashboard.vue';
import WarehouseManage from '../views/WarehouseManage.vue';
import ProcurementManage from '../views/ProcurementManage.vue';
import DeliveryManage from '../views/DeliveryManage.vue';
import TakeoutManage from '../views/TakeoutManage.vue';
import MiddlewareMonitor from '../views/MiddlewareMonitor.vue';
import VoucherVerify from '../views/VoucherVerify.vue';

export const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', component: PublicHome },
    { path: '/front', redirect: '/' },
    { path: '/login', component: Login },
    { path: '/order-status', component: OrderStatus },
    { path: '/cart', component: Cart },
    { path: '/checkout', component: Checkout },
    {
      path: '/middle',
      component: MiddleLayout,
      children: [
        { path: '', component: MiddleDashboard },
        { path: 'orders', component: OrderStatus },
        { path: 'takeout', component: TakeoutManage },
        { path: 'voucher', component: VoucherVerify },
        { path: 'payment', component: PaymentGateway },
        { path: 'middleware', component: MiddlewareMonitor },
        { path: 'delivery', component: DeliveryManage }
      ]
    },
    {
      path: '/back',
      component: BackendLayout,
      children: [
        { path: '', component: BackendDashboard },
        { path: 'map', component: MapDashboard },
        { path: 'warehouse', component: WarehouseManage },
        { path: 'procurement', component: ProcurementManage },
        { path: 'delivery', component: DeliveryManage },
        { path: 'orders', component: OrderStatus }
      ]
    },
    { path: '/admin', redirect: '/back' },
    { path: '/admin/:pathMatch(.*)*', redirect: '/back' }
  ]
});

export default router;

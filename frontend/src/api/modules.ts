import { http } from './http';

export interface ApiResponse<T = unknown> {
  code: number;
  message: string;
  data: T;
}

export interface PaymentCreatePayload {
  orderNo: string;
  provider: string;
  amount: number;
  subject: string;
}

export interface VoucherPayload {
  channel: string;
  code: string;
  storeId: string;
  operatorId: string;
}

export interface ExternalOrderPayload {
  platform: string;
  platformOrderNo: string;
  storeId: string;
  orderAmount: number;
  receiverName: string;
}

export interface ExternalOrderStatusPayload {
  platform: string;
  platformOrderNo: string;
  status: string;
}

export interface LocationReportPayload {
  deliveryNo: string;
  riderId: string;
  lng: number;
  lat: number;
  temperature: number;
  status: string;
}

const request = <T>(promise: Promise<unknown>) => promise as Promise<ApiResponse<T>>;

export const products = () => request(http.get('/products'));
export const login = (data: { username: string; password: string }) => request(http.post('/auth/login', data));
export const reportOverview = () => request(http.get('/reports/overview'));
export const pearlRiverStores = () => request(http.get('/reports/pearl-river-delta'));
export const orderTimeline = (orderNo: string) => request(http.get(`/orders/${orderNo}/timeline`));
export const createPayment = (data: PaymentCreatePayload) => request(http.post('/payments/create', data));
export const verifyVoucher = (data: VoucherPayload) => request(http.post('/vouchers/verify', data));
export const consumeVoucher = (data: VoucherPayload) => request(http.post('/vouchers/consume', data));
export const externalPlatforms = () => request(http.get('/takeout/platforms'));
export const externalOrderSync = (data: ExternalOrderPayload) => request(http.post('/takeout/orders/sync', data));
export const externalOrderStatus = (data: ExternalOrderStatusPayload) => request(http.post('/takeout/orders/status', data));
export const middlewareEvents = () => request(http.get('/cake-middleware/events'));
export const warehouseSummary = () => request(http.get('/warehouse/summary'));
export const warehouseCakeStatus = () => request(http.get('/warehouse/cake-status'));
export const reportDamage = (data: Record<string, unknown>) => request(http.post('/warehouse/damage-reports', data));
export const procurementMaterials = () => request(http.get('/procurement/materials'));
export const procurementOrders = () => request(http.get('/procurement/purchase-orders'));
export const deliveryTasks = () => request(http.get('/delivery/tasks'));
export const reportLocation = (data: LocationReportPayload) => request(http.post('/geo/location-reports', data));

export const api = {
  products,
  login,
  reportOverview,
  pearlRiverStores,
  orderTimeline,
  createPayment,
  verifyVoucher,
  consumeVoucher,
  externalPlatforms,
  externalOrderSync,
  externalOrderStatus,
  middlewareEvents,
  warehouseSummary,
  warehouseCakeStatus,
  reportDamage,
  procurementMaterials,
  procurementOrders,
  deliveryTasks,
  reportLocation
};

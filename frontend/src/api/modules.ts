import { http } from './http'

export const api = {
  products: () => http.get('/products'),
  login: (data: any) => http.post('/auth/login', data),
  reportOverview: () => http.get('/reports/overview'),
  pearlRiverStores: () => http.get('/reports/pearl-river-delta'),
  orderTimeline: (orderNo: string) => http.get(`/orders/${orderNo}/timeline`),
  createPayment: (data: any) => http.post('/payments/create', data),
  verifyVoucher: (data: any) => http.post('/vouchers/verify', data),
  consumeVoucher: (data: any) => http.post('/vouchers/consume', data),
  waimaiPlatforms: () => http.get('/waimai/platforms'),
  waimaiSync: (data: any) => http.post('/waimai/orders/sync', data),
  waimaiStatus: (data: any) => http.post('/waimai/orders/status', data),
  middlewareEvents: () => http.get('/cake-middleware/events'),
  warehouseOverview: () => http.get('/warehouse/overview'),
  reportDamage: (data: any) => http.post('/warehouse/damage-reports', data),
  procurementOverview: () => http.get('/procurement/overview'),
  deliveryOverview: () => http.get('/delivery/overview'),
  reportLocation: (data: any) => http.post('/geo/location-reports', data)
}

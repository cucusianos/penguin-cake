export interface CakeProduct {
  id: string;
  name: string;
  category: string;
  description: string;
  price: number;
  image: string;
  tag: string;
  sales: number;
  sku: string;
}

export interface CityStat {
  city: string;
  stores: number;
  orders: number;
  gmv: number;
  x: number;
  y: number;
}

export const cakeProducts: CakeProduct[] = [
  {
    id: 'cake-chocolate',
    name: '浓情巧克力蛋糕',
    category: '巧克力',
    description: '72% 黑巧克力慕斯，浓郁丝滑，口感醇厚',
    price: 268,
    image: '/images/cake-chocolate.jpg',
    tag: '热销',
    sales: 1268,
    sku: 'SKU8801'
  },
  {
    id: 'cake-strawberry',
    name: '草莓奶油蛋糕',
    category: '水果',
    description: '新鲜草莓搭配北海道奶油，清甜轻盈',
    price: 268,
    image: '/images/cake-strawberry.jpg',
    tag: '经典',
    sales: 1025,
    sku: 'SKU3305'
  },
  {
    id: 'cake-tiramisu',
    name: '提拉米苏',
    category: '慕斯',
    description: '经典咖啡风味，绵密顺滑，回味悠长',
    price: 288,
    image: '/images/cake-tiramisu.jpg',
    tag: '推荐',
    sales: 842,
    sku: 'SKU7702'
  },
  {
    id: 'cake-basque',
    name: '巴斯克芝士蛋糕',
    category: '芝士',
    description: '焦香表面，浓郁芝士，绵密顺滑',
    price: 238,
    image: '/images/cake-basque.jpg',
    tag: '新品',
    sales: 621,
    sku: 'SKU5501'
  },
  {
    id: 'cake-grape',
    name: '青提千层蛋糕',
    category: '水果',
    description: '青提果香与清爽奶油叠层，适合庆祝分享',
    price: 258,
    image: '/images/cake-grape.jpg',
    tag: '时令',
    sales: 532,
    sku: 'SKU6613'
  }
];

export const categories = ['全部蛋糕', '巧克力', '芝士', '水果', '慕斯'];

export const prdCities: CityStat[] = [
  { city: '广州', stores: 18, orders: 3285, gmv: 238760, x: 48, y: 31 },
  { city: '深圳', stores: 24, orders: 2487, gmv: 268560, x: 62, y: 58 },
  { city: '佛山', stores: 12, orders: 1356, gmv: 168430, x: 37, y: 47 },
  { city: '东莞', stores: 14, orders: 1102, gmv: 128950, x: 56, y: 44 },
  { city: '珠海', stores: 6, orders: 298, gmv: 96850, x: 42, y: 73 },
  { city: '中山', stores: 6, orders: 356, gmv: 108200, x: 47, y: 64 },
  { city: '江门', stores: 4, orders: 478, gmv: 82560, x: 31, y: 58 },
  { city: '惠州', stores: 8, orders: 568, gmv: 126200, x: 72, y: 34 },
  { city: '肇庆', stores: 9, orders: 178, gmv: 76800, x: 24, y: 29 },
  { city: '清远', stores: 5, orders: 332, gmv: 69200, x: 42, y: 18 },
  { city: '香港', stores: 3, orders: 188, gmv: 58900, x: 70, y: 73 },
  { city: '澳门', stores: 2, orders: 96, gmv: 32600, x: 40, y: 84 }
];

export const orderRows = [
  ['#ORD-20250518-89421', '浓情巧克力蛋糕 6寸', '淘宝闪购', '深圳南山店', '待支付', '¥168.00', '10:21'],
  ['#ORD-20250518-89420', '草莓奶油蛋糕 6寸', '京东外卖', '广州天河店', '制作中', '¥198.00', '10:20'],
  ['#ORD-20250518-89419', '提拉米苏 6寸', '外卖聚合', '佛山南海店', '待配送', '¥168.00', '10:19'],
  ['#ORD-20250518-89418', '巴斯克芝士蛋糕 6寸', '淘宝闪购', '东莞万江店', '配送中', '¥188.00', '10:18'],
  ['#ORD-20250518-89417', '抹茶生巧蛋糕 6寸', '京东外卖', '珠海香洲店', '已完成', '¥168.00', '10:17']
];

export const platformRows = [
  { name: '京东外卖', orders: '1,842 单', status: '已连接', amount: '48,762' },
  { name: '淘宝闪购', orders: '1,326 单', status: '已连接', amount: '27,635' },
  { name: '外卖聚合', orders: '674 单', status: '在线', amount: '16,420' }
];

export const paymentRows = [
  { name: '微信支付', status: '正常', rate: '99.8%' },
  { name: '支付宝', status: '正常', rate: '99.6%' },
  { name: 'Stripe', status: '正常', rate: '99.3%' },
  { name: '虚拟币支付', status: '维护中', rate: '--' }
];

export const serviceHealth = [
  ['订单服务', '正常', '120ms'],
  ['商品服务', '正常', '98ms'],
  ['库存服务', '正常', '110ms'],
  ['支付服务', '正常', '150ms'],
  ['配送服务', '正常', '135ms'],
  ['营销服务', '正常', '88ms'],
  ['会员服务', '正常', '102ms'],
  ['数据服务', '正常', '95ms']
];

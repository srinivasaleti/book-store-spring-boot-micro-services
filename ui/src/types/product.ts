export interface Product {
  id: number;
  code: string;
  name: string;
  description: string;
  imageUrl: string;
  price: number;
}

export interface ListProductsResponse {
  data: Product[];
  currentPage: number;
  totalElements: number;
  totalPages: number;
  hasNext: boolean;
  hasPrev: boolean;
  isFirst: boolean;
  isLast: boolean;
}

export interface OrderItem {
  code: string;
  name: string;
  quantity: number;
  price: number;
}

export interface OrderRequest {
  customer: {
    username: string;
    name: string;
    email: string;
    phone: string;
  };
  deliveryAddress: {
    line1: string;
    line2: string;
    city: string;
    state: string;
    zipCode: string;
    country: string;
  };
  orderItems: OrderItem[];
  comments?: string;
}

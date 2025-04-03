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

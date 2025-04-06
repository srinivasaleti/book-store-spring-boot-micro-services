import { useState } from 'react';
import { ListProductsResponse } from '../types/product';
import { Config } from '../config';

export const useProducts = () => {
  const [productsData, setProductsData] = useState<ListProductsResponse | null>(
    null,
  );
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [page, setPage] = useState(1);

  const fetchProducts = async (pageNumber: number = page) => {
    setLoading(true);
    setError(null);

    try {
      const response = await fetch(
        `${Config.catalogURL}/products?page=${pageNumber}`,
      );
      if (!response.ok) {
        throw new Error('Failed to fetch products');
      }
      const data: ListProductsResponse = await response.json();
      setProductsData(data);
      setPage(pageNumber);
    } catch (err) {
      setError((err as Error).message);
    } finally {
      setLoading(false);
    }
  };

  return { productsData, loading, error, page, fetchProducts };
};

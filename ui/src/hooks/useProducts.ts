import { useState, useEffect } from "react";
import { ListProductsResponse } from "../types/product";
import { Config } from "../config";

export const useProducts = () => {
  const [productsData, setProductsData] = useState<ListProductsResponse | null>(
    null
  );
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch(`${Config.catalogURL}/products`);
        if (!response.ok) {
          setError("Failed to fetch products");
          return;
        }
        const data: ListProductsResponse = await response.json();
        setProductsData(data);
      } catch (err) {
        setError((err as Error).message);
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, []);

  return { productsData, loading, error };
};

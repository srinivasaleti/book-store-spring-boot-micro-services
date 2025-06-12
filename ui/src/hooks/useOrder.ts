import { useState } from 'react';
import { Config } from '../config';
import { OrderRequest } from '../types/product';

export const usePlaceOrder = () => {
  const [placing, setPlacing] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState(false);

  const placeOrder = async (order: OrderRequest) => {
    setPlacing(true);
    setError(null);
    setSuccess(false);

    try {
      const response = await fetch(`${Config.orderUrl}/orders`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(order),
      });

      if (!response.ok) {
        throw new Error('Failed to place order');
      }

      setSuccess(true);
    } catch (err) {
      setError((err as Error).message);
    } finally {
      setPlacing(false);
    }
  };

  return { placeOrder, placing, error, success };
};

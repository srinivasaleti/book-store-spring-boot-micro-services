import { Box, Button, Flex, Text } from '@radix-ui/themes';
import styled from 'styled-components';
import { useCart } from '../context/cart-context';
import { EmptyCart } from '../components/empty-cart';
import { CartItem } from '../components/cart-item';
import { usePlaceOrder } from '../hooks/useOrder';

export const Cart = () => {
  const { cartItems, totalAmount, resetCart } = useCart();
  const { placeOrder, placing, error, success } = usePlaceOrder();

  const handlePlaceOrder = async () => {
    const orderPayload = {
      customer: {
        username: 'johndoe',
        name: 'John Doe',
        email: 'john@example.com',
        phone: '1234567890',
      },
      deliveryAddress: {
        line1: '123 Main St',
        line2: 'Apt 4B',
        city: 'New York',
        state: 'NY',
        zipCode: '10001',
        country: 'IN',
      },
      orderItems: cartItems.map((item) => ({
        code: item.product.code,
        name: item.product.name,
        quantity: item.quantity,
        price: item.product.price,
      })),
      comments: 'Leave at the doorstep',
    };

    await placeOrder(orderPayload);
    resetCart();
    alert('Order Successfully placed');
  };

  if (cartItems.length === 0) {
    return <EmptyCart />;
  }

  return (
    <CartContainer>
      <Flex direction="column" gap="4">
        {cartItems.map((item) => (
          <CartItem key={item.product.id} item={item} />
        ))}
      </Flex>

      <Summary>
        <Text size="4" weight="bold">
          Total: ₹{totalAmount.toFixed(2)}
        </Text>

        <ButtonGroup>
          <ClearAllButton onClick={resetCart}>Clear</ClearAllButton>
          <PlaceOrderButton onClick={handlePlaceOrder} disabled={placing}>
            {placing ? 'Placing...' : 'Place Order'}
          </PlaceOrderButton>
        </ButtonGroup>
      </Summary>

      {success && (
        <Text color="green" weight="bold">
          ✅ Order placed successfully!
        </Text>
      )}

      {error && (
        <Text color="red" weight="bold">
          ❌ Failed to place order: {error}
        </Text>
      )}
    </CartContainer>
  );
};

/* --- Styled Components --- */

const CartContainer = styled(Box)`
  margin: 0 auto;
  padding: 2rem;
  min-height: calc(100vh - 64px);
`;

const Summary = styled(Flex)`
  justify-content: space-between;
  align-items: center;
  margin-top: 2rem;
  flex-wrap: wrap;
  gap: 1rem;
`;

const ButtonGroup = styled(Flex)`
  gap: 1rem;
  flex-wrap: wrap;
`;

const ClearAllButton = styled(Button)`
  cursor: pointer;
  background-color: #ef4444;
  color: white;
  font-weight: bold;

  &:hover {
    background-color: #dc2626;
  }
`;

const PlaceOrderButton = styled(Button)`
  cursor: pointer;
  background-color: #10b981;
  color: white;
  font-weight: bold;

  &:hover {
    background-color: #059669;
  }

  &:disabled {
    background-color: #a7f3d0;
    cursor: not-allowed;
  }
`;

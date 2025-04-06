import { Box, Button, Flex, Text } from '@radix-ui/themes';
import styled from 'styled-components';
import { useCart } from '../context/cart-context';
import { EmptyCart } from '../components/empty-cart';
import { CartItem } from '../components/cart-item';

export const Cart = () => {
  const { cartItems, totalAmount, resetCart } = useCart();

  const handlePlaceOrder = () => {
    alert('Order placed successfully!');
    resetCart();
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
          <PlaceOrderButton onClick={handlePlaceOrder}>
            Place Order
          </PlaceOrderButton>
        </ButtonGroup>
      </Summary>
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
`;

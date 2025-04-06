import { Flex, Text, Box } from '@radix-ui/themes';
import styled from 'styled-components';
import { ShoppingCart } from 'lucide-react';
import { useCart } from '../context/cart-context';

export const AppBar = () => {
  const { totalItems } = useCart();
  return (
    <StyledHeader>
      <Flex justify="between" align="center" p="3">
        <Text size="5" weight="bold" color="blue">
          BookStore
        </Text>

        <CartWrapper>
          <ShoppingCart size={24} />
          {totalItems > 0 && <Badge>{totalItems}</Badge>}
        </CartWrapper>
      </Flex>
    </StyledHeader>
  );
};

/* --- Styled Components --- */
const StyledHeader = styled(Box)`
  background-color: #f9fafb;
  border-bottom: 1px solid #e5e7eb;
  position: sticky;
  top: 0;
  z-index: 10;
`;

const CartWrapper = styled.button`
  position: relative;
  background: none;
  border: none;
  cursor: pointer;
  padding: 6px;
  border-radius: 8px;
  transition: background 0.2s ease-in-out;

  &:hover {
    background-color: #e5e7eb;
  }

  &:focus {
    outline: 2px solid #60a5fa;
    outline-offset: 2px;
  }
`;

const Badge = styled.span`
  position: absolute;
  top: 0;
  right: 0;
  transform: translate(50%, -50%);
  background-color: #ef4444;
  color: white;
  font-size: 10px;
  font-weight: bold;
  width: 18px;
  height: 18px;
  border-radius: 9999px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

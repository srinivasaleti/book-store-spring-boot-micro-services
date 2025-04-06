import { Flex, Text } from '@radix-ui/themes';
import styled from 'styled-components';
import { useCart } from '../context/cart-context';
import { CartItem as CartItemType } from '../context/cart-context';

type Props = {
  item: CartItemType;
};

export const CartItem = ({ item }: Props) => {
  const { addToCart, removeFromCart } = useCart();

  return (
    <CartItemContainer>
      <Content>
        <ProductImage src={item.product.imageUrl} alt={item.product.name} />

        <Details>
          <ItemInfo>
            <Text weight="bold" size="4">
              {item.product.name}
            </Text>
            <Text size="2" color="gray">
              ₹{item.product.price.toFixed(2)}
            </Text>
          </ItemInfo>

          <BottomRow>
            <QuantityControl>
              <QuantityButton onClick={() => removeFromCart(item.product.id)}>
                -
              </QuantityButton>
              <Text>{item.quantity}</Text>
              <QuantityButton onClick={() => addToCart(item.product)}>
                +
              </QuantityButton>
            </QuantityControl>

            <Text weight="bold">
              ₹{(item.quantity * item.product.price).toFixed(2)}
            </Text>
          </BottomRow>
        </Details>
      </Content>
    </CartItemContainer>
  );
};

/* --- Styled Components --- */

const CartItemContainer = styled.div`
  border: 1px solid #e2e8f0;
  padding: 1rem;
  border-radius: 8px;
  background-color: white;
  margin-bottom: 1rem;
`;

const ProductImage = styled.img`
  width: 64px;
  height: 64px;
  border-radius: 6px;
  object-fit: cover;
  flex-shrink: 0;

  @media (max-width: 600px) {
    align-self: center;
  }
`;

const QuantityControl = styled(Flex)`
  align-items: center;
  gap: 0.5rem;
`;

const QuantityButton = styled.button`
  padding: 4px 12px;
  font-size: 16px;
  font-weight: bold;
  border: 1px solid #cbd5e1;
  border-radius: 6px;
  background: #f1f5f9;
  cursor: pointer;

  &:hover {
    background: #e2e8f0;
  }
`;

const Content = styled.div`
  display: flex;
  align-items: flex-start;
  gap: 1rem;

  @media (max-width: 600px) {
    flex-direction: column;
    align-items: center;
    text-align: center;
  }
`;

const Details = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;

  @media (max-width: 600px) {
    align-items: center;
    width: 100%;
  }
`;

const ItemInfo = styled.div`
  margin-bottom: 0.75rem;
  display: flex;
  gap: 8px;
  align-items: center;

  @media (max-width: 600px) {
    flex-direction: column;
    gap: 0.25rem;
  }
`;

const BottomRow = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;

  @media (max-width: 600px) {
    width: 100%;
    justify-content: space-around;
    gap: 1rem;
  }
`;

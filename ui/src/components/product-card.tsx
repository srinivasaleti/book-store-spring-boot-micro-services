import { Card, Flex, Text, Heading, Box } from "@radix-ui/themes";
import styled from "styled-components";
import { Product } from "../types/product";
import { useCart } from "../context/cart-context";

interface Props {
  product: Product;
}

export const ProductCard: React.FC<Props> = ({ product }) => {
  const { cartItems, addToCart, removeFromCart } = useCart();
  const quantity = cartItems.find(item => item.product.id === product.id)?.quantity || 0;

  return (
    <Container>
      <StyledCard>
        <ImageContainer>
          <StyledImage src={product.imageUrl} alt={product.name} />
        </ImageContainer>

        <Content>
          <StyledHeading weight="bold">{product.name}</StyledHeading>
          <Description size="2" color="gray">
            {product.description}
          </Description>

          <PriceAndCart>
            <StyledPrice size="5" weight="bold">
              ${product.price.toFixed(2)}
            </StyledPrice>

            {quantity === 0 ? (
              <AddButton onClick={() => addToCart(product)}>Add to Cart</AddButton>
            ) : (
              <QuantityControls>
                <CartButton onClick={() => removeFromCart(product.id)}>-</CartButton>
                <Quantity>{quantity}</Quantity>
                <CartButton onClick={() => addToCart(product)}>+</CartButton>
              </QuantityControls>
            )}
          </PriceAndCart>
        </Content>
      </StyledCard>
    </Container>
  );
};

/* --- Styled Components --- */
const Container = styled(Box)`
  padding: 16px;
`;

const StyledCard = styled(Card)`
  width: 280px;
  border: 1px solid #d1d5db;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  overflow: hidden;
  transition: box-shadow 0.2s ease-in-out;

  &:hover {
    box-shadow: 0 6px 10px rgba(0, 0, 0, 0.15);
  }
`;

const ImageContainer = styled(Box)`
  height: 350px;
  border-bottom: 1px solid #e5e7eb;
`;

const StyledImage = styled.img`
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s;

  &:hover {
    transform: scale(1.05);
  }
`;

const Content = styled(Flex)`
  height: 150px;
  padding: 12px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const StyledHeading = styled(Heading)`
  font-size: 1rem;
`;

const Description = styled(Text)`
  flex-grow: 1;
`;

const StyledPrice = styled(Text)`
  color: #1d4ed8;
  font-weight: bold;
`;

const PriceAndCart = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 12px;
`;

const AddButton = styled.button`
  background-color: #2563eb;
  color: white;
  border: none;
  padding: 6px 10px;
  font-size: 14px;
  border-radius: 6px;
  cursor: pointer;

  &:hover {
    background-color: #1d4ed8;
  }
`;

const QuantityControls = styled.div`
  display: flex;
  align-items: center;
  gap: 6px;
`;

const CartButton = styled.button`
  background-color: #e5e7eb;
  border: none;
  padding: 4px 10px;
  font-size: 16px;
  border-radius: 4px;
  cursor: pointer;

  &:hover {
    background-color: #d1d5db;
  }
`;

const Quantity = styled.span`
  font-size: 16px;
  font-weight: bold;
`;

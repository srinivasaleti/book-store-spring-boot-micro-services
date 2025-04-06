import { Box, Flex, Text, Button } from '@radix-ui/themes';
import styled from 'styled-components';
import { ShoppingCart } from 'lucide-react';
import { useNavigate } from 'react-router-dom';
import { CATALOG_ROUTE } from '../App';

export const EmptyCart = () => {
  const navigate = useNavigate();
  return (
    <StyledContainer>
      <Flex direction="column" align="center" justify="center" gap="3">
        <ShoppingCart size={48} color="#94a3b8" />
        <Text size="5" weight="bold" color="gray">
          Your cart is empty
        </Text>
        <Text size="3" color="gray">
          Looks like you haven’t added anything yet.
        </Text>
        <StyledButton onClick={() => navigate(CATALOG_ROUTE)}>
          Add Items
        </StyledButton>
      </Flex>
    </StyledContainer>
  );
};

/* --- Styled Components --- */
const StyledContainer = styled(Box)`
  display: flex;
  height: calc(100vh - 64px); // subtracting AppBar height
  justify-content: center;
  align-items: center;
  background-color: #f8fafc;
  padding: 2rem;
`;

const StyledButton = styled(Button)`
  cursor: pointer;

  &:hover {
    opacity: 0.9;
  }
`;

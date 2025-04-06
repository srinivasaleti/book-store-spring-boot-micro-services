// components/AppBar.tsx
import { Flex, Text, Box } from "@radix-ui/themes";
import styled from "styled-components";
import { ShoppingCart } from "lucide-react";

export const AppBar = () => {
    return (
        <StyledHeader>
            <Flex justify="between" align="center" p="3">
                <Text size="5" weight="bold" color="blue">
                    BookStore
                </Text>

                <CartWrapper>
                    <ShoppingCart size={24} />
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

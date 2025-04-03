import { Card, Flex, Text, Heading, Box } from "@radix-ui/themes";
import styled from "styled-components";
import { Book } from "../types/book";

interface BookCardProps {
  book: Book;
}

export const BookCard: React.FC<BookCardProps> = ({ book }) => {
  return (
    <Container>
      <StyledCard>
        <ImageContainer>
          <StyledImage src={book.imageUrl} alt={book.name} />
        </ImageContainer>

        <Content>
          <StyledHeading weight="bold">{book.name}</StyledHeading>
          <Description size="2" color="gray">
            {book.description}
          </Description>
          <StyledPrice size="5" weight="bold">
            ${book.price.toFixed(2)}
          </StyledPrice>
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

  object-fit: contain;
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
  flex-grow: 1; /* Ensures even spacing */
`;

const StyledPrice = styled(Text)`
  color: #1d4ed8;
  font-weight: bold;
`;

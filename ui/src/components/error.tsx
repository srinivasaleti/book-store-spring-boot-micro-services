import { AlertTriangle } from 'lucide-react';
import styled from 'styled-components';

export const ErrorContainer = ({
  error,
  onRetry,
}: {
  error: string;
  onRetry: () => void;
}) => {
  return (
    <Container>
      <AlertTriangle size={48} strokeWidth={1.5} color="#ef4444" />
      <ErrorTitle>Oops! Something went wrong</ErrorTitle>
      <ErrorMessage>{error}</ErrorMessage>
      <RetryButton onClick={onRetry}>Try Again</RetryButton>
    </Container>
  );
};

const Container = styled.div`
  min-height: 60vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  gap: 1rem;
  padding: 2rem;
`;

const ErrorTitle = styled.h2`
  font-size: 20px;
  font-weight: bold;
`;

const ErrorMessage = styled.p`
  color: #6b7280;
`;

const RetryButton = styled.button`
  padding: 10px 16px;
  background-color: #ef4444;
  color: #fff;
  border: none;
  border-radius: 6px;
  font-weight: bold;
  cursor: pointer;
  font-size: 14px;

  &:hover {
    background-color: #dc2626;
  }
`;

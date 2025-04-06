import { useEffect } from 'react';
import styled from 'styled-components';
import { ProductCard } from '../components/product-card';
import { useProducts } from '../hooks/useProducts';
import { Loader2 } from 'lucide-react';
import { ErrorContainer } from '../components/error';

export const Catalog = () => {
  const { productsData, loading, error, page, fetchProducts } = useProducts();

  useEffect(() => {
    fetchProducts();
  }, []);

  if (error) {
    return (
      <ErrorContainer
        error={'Oops! Something went wrong'}
        onRetry={() => fetchProducts()}
      />
    );
  }

  return (
    <div>
      {loading && (
        <LoaderContainer>
          <Loader2
            className="spinner"
            size={48}
            strokeWidth={2}
            color="#1d4ed8"
          />
          <LoaderText>Loading products...</LoaderText>
        </LoaderContainer>
      )}

      {!loading && (
        <>
          <ProductGrid>
            {productsData?.data.map((product) => (
              <ProductCard key={product.id} product={product} />
            ))}
          </ProductGrid>

          <PaginationContainer>
            <PaginationButton
              onClick={() => fetchProducts(page - 1)}
              disabled={!productsData?.hasPrev}
            >
              ⬅ Previous
            </PaginationButton>

            <PageInfo>
              Page {productsData?.currentPage} of {productsData?.totalPages}
            </PageInfo>

            <PaginationButton
              onClick={() => fetchProducts(page + 1)}
              disabled={!productsData?.hasNext}
            >
              Next ➡
            </PaginationButton>
          </PaginationContainer>
        </>
      )}
    </div>
  );
};

/* --- Styled Components --- */
const ProductGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 16px;
`;

const PaginationContainer = styled.div`
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 12px;
`;

const PaginationButton = styled.button`
  padding: 10px 16px;
  background-color: ${({ disabled }) => (disabled ? '#ccc' : '#1d4ed8')};
  color: #fff;
  border: none;
  border-radius: 6px;
  cursor: ${({ disabled }) => (disabled ? 'not-allowed' : 'pointer')};
  font-size: 14px;
  transition: background 0.2s;

  &:hover {
    background-color: ${({ disabled }) => (disabled ? '#ccc' : '#2563eb')};
  }
`;

const PageInfo = styled.span`
  font-size: 16px;
  font-weight: bold;
`;

const LoaderContainer = styled.div`
  min-height: 60vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  .spinner {
    animation: spin 1s linear infinite;
  }

  @keyframes spin {
    to {
      transform: rotate(360deg);
    }
  }
`;

const LoaderText = styled.p`
  margin-top: 0.75rem;
  font-size: 16px;
  color: #1f2937;
`;

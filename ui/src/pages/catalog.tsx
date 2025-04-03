import { ProductCard } from "../components/product-card";
import { useProducts } from "../hooks/useProducts";

export const Catalog = () => {
  const { productsData, loading, error } = useProducts();

  if (loading) return <p>Loading products...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div>
      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fill, minmax(280px, 1fr))",
        }}
      >
        {productsData?.data.map((product) => (
          <ProductCard key={product.id} product={product} />
        ))}
      </div>

      <div style={{ marginTop: "20px", textAlign: "center" }}>
        <p>
          Page {productsData?.currentPage} of {productsData?.totalPages}
        </p>
      </div>
    </div>
  );
};

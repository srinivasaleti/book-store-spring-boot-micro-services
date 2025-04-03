import { ProductCard } from "./product-card";
import { Box, Flex } from "@radix-ui/themes";
import { Product } from "../types/product";

interface Props {
  products: Product[];
}

export const ProductList: React.FC<Props> = ({ products }) => {
  return (
    <Box p="4">
      <Flex
        gap="4"
        wrap="wrap"
        justify="center"
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fill, minmax(280px, 1fr))",
          gap: "20px",
          justifyContent: "center",
        }}
      >
        {products.map((product) => (
          <ProductCard key={product.id} product={product} />
        ))}
      </Flex>
    </Box>
  );
};

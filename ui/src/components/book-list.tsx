import { BookCard } from "./book-card";
import { Box, Flex } from "@radix-ui/themes";
import { Book } from "../types/book";

interface BookListProps {
  books: Book[];
}

export const BookList: React.FC<BookListProps> = ({ books }) => {
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
        {books.map((book) => (
          <BookCard key={book.id} book={book} />
        ))}
      </Flex>
    </Box>
  );
};

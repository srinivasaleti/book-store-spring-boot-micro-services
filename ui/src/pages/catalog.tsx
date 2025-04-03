import { BookCard } from "../components/book-card";

const book = {
  id: 17,
  code: "P002",
  name: "1984",
  description:
    "A dystopian novel by George Orwell about a totalitarian regime.",
  imageUrl: "https://covers.openlibrary.org/b/id/7222246-L.jpg",
  price: 8.99,
};

export const Catalog = () => {
  return <BookCard key={book.id} book={book} />;
};

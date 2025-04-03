import { ProductList } from "../components/product-list";

const products = [
  {
    id: 17,
    code: "P002",
    name: "1984",
    description:
      "A dystopian novel by George Orwell about a totalitarian regime.",
    imageUrl: "https://covers.openlibrary.org/b/id/7222246-L.jpg",
    price: 8.99,
  },
  {
    id: 28,
    code: "P013",
    name: "Brave New World",
    description: "A dystopian novel by Aldous Huxley.",
    imageUrl: "https://covers.openlibrary.org/b/id/9253196-L.jpg",
    price: 8.99,
  },
  {
    id: 26,
    code: "P011",
    name: "Crime and Punishment",
    description: "A psychological novel by Fyodor Dostoevsky.",
    imageUrl: "https://covers.openlibrary.org/b/id/10958361-L.jpg",
    price: 9.49,
  },
  {
    id: 30,
    code: "P015",
    name: "Frankenstein",
    description: "A gothic novel by Mary Shelley.",
    imageUrl: "https://covers.openlibrary.org/b/id/12985243-L.jpg",
    price: 7.99,
  },
  {
    id: 20,
    code: "P005",
    name: "Harry Potter and the Sorcerer's Stone",
    description: "The first book in J.K. Rowling's famous series.",
    imageUrl: "https://covers.openlibrary.org/b/id/7888783-L.jpg",
    price: 14.99,
  },
];

export const Catalog = () => {
  return <ProductList products={products} />;
};

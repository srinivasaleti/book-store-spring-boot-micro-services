import { Route, Routes } from 'react-router-dom';
import { Catalog } from './pages/catalog';
import { NotFound } from './pages/not-found';
import { CartProvider } from './context/cart-context';

function App() {
  return (
    <Routes>
      <Route
        path="/"
        element={
          <CartProvider>
            <Catalog />
          </CartProvider>
        }
      />
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}

export default App;

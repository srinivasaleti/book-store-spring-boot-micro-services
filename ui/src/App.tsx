import { Route, Routes } from 'react-router-dom';
import { Catalog } from './pages/catalog';
import { NotFound } from './pages/not-found';
import { CartProvider } from './context/cart-context';
import { Cart } from './pages/cart';
import { AppBarProvider } from './context/app-bar-context';

export const CART_ROUTE = '/cart';
export const CATALOG_ROUTE = '/';

function App() {
  return (
    <Routes>
      <Route
        path={CATALOG_ROUTE}
        element={
          <CartProvider>
            <AppBarProvider>
              <Catalog />
            </AppBarProvider>
          </CartProvider>
        }
      />
      <Route
        path={CART_ROUTE}
        element={
          <CartProvider>
            <AppBarProvider>
              <Cart />
            </AppBarProvider>
          </CartProvider>
        }
      />
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}

export default App;

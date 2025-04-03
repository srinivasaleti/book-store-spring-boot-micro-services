import { Route, Routes } from "react-router-dom";
import { Catalog } from "./pages/catalog";
import { NotFound } from "./pages/not-found";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Catalog />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}

export default App;

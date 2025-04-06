import { createContext, ReactNode } from 'react';
import { AppBar } from '../components/app-bar';

type AppBarContextType = {};

const AppBarContext = createContext<AppBarContextType>({});

export const AppBarProvider = ({ children }: { children: ReactNode }) => {
  return (
    <AppBarContext.Provider value={{}}>
      <AppBar />
      {children}
    </AppBarContext.Provider>
  );
};

import React from "react";
import { useAuth } from "../hooks/useAuth";

type LayoutProps = {
  children: React.ReactNode;
};

const Layout: React.FC<LayoutProps> = ({ children }) => {
  const { user, logout } = useAuth();

  console.log("user", user);

  const handleLogout = () => {
    logout();
  };

  return (
    <div className="flex flex-col min-h-screen bg-gray-100">
      <nav className="bg-gray-800 text-white p-4 flex justify-between items-center">
        <div className="font-bold text-xl">Your Logo</div>
        {user && (
          <div className="flex items-center space-x-4">
            <span>Logged in as {user.name}</span>
            <button
              onClick={handleLogout}
              className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-3 rounded text-sm"
            >
              Sign Out
            </button>
          </div>
        )}
      </nav>

      <main className="flex-grow flex justify-center items-center">
        {children}
      </main>

      <footer className="bg-gray-200 text-center p-4 mt-8">
        © 2023 Your Website
      </footer>
    </div>
  );
};

export default Layout;

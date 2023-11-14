import React from "react";
import logo from "./logo.svg";

function App() {
  return (
    <div className="text-center">
      <header className="bg-gray-800 min-h-screen flex flex-col items-center justify-center text-white">
        <img
          src={logo}
          className="h-40 animate-spin-slow pointer-events-none"
          alt="logo"
        />
        <p className="text-base">
          Edit <code className="text-lg">src/App.tsx</code> and save to reload.
        </p>
        <a
          className="text-blue-300"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;

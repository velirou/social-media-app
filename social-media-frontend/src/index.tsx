import React from 'react';
import { createRoot } from 'react-dom/client'; // React 18's new root API
import App from './App'; // Import the root App component
import './index.css'; // Optional: Import global styles

// Get the root container element from the DOM
const container = document.getElementById('root');

// Ensure the root container exists
if (!container) {
  throw new Error('Root container not found');
}

// Create a root for the app using the new React 18 API
const root = createRoot(container);

// Render the App component inside the root
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
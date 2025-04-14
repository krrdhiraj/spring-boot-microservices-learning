import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { AuthProvider } from './Config/authContext.jsx'
import Home from './components/Home.jsx'

createRoot(document.getElementById('root')).render(
  // <StrictMode>
    <AuthProvider>
      <Home />
    </AuthProvider>
  // {/* </StrictMode> */}
)

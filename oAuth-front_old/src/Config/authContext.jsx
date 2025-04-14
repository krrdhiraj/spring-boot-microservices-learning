import { createContext, useContext, useEffect, useState } from "react"
import keycloak from "./keycloak";

const AuthContext = createContext();

export const AuthProvider = ({children}) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [keycloakInstance, setKeycloakInstance] = useState(null);
// jaise hi application load hoga keycloak : banda login h ki nhi

useEffect(() => {
    // call hoga on start
    keycloak
    .init({ // check krega user login h ki nhi h
        onLoad: "login-required", 
        // onLoad: "check-sso", // by default login nhi karega
        pkceMethod: "S256"
    }) // if user is authenticated then
    .then(authenticated => {
        console.log("user login success");
        console.log(isAuthenticated);
        setIsAuthenticated(authenticated);
        setKeycloakInstance(keycloak);
        console.log(keycloak);
    })// agar koi errror aa gya
    .catch((error) =>{
        console.log("error", error);
        console.log("user login fail");
    });
}, []);

    return (
    <AuthContext.Provider value={
        {
            isAuthenticated,
            setIsAuthenticated,
            keycloak:keycloakInstance
        }}
    >
    {children}
    </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);

// jo v pass krenge Authprovider ke andar wo iss tarah se configure hoga.
// <AuthProvider>
//     <div>
//         { children }
//     </div>
// </AuthProvider> 
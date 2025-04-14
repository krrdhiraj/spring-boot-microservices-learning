import { useState } from 'react'
import './App.css'
import { useAuth } from './Config/authContext'

function App() {
  const [count, setCount] = useState(0)

const {isAuthenticated, setIsAuthenticated, keycloak} = useAuth();
console.log(isAuthenticated);
console.log(keycloak);
  return (
    <>
    {console.log(keycloak)}
    {isAuthenticated ? 
    <div>
        <h1>Welcome to oAuth-Front Application</h1>
        <h2>Cliend-Id : {keycloak.clientId}</h2>
        <h2>UserName : {keycloak.tokenParsed.preferred_username}</h2>
        <h3>FullName : {keycloak.tokenParsed.given_name} {keycloak.tokenParsed.family_name}</h3>
        <h3>Email : {keycloak.tokenParsed.email}</h3>
        <h4>Roles : {" "}
          {keycloak.realmAccess.roles.map((item) => 
            (<span>{item} ,</span>))
          }</h4>
        <h2>User Information</h2>
        <h2>
          {isAuthenticated ?
           "User is Authenticated !" 
           : "User is not Authenticated !"}
           </h2>
        <button onClick={() => {
          keycloak.logout();
          console.log("User is logged out");
        }}>Logout</button>
        </div>
        :<div>
          <h3>Login Required !</h3>
          <button onClick={()=>{
            keycloak.login();
          }}>Login</button>
          </div>
           } 
    </>
  )
}

export default App

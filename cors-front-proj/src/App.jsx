import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const apiUrl = "http://localhost:8081/api/v1/categories"
  function getData(){
    console.log("getting Data")
    // logic to get Data from Backend(Server)
    fetch(apiUrl)
    .then((response)=>{
      response.json().
      then((data)=>{
        console.log(data);
      }).catch((error)=>
         console.log(error))
    }).catch((error)=>
       console.log(error));
  }
  return (
    <>
      
      <h1>Cors-Front</h1>
      <h1>Popular Categories  </h1>
      <div className="card">
        <button onClick={getData}>Get Data</button>
        
      </div>
      <p className="read-the-docs">
        Developed by Dhiraj & Team.
      </p>
    </>
  )
}

export default App

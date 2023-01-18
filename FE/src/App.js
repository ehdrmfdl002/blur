import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import React from "react";

import "./App.css";

import Home from "./pages/Home";
// import Meeting from "./pages/Meeting";
import MyInfo from "./pages/MyInfo";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<Home />}></Route>
          <Route path="/myinfo" element={<MyInfo />}></Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;

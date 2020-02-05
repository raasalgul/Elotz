import React from 'react';
import './App.css';
import ResponsiveDrawer from './components/Layouts/ResponsiveDrawer';
import {BrowserRouter as Router,Route} from "react-router-dom";
import {dashboardRoutes} from "./constants/RoutesObjects"
function App() {
  return (
    <div className="App">
     
      <Router>
      {dashboardRoutes.map((prop,key) => {
                console.log(prop)
        return(
         <Route
                  path={prop.path}
                  component={prop.component}
                  key={key}
                />
        );
                })
      }
     <ResponsiveDrawer/>
     </Router>
    </div>
  );
}

export default App;

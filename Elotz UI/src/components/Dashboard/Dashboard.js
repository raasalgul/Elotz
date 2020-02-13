import React from 'react';
import Daily from './Daily';
import Monthly from './Monthly';
import Yearly from './Yearly';
function Dashboard() {
  
  return (
    <div  style={{display:'flex',flexWrap:'wrap',marginTop:'50px',justifyContent:'center'}}>
  <Daily style={{margin:'30px'}}/>
  <Monthly />
    <Yearly/>
    <Monthly />
    </div>
  );
}

export default Dashboard;

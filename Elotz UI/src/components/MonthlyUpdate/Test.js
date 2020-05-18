import React, { useState,useEffect } from 'react';
import Calendar from 'react-calendar';
import { Button } from '@material-ui/core';
const Test = () => {
    const[date,setDate]=useState(new Date());
    const[calenderClick,setCalenderClick]=useState(true);
    const serviceURLHost="http://localhost:8089";
    // useEffect(()=>{
    //     fetch(`${serviceURLHost}/Elotz-home/graph/daily`).then((response) => {
    //         return response.json();
    //       })
    //       .then((myJson) => {
       
    // },[])
    return (
        
        <div style={{margin:'50vh'}}>
        {console.log(date)}
        <Button variant="contained" color="primary" onClick={()=>{setCalenderClick(!calenderClick)}}>Show Calender</Button>        
        {calenderClick?
          <Calendar
            onChange={(date)=>{setDate(date)}}
            onClickDay={()=>{setCalenderClick(false)}}
            value={date}
          />:""
        }
        </div>
      );
}
 
export default Test;
import React, { useState } from 'react';
import Calendar from 'react-calendar';
import { Button } from '@material-ui/core';
import { Typography } from '@material-ui/core';
const CustomCalender = (props) => {
    const[date,setDate]=useState(new Date());
    const[calenderClick,setCalenderClick]=useState(false);
    // const serviceURLHost="http://localhost:8089";
    // useEffect(()=>{
    //     fetch(`${serviceURLHost}/Elotz-home/graph/daily`).then((response) => {
    //         return response.json();
    //       })
    //       .then((myJson) => {
       
    // },[])
    let buttonName=!calenderClick?"Show Calender":"Hide Calender"
    return (
        <div>
        {}
        <Button variant="contained" color="primary" onClick={()=>{setCalenderClick(!calenderClick)}}>
          {buttonName}</Button>        
        {calenderClick?
          <Calendar
            // onChange={(date)=>{
            //   // console.log(date);
            //   // setDate(date)
            // }}
            onClickDay={(d)=>{
              setDate(d);
              let localDate=`${d.getFullYear()}-${d.getMonth()+1<10?`0${d.getMonth()+1}`:d.getMonth()+1}-${d.getDate()<10?
                `0${d.getDate()}`:d.getDate()}`;
              console.log();
              setCalenderClick(false);
              props.selectedDate(localDate);
            }}
            value={date}
          />:""
        }
        <br/>
        <Typography variant="subtitle1" component="h2">
        {props.intialDate}
        </Typography>
        </div>
      );
}
 
export default CustomCalender;
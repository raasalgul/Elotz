import React, { Fragment } from 'react';
import Daily from './Daily';
import Monthly from './Monthly';
import Yearly from './Yearly';
import {useEffect,useState} from "react"
import { makeStyles } from '@material-ui/core/styles';
import CircularProgress from '@material-ui/core/CircularProgress';
const useStyles = makeStyles(theme => ({
  root: {
    display:'flex',
    flexWrap:'wrap',
    marginTop:'13vh',
    justifyContent:'center'
  },
  // light:{
  //   display:'flex',
  //   flexWrap:'wrap',
  //   marginTop:'50px',
  //   justifyContent:'center',
  //   backgroundColor: 'rgba(0,0,0,0.5)'
  // }
}));
function Dashboard() {
   const [check,setCheck]=useState(true); 
   const classes = useStyles();
  
  useEffect(
    ()=>{
      new Promise(resolve => {
        setTimeout(() => {
          resolve();
        setCheck(false);
        }, 1000);
      })
    }
  );
  return (
    // check? <div style={{position:"fixed",top:"50%",right:"50%"}}><CircularProgress color="secondary"/></div>:
    <Fragment>
   { check?<div style={{position:"fixed",top:"50%",right:"50%"}}><CircularProgress color="secondary"/></div>:null}
    <div className={classes.root}>
  <Daily/>
  {/* <Monthly />
    <Yearly/>
    <Monthly /> */}
    </div>
    </Fragment>
   );
}
export default Dashboard;

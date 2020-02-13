import React from 'react';
import Daily from './Daily';
import Monthly from './Monthly';
import Yearly from './Yearly';
import {useEffect,useState} from "react"
import { makeStyles } from '@material-ui/core/styles';
import CircularProgress from '@material-ui/core/CircularProgress';
const useStyles = makeStyles(theme => ({
  root: {
    display: 'flex',
    '& > * + *': {
      marginLeft: theme.spacing(2),
    },
  },
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
        }, 6000);
      })
    }
  );
  return (
    check? <div style={{position:"fixed",top:"50%",right:"50%"}}><CircularProgress color="secondary"/></div>:
    <div  style={{display:'flex',flexWrap:'wrap',marginTop:'50px',justifyContent:'center'}}>
  <Daily style={{margin:'30px'}}/>
  <Monthly />
    <Yearly/>
    <Monthly />
    </div>
   );
}
export default Dashboard;

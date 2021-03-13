import React from 'react';
import {useEffect,useState} from "react"
import { makeStyles } from '@material-ui/core/styles';
import Select from '@material-ui/core/Select';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
const useStyles = makeStyles({
  root: {
    position: 'relative',
    top:'200px'
  },
  year: {
   display:'flex',
   margin:30,
   flexDirection:'column',
   alignItems:'flex-end'
  },
  root_cardContent:{
    maxHeight: 275,
    overflowY:'scroll'
  },
  card_root: {
    minWidth: 275,
    maxWidth: 500,
    minHeight:180,
    maxHeight: 180,
    backgroundColor: "#79B473",
    color:"#ffffff"
  },
  topic_style: {
    color: "#70A37F",
    fontSize: 32,
    fontFamily:"Andale Mono",
    padding:20
  },
  task_style: {
    fontSize: 24
  },
  next_button:{
     backgroundColor: "#41658A",
     color:"#ffffff",
     float:"left",
     margin:10,  
     '&:hover': {
      backgroundColor:'#414073',
      color: '#FFF'
  }
  },
  prev_button:{
    backgroundColor: "#41658A",
    color:"#ffffff",
    float:"right",
    margin:10,
    '&:hover': {
      backgroundColor:'#41658A',
      color: '#FFF'
  }
 }
});
let topicIndex=0;
export default function AllView() {
    const serviceURLHost="http://localhost:8089";
  const classes = useStyles();

const [yearKeys,setYearKeys]=useState([]);
const [yearKey,setYearKey]=useState('');
const [monthKeys,setMonthKeys]=useState([]);
const [monthKey,setMonthKey]=useState('');
const [data,setData]=useState({});
const [load,setLoad]=useState(false);
useEffect(()=>{
  fetch(`${serviceURLHost}/Elotz-home/allData/view`).then((response) => {
    return response.json();
  })
  .then((myJson) => {
    setData(myJson);
    let keys=Object.keys(myJson);
    setYearKeys(keys);
    setYearKey(`${keys[0]}`);
    let monthKeys=Object.keys(myJson[keys[0]]);
    setMonthKeys(monthKeys);
    setMonthKey(monthKeys[0]);
    setLoad(true);
    });
  },[]);
  function handleNext() {
    topicIndex<monthKeys.length-1?topicIndex++:topicIndex=0;
    console.log(`${topicIndex} Length ${monthKeys.length}`);
    var name=monthKeys[topicIndex];
    setMonthKey(name.toUpperCase());
  }
  function handlePrevious() {
    topicIndex>0?--topicIndex:topicIndex=monthKeys.length-1;
    // console.log(`${topicIndex} Length ${Object.length}`);
    var name=monthKeys[topicIndex];
    setMonthKey(name.toUpperCase());
    }
return (
  <div>
  {load?<div className={classes.root}>
        <div className={classes.year}>
        <InputLabel id="topic-select-label">Year</InputLabel>
        <Select
          labelId="topic-select-label"
          id="topic-select"
          value={yearKey}
          onChange={(event)=>{setYearKey(event.target.value);
            let monthKeys=Object.keys(data[event.target.value]);
    setMonthKeys(monthKeys);
    setMonthKey(monthKeys[0]);
    console.log(monthKeys[0]);
          }}
        >
          {yearKeys.map(
                      (year, index) => (
                        <MenuItem key={index} value={year}>
                          {year}
                        </MenuItem>
                      )
                    )}
        </Select>
        </div>
        {console.log(`both ${+Object.keys(data).length !== 0 && data.constructor !== Object}`)}
        {console.log(`single ${+Object.keys(data).length}`)}
           <Typography variant="h5" component="h2" className={classes.topic_style}>
                       {monthKey}
                       </Typography>
            <Card>
            <CardContent className={classes.root_cardContent}>
                <Grid container spacing={2}>
                {
                  Object.keys(data).length !== 0?data[yearKey][monthKey].map((value,index)=>{
                    return(
                     <Grid key={index} item xs={3}>
                     <Card className={classes.card_root}>
                     <CardContent>
                       <Typography className={classes.task_style} gutterBottom>
                        {value.task}
                       </Typography>
                        <Typography variant="body2" component="p">
                         Description 
                         <br />
                         <br/>
                         {'"Time" : '}{value.time}
                         <br/>
                         <br/>
                         {value.active?"It is active":"It is not active"}
                       </Typography> 
                     </CardContent>
                   </Card>
                     </Grid>);
                }
                )
               :null
                }
            </Grid>
            </CardContent>
            <CardActions>
            <Grid container>
            <Grid item xs={6}>
              <Button size="small" variant="contained" color="default" className={classes.next_button} onClick={handlePrevious}>previous</Button>
              </Grid>
              <Grid item xs={6}>
              <Button size="small" variant="contained" color="default" className={classes.prev_button} onClick={handleNext}>Next</Button>
           </Grid>
           </Grid>
            </CardActions>
          </Card>
   </div>:null}
   </div>
  );
  
}
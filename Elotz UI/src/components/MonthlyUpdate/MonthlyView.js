import React from 'react';
import {useEffect,useState} from "react"
import { makeStyles } from '@material-ui/core/styles';
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
// const test={
//   'java':['Oops','Collections','Variables','Loops','Date','String'],
//   'react':['Hooks','Functional components','Class components'],
//   'mongo db':['DML','DDL','transations']
// }
export default function MonthlyView() {
    const serviceURLHost="http://localhost:8089";
  const classes = useStyles();
// const response=['java','react','mongo db','Jax-RS','hooks','react','mongo db','Jax-RS','hooks','react','mongo db','Jax-RS','hooks','react','mongo db','Jax-RS','hooks'];

const [topic,setTopic]=useState('');
const [tasks,setTasks]=useState([]);
const [data,setData]=useState([]);
useEffect(()=>{
  fetch(`${serviceURLHost}/Elotz-home/monthlyUpdate/view`).then((response) => {
    return response.json();
  })
  .then((myJson) => {
    var jsonString=JSON.stringify(myJson);
    console.log(jsonString);
    // var initJson=`{no data: no data}`;
    // jsonString.length<=2?
    // setData(initJson):
    // setData(myJson);
    setData(myJson);
    // var name=jsonString.length<=2?"no data":Object.keys(myJson)[0];
    var name=Object.keys(myJson)[0];
    console.log(name);
  setTopic(name.toUpperCase());
  setTasks(myJson[name]);
  });
  },[]);
  function handleNext() {
    topicIndex<Object.keys(data).length-1?topicIndex++:topicIndex=0;
    // console.log(`${topicIndex} Length ${Object.keys(test).length}`);
    var name=Object.keys(data)[topicIndex];
    setTopic(name.toUpperCase());
    setTasks(data[name]);
   
  }
  function handlePrevious() {
    topicIndex>0?--topicIndex:topicIndex=Object.keys(data).length-1;
    // console.log(`${topicIndex} Length ${Object.length}`);
    var name=Object.keys(data)[topicIndex];
    setTopic(name.toUpperCase());
    setTasks(data[name]);
    //console.log(`Index ${name.toUpperCase()}`);
  }
  // console.dir(handleNext);
return (
      <div className={classes.root}>
          <Typography variant="h5" component="h2" className={classes.topic_style}>
                       {topic}
                       </Typography>
            <Card>
            {console.log(`tasks`+JSON.stringify(tasks))}
            <CardContent className={classes.root_cardContent}>
                <Grid container spacing={2}>
                {
 
                tasks.map((value,index)=>{
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
                })}
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
   </div>
  );
  
}
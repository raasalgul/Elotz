import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Select from '@material-ui/core/Select';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Checkbox from '@material-ui/core/Checkbox';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import CircularProgress from '@material-ui/core/CircularProgress';
import {useState,useEffect} from "react";
import CustomCalender from "./CustomCalender";
// import axios from "axios";
const serviceURLHost="http://localhost:8089";
const useStyles = makeStyles(theme => ({
    root: {
        display:'flex',
        minWidth: 275,
        position:"relative",
        top:"150px",
        flexWrap:"wrap",
        flexShrink:1,
        justifyContent:"center",
      '& .MuiTextField-root': {
        margin: theme.spacing(1),
        width: 420,
      },
      '& .MuiFormControlLabel-root':{
        margin: theme.spacing(2.5),
      }
    },
    card: {
        flexBasis:'1000px',
      },
      card_blur:{
        flexBasis:'1000px',
        backgroundColor: 'rgba(0,0,0,0.3)'
      },
      formControl: {
        margin: theme.spacing(1),
        flexBasis:'700px'
      },
      topic:{
        flexBasis:'700px'
      },
      checkboxs:{
        display:'flex',
        marginTop:'25px'
      },
      cardContent:{
        display:'flex',
        flexWrap:'wrap',
        flexShrink:1,
      },
      task_view:{
        flexBasis:'700px'
      },
      time:{
        flexBasis:'660px'
      },
      month: {
        margin: theme.spacing(1),
        flexBasis:'250px'
      },
      submitButton:{
        backgroundColor: "#41658A",
        color:"#ffffff",
        float:"left",
        margin:10,  
        '&:hover': {
         backgroundColor:'#414073',
         color: '#FFF'
      }
    }
  }));
export default function MonthlyUpdate() {
    const [topic, setTopic] = React.useState('');
    const [task, setTask] = React.useState('');
    const [time,setTime]=React.useState('');
    const [month,setMonth]=React.useState('');
    const [checkedTopic, setCheckedTopic] = React.useState(false);
    const [checkedTask, setCheckedTask] = React.useState(false);
    const [check,setCheck]=useState(false); 
    const [getTopic,setGetTopic]=React.useState([]);
    const [getTask,setGetTask]=React.useState({ "topic": "",
    "tasks": [
       
    ],
    "time": [
       
    ],
    "active":[

    ]
  });
    useEffect(()=>{
      fetch(`${serviceURLHost}/Elotz-home/dailyUpdate/topic`).then((response) => {
        return response.json();
      })
      .then((myJson) => {
        console.log(myJson);
        setGetTopic(myJson);
      });
      // if(topic!=='' && !checkedTopic)
      if(topic!=='' && !checkedTopic)
      fetch(`${serviceURLHost}/Elotz-home/dailyUpdate/task/${topic}`).then((response) => {
        return response.json();
      })
      .then((myJson) => {
        console.log(myJson);
        setGetTask(myJson);
       
      });
    },[topic,checkedTopic]);
  const classes = useStyles();
  
  async function handleSubmit(){
    let data={};
    data.topic=topic;
    data.task=task;
    data.time=time;
    data.month=month;
    console.log(data);
    const response = await fetch(`${serviceURLHost}/Elotz-home/monthlyUpdate/post`, {
      method: 'POST', // *GET, POST, PUT, DELETE, etc.
      mode: 'cors', // no-cors, *cors, same-origin
      cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
      credentials: 'same-origin', // include, *same-origin, omit
      headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      redirect: 'follow', // manual, *follow, error
      referrerPolicy: 'no-referrer', // no-referrer, *client
      body: JSON.stringify(data) // body data type must match "Content-Type" header
    });
    return await response.json(); // parses JSON response into native JavaScript objects
  
  }
  return (
      <div className={classes.root}>
         {check? <div style={{position:"absolute",top:"50%",right:"50%"}}><CircularProgress color="secondary"/></div>:null}
    <Card className={check?classes.card_blur:classes.card}>
      <CardContent className={classes.cardContent}>
          {checkedTopic?
        <TextField  value={topic} className={classes.topic} id="standard-basic" label="Topic"  onChange={(event)=>{setTopic(event.target.value)}}/>:
        <FormControl className={classes.formControl}>
      <InputLabel id="topic-select-label">Topic</InputLabel>
        <Select
          labelId="topic-select-label"
          id="topic-select"
          value={topic}
          onChange={(event)=>{setTopic(event.target.value);
            setCheck(true);
            new Promise(resolve => {
              setTimeout(() => {
                resolve();
              setCheck(false);
              }, 1000);
            })
          }}
        >
{getTopic.map(
                      (stateCodeOption, index) => (
                        <MenuItem key={index} value={stateCodeOption}>
                          {stateCodeOption}
                        </MenuItem>
                      )
                    )}
        </Select>
        </FormControl>}
        <FormControlLabel
        className={classes.checkboxs}
        control={
            <Checkbox
            checked={checkedTopic}
            onChange={()=>{setCheckedTopic(!checkedTopic);
            }}
            value="primary"
            inputProps={{ 'aria-label': 'primary checkbox' }}
          />
        }
        label="New Topic"
      />
        {checkedTask?
         <TextField value={task} className={classes.task_view} id="standard-basic" label="Task" onChange={(event)=>{setTask(event.target.value)}}/>:
        <FormControl className={classes.formControl}>
      <InputLabel id="task-select-label">Task</InputLabel>
        <Select
          labelId="task-select-label"
          id="task-select"
          value={task}
          onChange={(event)=>{setTask(event.target.value);
            setCheck(true);
            new Promise(resolve => {
              setTimeout(() => {
                resolve();
               
              setCheck(false);
              const localTask=getTask.tasks.indexOf(event.target.value);
                console.log(getTask.tasks[localTask] +` `+getTask.active[localTask]);
             //   setCheckedActive(getTask.active[localTask]);
                setTime(getTask.time[localTask]);
              }, 1000);
            })
          }}
        >
          {getTask.tasks.map(
                      (stateCodeOption, index) => (
                        <MenuItem key={index} value={stateCodeOption}>
                          {stateCodeOption}
                        </MenuItem>
                      )
                    )}
        </Select>
        </FormControl>}
        <FormControlLabel
        className={classes.checkboxs}
        control={
            <Checkbox
            checked={checkedTask}
            onChange={()=>{setCheckedTask(!checkedTask);
            }}
            value="primary"
            inputProps={{ 'aria-label': 'primary checkbox' }}
          />
        }
        label="New Task"
      />
      <TextField className={classes.time} value={time} id="standard-basic" label="Time in hours" onChange={(event)=>{setTime(event.target.value)}}/>
      <FormControl className={classes.month}>
        <CustomCalender/>
        </FormControl>
      </CardContent>
      <CardActions>
        <Button className={classes.submitButton} disabled={check} size="small" variant="contained" color="primary" onClick={handleSubmit}>Submit</Button>
      </CardActions>
    </Card>
    </div>
  );
}

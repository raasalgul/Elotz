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
import {useState,useEffect} from "react"
import axios from "axios";
const serviceURLHost="localhost:8089/";
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
      task:{
        flexBasis:'700px'
      },
      time:{
        flexBasis:'700px'
      }
  }));
export default function DailyUpdate() {
    const [topic, setTopic] = React.useState('');
    const [task, setTask] = React.useState('');
    const [checkedTopic, setCheckedTopic] = React.useState(false);
    const [checkedTask, setCheckedTask] = React.useState(false);
    const [check,setCheck]=useState(false); 
    const [getTopic,setGetTopic]=React.useState([]);
    const [getTask,setGetTak]=React.useState({});
    useEffect(()=>{
      axios.get(`${serviceURLHost}/Elotz-home/dailyUpdate/topic`).then(res => {
        const topics = res.data;
        console.log(topics);
        setGetTopic(topics);
      });
    });
  const classes = useStyles();
 
  return (
      <div className={classes.root}>
         {check? <div style={{position:"absolute",top:"50%",right:"50%"}}><CircularProgress color="secondary"/></div>:null}
    <Card className={check?classes.card_blur:classes.card}>
      <CardContent className={classes.cardContent}>
          {checkedTopic?
        <TextField className={classes.topic} id="standard-basic" label="Topic" />:
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
          {/* {
          getTopic.map((topic,index)=>{
          <MenuItem value={topic}>{topic}</MenuItem>
          })
          } */}
          {/* <MenuItem value={'React'}>React</MenuItem>
          <MenuItem value={'Java'}>Java</MenuItem>
          <MenuItem value={'Spring'}>Spring</MenuItem> */}
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
         <TextField className={classes.task} id="standard-basic" label="Task" />:
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
              }, 1000);
            })
          }}
        >
          <MenuItem value={'Ten'}>Ten</MenuItem>
          <MenuItem value={'Twenty'}>Twenty</MenuItem>
          <MenuItem value={'Thirty'}>Thirty</MenuItem>
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
      <TextField className={classes.time} id="standard-basic" label="Time in hours" />
      </CardContent>
      <CardActions>
        <Button disabled={check} size="small" variant="contained" color="primary">Submit</Button>
      </CardActions>
    </Card>
    </div>
  );
}
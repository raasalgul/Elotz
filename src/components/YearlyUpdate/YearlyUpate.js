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
        flexBasis:'1000px'
      },
      formControl: {
        margin: theme.spacing(1),
        flexBasis:'700px'
      },
      month: {
        margin: theme.spacing(1),
        flexBasis:'150px'
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
export default function YearlyUpdate() {
    const [topic, setTopic] = React.useState('');
    const [task, setTask] = React.useState('');
    const [checkedTopic, setCheckedTopic] = React.useState(false);
    const [checkedTask, setCheckedTask] = React.useState(false);
    // const handleChange = event => {
    //     setAge(event.target.value);
    //   };
  const classes = useStyles();
 
  return (
      <div className={classes.root}>
    <Card className={classes.card}>
      <CardContent className={classes.cardContent}>
          {checkedTopic?
        <TextField className={classes.topic} id="standard-basic" label="Topic" />:
        <FormControl className={classes.formControl}>
      <InputLabel id="topic-select-label">Topic</InputLabel>
        <Select
          labelId="topic-select-label"
          id="topic-select"
          value={topic}
          onChange={(event)=>{setTopic(event.target.value)}}
        >
          <MenuItem value={'React'}>React</MenuItem>
          <MenuItem value={'Java'}>Java</MenuItem>
          <MenuItem value={'Spring'}>Spring</MenuItem>
        </Select>
        </FormControl>}
        <FormControlLabel
        className={classes.checkboxs}
        control={
            <Checkbox
            checked={checkedTopic}
            onChange={()=>{setCheckedTopic(!checkedTopic)}}
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
          onChange={(event)=>{setTask(event.target.value)}}
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
            onChange={()=>{setCheckedTask(!checkedTask)}}
            value="primary"
            inputProps={{ 'aria-label': 'primary checkbox' }}
          />
        }
        label="New Task"
      />
      <TextField className={classes.time} id="standard-basic" label="Time in hours" />
      <FormControl className={classes.month}>
      <InputLabel id="topic-select-label">Year</InputLabel>
        <Select
          labelId="topic-select-label"
          id="topic-select"
          value={topic}
          onChange={(event)=>{setTopic(event.target.value)}}
        >
          <MenuItem value={'2019'}>2019</MenuItem>
          <MenuItem value={'2020'}>2020</MenuItem>
          <MenuItem value={'2021'}>2021</MenuItem>
        </Select>
        </FormControl>
      </CardContent>
      <CardActions>
        <Button size="small" variant="contained" color="primary">Submit</Button>
      </CardActions>
    </Card>
    </div>
  );
}
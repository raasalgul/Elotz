import React from 'react';
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
    top:'250px'
  },
  root_cardContent:{
    maxHeight: 275,
    overflowY:'scroll'
  },
  card_root: {
    minWidth: 275,
    maxWidth: 'fit-content'
  },
  bullet: {
    display: 'inline-block',
    margin: '0 2px',
    transform: 'scale(0.8)',
  },
  title: {
    fontSize: 14,
  },
  pos: {
    marginBottom: 12,
  },
});

export default function DailyView() {
  const classes = useStyles();
const response=['java','react','mongo db','Jax-RS','hooks','react','mongo db','Jax-RS','hooks','react','mongo db','Jax-RS','hooks','react','mongo db','Jax-RS','hooks'];
  return (
    <Card className={classes.root}>
      <CardContent className={classes.root_cardContent}>
          <Grid container spacing={2}>
          {response.map(value=>{
              return(
               <Grid item xs={3}>
               <Card className={classes.card_root}>
               <CardContent>
                 <Typography className={classes.title} color="textSecondary" gutterBottom>
                 Topic {value}
                 </Typography>
                 <Typography variant="h5" component="h2">
                 World of {value}
                 </Typography>
                 <Typography className={classes.pos} color="textSecondary">
                   Task
                 </Typography>
                 <Typography variant="body2" component="p">
                   Description
                   <br />
                   {'"author"'}
                 </Typography>
               </CardContent>
             </Card>
               </Grid>);
          })}
      </Grid>
      </CardContent>
      <CardActions>
        <Button size="small">Learn More</Button>
      </CardActions>
    </Card>
  );
}

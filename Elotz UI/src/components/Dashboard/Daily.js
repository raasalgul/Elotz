import React, { useEffect } from 'react';
import Chart from "react-google-charts";
import {useTheme } from '@material-ui/core/styles';
import MobileStepper from '@material-ui/core/MobileStepper';
import Button from '@material-ui/core/Button';
import KeyboardArrowLeft from '@material-ui/icons/KeyboardArrowLeft';
import KeyboardArrowRight from '@material-ui/icons/KeyboardArrowRight';
const Daily = () => {
   const [activeStep, setActiveStep] = React.useState(0);
   const serviceURLHost="http://localhost:8089";
   const [data, setData] = React.useState([]);
  const maxSteps = 52;
  const theme = useTheme();
  const handleNext = () => {
    setActiveStep(prevActiveStep => prevActiveStep + 1);
  };

  const handleBack = () => {
    setActiveStep(prevActiveStep => prevActiveStep - 1);
  };
  useEffect(()=>{
    fetch(`${serviceURLHost}/Elotz-home/graph/weekday`).then((response) => {
      return response.json();
    })
    .then((myJson) => {
      console.log(JSON.stringify(myJson));
      setData(myJson);
     
    });
  },[]);
    return ( 
      <div>
    <Chart
        width={'600px'}
        height={'400px'}
        chartType="LineChart"   
        loader={<div>Loading Chart</div>}
        data={
          [
            ["Java-Variables","Java-Oops","React-Hooks","Java-Collections","React-Components"],
            [2,1,0,0,0],
            [4,2,0,0,0],
            [6,3,5,3,2]
          ]}
       // data={data}
        options={{
          title: "Week day status",
         // minValue:8,
         hAxis: {
          title: 'Days',
          minValue:0,
          //maxValue:7
        },
        vAxis: {
          title: 'Hours Spend',
          minValue:0
        },
        }}
        rootProps={{ 'data-testid': '3' }}
      />
       <MobileStepper
        steps={maxSteps}
        position="static"
        variant="text"
        activeStep={activeStep}
        nextButton={
          <Button size="small" onClick={handleNext} disabled={activeStep === maxSteps - 1}>
            Next
            {theme.direction === 'rtl' ? <KeyboardArrowLeft /> : <KeyboardArrowRight />}
          </Button>
        }
        backButton={
          <Button size="small" onClick={handleBack} disabled={activeStep === 0}>
            {theme.direction === 'rtl' ? <KeyboardArrowRight /> : <KeyboardArrowLeft />}
            Back
          </Button>
        }
      />
      </div>
      );
}
 
export default Daily;
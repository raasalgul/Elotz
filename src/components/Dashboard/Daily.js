import React from 'react';
import Chart from "react-google-charts";
const Daily = () => {
    return ( 
    <Chart
        width={'600px'}
        height={'400px'}
        chartType="LineChart"
        loader={<div>Loading Chart</div>}
        data={[
          [
            'Day',
            'Google',
            'Microsoft',
            'Water',
          ],
          [1,10 ,20, 5],
          [2,5,20, 5],
          [3,5, 18, 5],
          [4,3, 18,4],
          [5,3, 13, 4],
          [6, 2, 13, 0],
          [7, 0,9, 0],
        ]}
        options={{
          title: "Week day status",
         // minValue:8,
         hAxis: {
          title: 'Days',
          minValue:0,
          maxValue:7
        },
        vAxis: {
          title: 'Hours Spend',
        },
        }}
        rootProps={{ 'data-testid': '3' }}
      />);
}
 
export default Daily;
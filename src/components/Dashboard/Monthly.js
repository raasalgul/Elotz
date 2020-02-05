import React from 'react';
import Chart from "react-google-charts";
const Monthly = () => {
    return ( <Chart
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
          [8,10 ,20, 5],
          [9,5,20, 5],
          [10,5, 18, 5],
          [11,3, 18,4],
          [12,3, 13, 4],
          [13, 2, 13, 0],
          [14, 0,9, 0],
          [15,10 ,20, 5],
          [16,5,20, 5],
          [17,5, 18, 5],
          [18,3, 18,4],
          [19,3, 13, 4],
          [20, 2, 13, 0],
          [21, 0,9, 0],
          [22,10 ,20, 5],
          [23,5,20, 5],
          [24,5, 18, 5],
          [25,3, 18,4],
          [26,3, 13, 4],
          [27, 2, 13, 0],
          [28, 0,9, 0]
        ]}
        options={{
            title: "Monthly status",
           hAxis: {
            title: 'Days',
            minValue:1,
            maxValue:31
          },
          vAxis: {
            title: 'Hours Spend',
          },
          }}
        rootProps={{ 'data-testid': '3' }}
      />);
}
 
export default Monthly;
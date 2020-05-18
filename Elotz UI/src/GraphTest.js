import React, { useState,useEffect } from 'react';
import {Line} from 'react-chartjs-2'
const Test = () => {
    const[chartData,setChartData]=useState({});
    const serviceURLHost="http://localhost:8089";
    // test=
    const chart=()=>{
        setChartData({
            labels:[],
            datasets:[]

        })
    }
    useEffect(()=>{
        fetch(`${serviceURLHost}/Elotz-home/graph/daily`).then((response) => {
            return response.json();
          })
          .then((myJson) => {
            // var string =JSON.stringify(myJson);
            // console.log(string);
            // string=string.replace(/"(\w+)"\s*:/g, '$1:');
            // console.log(string);
            // setData(string);
           let data=chartData;
           let max=0;
           let i=1;
           let labels=[];
           myJson.forEach(element => {
               let size=element.data.length;
            max=max>size?max:size;
            let limit=max-i;
            for(let k=0;k<=limit;k++)
             labels.push(i++);  
           });
           data.labels=labels;
           data.datasets=myJson;
           setChartData(data);
          });
        chart()
    },[])
    return (<div style={{height:"600px",width:"500px"}}>
        <div>
            <Line data={chartData} options={{
                responsive:true
            }} 
            />
        </div>
    </div>);
}
 
export default Test;
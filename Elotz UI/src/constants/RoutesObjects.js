import Dashboard from "../components/Dashboard/Dashboard"
import Daily from "../components/DailyUpdate/DailyUpdate"
import Monthly from "../components/MonthlyUpdate/MonthlyUpdate"
// import Weekly from "../components/WeeklyUpdate/WeeklyUpdate"
// import Yearly from "../components/YearlyUpdate/YearlyUpate";
 import DailyView from "../components/DailyUpdate/DailyView";
import MonthlyView from "../components/MonthlyUpdate/MonthlyView";
import AllView from "../components/AllView/AllView"
// import CustomCalender from "../components/MonthlyUpdate/CustomCalender";
export let dashboardRoutes = [
    {
      path: "/dashboard",
      name: "Dashboard",
      component: Dashboard
    },
    {
        path: "/daily-update",
        name: "Daily-Update",
        component: Daily
      },
      // {
      //   path: "/weekly-update",
      //   name: "Weekly",
      //   component: Weekly
      // },
      {
        path: "/monthly-update",
        name: "All-Update",
        component: Monthly
      },
      // {
      //   path: "/yearly-update",
      //   name: "Yearly",
      //   component: Yearly
      // }
      {
        path: "/daily-view",
        name: "Current Day-View",
        component: DailyView
      }
      ,{
        path: "/monthly-view",
        name: "Current Month-View",
        component: MonthlyView
      },
      {
        path: "/all-view",
        name: "All-View",
        component: AllView
      },
      // {
      //   path: "/test",
      //   name: "Test",
      //   component: CustomCalender
      // }
];
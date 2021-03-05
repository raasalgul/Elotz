import Dashboard from "../components/Dashboard/Dashboard"
import Daily from "../components/DailyUpdate/DailyUpdate"
import Monthly from "../components/MonthlyUpdate/MonthlyUpdate"
import Weekly from "../components/WeeklyUpdate/WeeklyUpdate"
import Yearly from "../components/YearlyUpdate/YearlyUpate";
 import DailyView from "../components/DailyUpdate/DailyView";
import MonthlyView from "../components/MonthlyUpdate/MonthlyView";
import CustomCalender from "../components/MonthlyUpdate/CustomCalender";
export let dashboardRoutes = [
    {
      path: "/dashboard",
      name: "Dashboard",
      component: Dashboard
    },
    {
        path: "/daily-update",
        name: "Daily",
        component: Daily
      },
      // {
      //   path: "/weekly-update",
      //   name: "Weekly",
      //   component: Weekly
      // },
      {
        path: "/monthly-update",
        name: "Monthly",
        component: Monthly
      },
      // {
      //   path: "/yearly-update",
      //   name: "Yearly",
      //   component: Yearly
      // }
      ,{
        path: "/daily-view",
        name: "Daily-View",
        component: DailyView
      }
      ,{
        path: "/monthly-view",
        name: "Monthly-View",
        component: MonthlyView
      },
      // {
      //   path: "/test",
      //   name: "Test",
      //   component: CustomCalender
      // }
];
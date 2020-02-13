import Dashboard from "../components/Dashboard/Dashboard"
import Daily from "../components/DailyUpdate/DailyUpdate"
import Montly from "../components/MonthlyUpdate/MonthlyUpdate"
import Weekly from "../components/WeeklyUpdate/WeeklyUpdate"
import Yearly from "../components/YearlyUpdate/YearlyUpate";
import DailyView from "../components/DailyUpdate/DailyView";
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
      },{
        path: "/weekly-update",
        name: "Weekly",
        component: Weekly
      },
      {
        path: "/monthly-update",
        name: "Montly",
        component: Montly
      },{
        path: "/yearly-update",
        name: "Yearly",
        component: Yearly
      }
      // ,{
      //   path: "/daily-view",
      //   name: "Daily-View",
      //   component: DailyView
      // }
];
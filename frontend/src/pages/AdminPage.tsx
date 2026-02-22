import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer } from 'recharts';
const data=[{name:'Mon',sales:1200},{name:'Tue',sales:2200},{name:'Wed',sales:1800}];
export const AdminPage = ()=> <div><h2 className='text-2xl font-bold mb-4'>Admin Dashboard</h2><div className='glass rounded-xl p-4 h-72'><ResponsiveContainer width='100%' height='100%'><BarChart data={data}><XAxis dataKey='name'/><YAxis/><Tooltip/><Bar dataKey='sales' fill='#7c3aed'/></BarChart></ResponsiveContainer></div></div>;

import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer } from 'recharts';
const data = [{ name: 'Mon', sales: 1200 }, { name: 'Tue', sales: 2200 }, { name: 'Wed', sales: 1800 }];
export const AdminPage = () => _jsxs("div", { children: [_jsx("h2", { className: 'text-2xl font-bold mb-4', children: "Admin Dashboard" }), _jsx("div", { className: 'glass rounded-xl p-4 h-72', children: _jsx(ResponsiveContainer, { width: '100%', height: '100%', children: _jsxs(BarChart, { data: data, children: [_jsx(XAxis, { dataKey: 'name' }), _jsx(YAxis, {}), _jsx(Tooltip, {}), _jsx(Bar, { dataKey: 'sales', fill: '#7c3aed' })] }) }) })] });

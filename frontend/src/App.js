import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import { Link, Navigate, Route, Routes } from 'react-router-dom';
import { HomePage } from './pages/HomePage';
import { SeatPage } from './pages/SeatPage';
import { AdminPage } from './pages/AdminPage';
import { StaffPage } from './pages/StaffPage';
import { useState } from 'react';
export const App = () => {
    const [dark, setDark] = useState(true);
    return _jsxs("div", { className: dark ? 'dark' : '', children: [_jsxs("nav", { className: 'glass sticky top-0 z-20 flex items-center justify-between px-4 py-3', children: [_jsx("div", { className: 'font-semibold tracking-wide', children: "\uD83C\uDFAC Cinema Luxe" }), _jsxs("div", { className: 'flex gap-4 text-sm', children: [_jsx(Link, { to: '/', children: "Home" }), _jsx(Link, { to: '/seats', children: "Seats" }), _jsx(Link, { to: '/admin', children: "Admin" }), _jsx(Link, { to: '/staff', children: "Staff" }), _jsx("button", { className: 'px-3 py-1 rounded bg-accent', onClick: () => setDark(v => !v), children: dark ? 'Dark' : 'Light' })] })] }), _jsx("main", { className: 'max-w-6xl mx-auto p-4', children: _jsxs(Routes, { children: [_jsx(Route, { path: '/', element: _jsx(HomePage, {}) }), _jsx(Route, { path: '/seats', element: _jsx(SeatPage, {}) }), _jsx(Route, { path: '/admin', element: _jsx(AdminPage, {}) }), _jsx(Route, { path: '/staff', element: _jsx(StaffPage, {}) }), _jsx(Route, { path: '*', element: _jsx(Navigate, { to: '/' }) })] }) })] });
};

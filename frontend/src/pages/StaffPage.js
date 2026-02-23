import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import { useState } from 'react';
import { api } from '../api/client';
export const StaffPage = () => {
    const [token, setToken] = useState('');
    const [msg, setMsg] = useState('');
    return _jsxs("div", { className: 'max-w-lg', children: [_jsx("h2", { className: 'text-2xl font-bold mb-3', children: "Ticket Validation" }), _jsx("input", { className: 'w-full bg-slate-800 p-2 rounded mb-3', value: token, onChange: e => setToken(e.target.value), placeholder: 'Enter QR token' }), _jsx("button", { className: 'px-3 py-2 bg-accent rounded', onClick: async () => { try {
                    const r = await api.post('/api/tickets/validate', { token });
                    setMsg(r.data.status);
                }
                catch {
                    setMsg('INVALID');
                } }, children: "Validate" }), _jsx("div", { className: 'mt-3', children: msg })] });
};

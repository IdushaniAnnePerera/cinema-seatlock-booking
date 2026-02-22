import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import { useState } from 'react';
const seats = Array.from({ length: 5 }, (_, r) => Array.from({ length: 8 }, (_, c) => `${String.fromCharCode(65 + r)}${c + 1}`));
export const SeatPage = () => {
    const [selected, setSelected] = useState([]);
    return _jsxs("div", { children: [_jsx("h2", { className: 'text-2xl font-bold mb-4', children: "Select Seats" }), _jsx("div", { className: 'space-y-2', children: seats.map(row => _jsx("div", { className: 'flex gap-2', children: row.map(s => {
                        const on = selected.includes(s);
                        return _jsx("button", { onClick: () => setSelected(v => on ? v.filter(x => x !== s) : [...v, s]), className: `w-10 h-10 rounded ${on ? 'bg-accent' : 'bg-slate-700'} hover:scale-105 transition`, children: s }, s);
                    }) }, row[0])) }), _jsxs("div", { className: 'mt-4 glass p-4 rounded', children: ["Selected: ", selected.join(', ') || 'None'] })] });
};

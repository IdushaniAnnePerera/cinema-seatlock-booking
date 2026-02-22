import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import { useQuery } from '@tanstack/react-query';
import { api } from '../api/client';
export const HomePage = () => {
    const { data } = useQuery({ queryKey: ['movies'], queryFn: async () => (await api.get('/api/public/movies?nowShowing=true')).data });
    return _jsxs("div", { children: [_jsx("h1", { className: 'text-3xl font-bold mb-4', children: "Now Showing" }), _jsx("div", { className: 'grid md:grid-cols-3 gap-4', children: (data || []).map((m) => _jsxs("div", { className: 'glass rounded-xl p-4', children: [_jsx("div", { className: 'font-semibold', children: m.title }), _jsx("div", { className: 'text-slate-400', children: m.language })] }, m.id)) })] });
};

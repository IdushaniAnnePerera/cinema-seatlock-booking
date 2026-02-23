import axios from 'axios';
export const api = axios.create({ baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8080' });
api.interceptors.request.use((config) => { const t=localStorage.getItem('accessToken'); if(t) config.headers.Authorization=`Bearer ${t}`; return config; });

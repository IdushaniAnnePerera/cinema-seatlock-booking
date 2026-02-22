import { useState } from 'react';
import { api } from '../api/client';
export const StaffPage = () => {
  const [token,setToken]=useState(''); const [msg,setMsg]=useState('');
  return <div className='max-w-lg'><h2 className='text-2xl font-bold mb-3'>Ticket Validation</h2><input className='w-full bg-slate-800 p-2 rounded mb-3' value={token} onChange={e=>setToken(e.target.value)} placeholder='Enter QR token'/><button className='px-3 py-2 bg-accent rounded' onClick={async()=>{try{const r=await api.post('/api/tickets/validate',{token});setMsg(r.data.status);}catch{setMsg('INVALID');}}}>Validate</button><div className='mt-3'>{msg}</div></div>;
};

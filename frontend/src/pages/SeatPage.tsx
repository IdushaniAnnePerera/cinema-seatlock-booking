import { useState } from 'react';
const seats = Array.from({length:5},(_,r)=>Array.from({length:8},(_,c)=>`${String.fromCharCode(65+r)}${c+1}`));
export const SeatPage = () => {
  const [selected,setSelected]=useState<string[]>([]);
  return <div><h2 className='text-2xl font-bold mb-4'>Select Seats</h2><div className='space-y-2'>{seats.map(row=><div className='flex gap-2' key={row[0]}>{row.map(s=>{
    const on=selected.includes(s);return <button key={s} onClick={()=>setSelected(v=>on?v.filter(x=>x!==s):[...v,s])} className={`w-10 h-10 rounded ${on?'bg-accent':'bg-slate-700'} hover:scale-105 transition`}>{s}</button>;
  })}</div>)}</div><div className='mt-4 glass p-4 rounded'>Selected: {selected.join(', ')||'None'}</div></div>;
};

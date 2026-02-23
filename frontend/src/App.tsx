import { Link, Navigate, Route, Routes } from 'react-router-dom';
import { HomePage } from './pages/HomePage';
import { SeatPage } from './pages/SeatPage';
import { AdminPage } from './pages/AdminPage';
import { StaffPage } from './pages/StaffPage';
import { useState } from 'react';

export const App = () => {
  const [dark, setDark] = useState(true);
  return <div className={dark ? 'dark' : ''}>
    <nav className='glass sticky top-0 z-20 flex items-center justify-between px-4 py-3'>
      <div className='font-semibold tracking-wide'>🎬 Cinema Luxe</div>
      <div className='flex gap-4 text-sm'><Link to='/'>Home</Link><Link to='/seats'>Seats</Link><Link to='/admin'>Admin</Link><Link to='/staff'>Staff</Link>
      <button className='px-3 py-1 rounded bg-accent' onClick={()=>setDark(v=>!v)}>{dark?'Dark':'Light'}</button></div>
    </nav>
    <main className='max-w-6xl mx-auto p-4'><Routes>
      <Route path='/' element={<HomePage/>}/><Route path='/seats' element={<SeatPage/>}/><Route path='/admin' element={<AdminPage/>}/><Route path='/staff' element={<StaffPage/>}/><Route path='*' element={<Navigate to='/'/>}/>
    </Routes></main>
  </div>;
};

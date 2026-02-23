import { useQuery } from '@tanstack/react-query';
import { api } from '../api/client';

export const HomePage = () => {
  const { data } = useQuery({ queryKey: ['movies'], queryFn: async()=> (await api.get('/api/public/movies?nowShowing=true')).data });
  return <div>
    <h1 className='text-3xl font-bold mb-4'>Now Showing</h1>
    <div className='grid md:grid-cols-3 gap-4'>{(data||[]).map((m:any)=><div className='glass rounded-xl p-4' key={m.id}><div className='font-semibold'>{m.title}</div><div className='text-slate-400'>{m.language}</div></div>)}</div>
  </div>;
};

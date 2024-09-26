import Sidebar from '@/components/Sidebar'
import Header from '@/components/Header';
import Head from '@/components/Head';
import { AgendamentoPaciente } from '@/components/AgendamentosBox/Agendamento';
import { AgendamentosBox } from '@/components/AgendamentosBox';
import { NextRequest, NextResponse } from 'next/server';
import { parseCookies } from 'nookies';
import { GetServerSideProps, GetServerSidePropsResult, NextPageContext } from 'next';
import { useEffect, useRef, useState } from 'react';
import { Toast } from 'primereact/toast';
import { Profissional } from '@/types/profissional';
import api from '@/services/nextApi';

const pacientes: AgendamentoPaciente[] = [
  { nomePaciente: "Paciente 1", horario: new Date(), status: "confirmado" },
  { nomePaciente: "Paciente 2", horario: new Date(), status: "confirmado" },
  { nomePaciente: "Paciente 3", horario: new Date(), status: "confirmado" },
  { nomePaciente: "Paciente 4", horario: new Date(), status: "confirmado" },
  { nomePaciente: "Paciente 5", horario: new Date(), status: "confirmado" }
]

export default function Home() {
  const [profissionais, setProfissionais] = useState<Profissional[]>([]);
  const toast = useRef<Toast>(null);

  
  useEffect(() => {
    listarProfissionais().then((profissionais) => {
      setProfissionais(profissionais)
    })
  }, [])

  // mostra todos os funcionarios
  async function listarProfissionais(): Promise<Profissional[] | any> {
    try {
      const response = await api.get("/profissional/listar")
      const profissionais = response.data

      return profissionais;
    } catch (e) {
      toast?.current?.show({ severity: 'error', summary: 'Erro', detail: 'Profissionais não encontrados', life: 3000 });
    }
  }

  return (
    <>
      <Head nomePagina='Página inicial' />
      <main className='container'>
        <Toast ref={toast} />
        <Sidebar />
        <div className="main">
          <Header titulo='Resumo de hoje' />
          {/* pacientes agendados, atendidos e que faltaram */}
          <div className='pacientes-dashboard flex justify-content-around mt-2'>
            <div className="flex flex-column align-items-center gap-2 text-gray-500">
              <div className='quantidade-pacientes'>9</div>
              <div>Pacientes agendados</div>
              <i className="pi pi-calendar text-2xl"></i>
            </div>
            <div className="flex flex-column align-items-center gap-2 text-green-500">
              <div className='quantidade-pacientes'>6</div>
              <div>Pacientes atendidos</div>
              <i className="pi pi-check text-2xl"></i>
            </div>
            <div className="flex flex-column align-items-center gap-2 text-red-500">
              <div className='quantidade-pacientes'>1</div>
              <div>Pacientes que faltaram</div>
              <i className="pi pi-times text-2xl"></i>
            </div>
          </div>
          {/* Agendamentos por funcionário */}
          <AgendamentosBox funcionarios={profissionais} pacientes={pacientes} />
        </div>
      </main>
    </>
  )
}

export async function getServerSideProps(ctx: NextPageContext) {
  const cookies = parseCookies(ctx);
  const { "ubs-access_token": token } = cookies as any;

  if (!token) {
    return {
      redirect: {
        permanent: false,
        destination: "/login",
      },
      props: {},
    }
  } else {
    return {
      props: {}
    }
  }
}

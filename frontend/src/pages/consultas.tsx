import Head from "@/components/Head";
import Header from "@/components/Header";
import Sidebar from "@/components/Sidebar";
import { Button } from "primereact/button";
import { ListBox, ListBoxChangeEvent } from "primereact/listbox"
import { Dropdown, DropdownChangeEvent } from 'primereact/dropdown';
import { Calendar, CalendarChangeEvent } from 'primereact/calendar';
import { useEffect, useRef, useState } from "react";
import { AgendamentoPaciente } from "@/components/AgendamentosBox/Agendamento";
import { AgendamentosBox } from "@/components/AgendamentosBox";
import api from "@/services/nextApi";
import { NextPageContext } from "next";
import { parseCookies } from "nookies";
import { Cliente } from "@/types/paciente";
import { Profissional } from "@/types/profissional"
import { Toast } from "primereact/toast";

const especialidades: string[] = [
    "especialidade 1",
    "especialidade 2",
    "especialidade 3",
    "especialidade 4",
    "especialidade 5",
]

const horarios: string[] = [
    "9:00", "10:00", "12:00", "14:30", "15:00", "15:30", "18:00", "20:00",
]

const pacientesAgendamentos: AgendamentoPaciente[] = [
    { nomePaciente: "Paciente 1", horario: new Date(), status: "confirmado" },
    { nomePaciente: "Paciente 2", horario: new Date(), status: "confirmado" },
    { nomePaciente: "Paciente 3", horario: new Date(), status: "confirmado" },
    { nomePaciente: "Paciente 4", horario: new Date(), status: "confirmado" },
    { nomePaciente: "Paciente 5", horario: new Date(), status: "confirmado" }
]

export default function Consultas() {
    const [paciente, setPaciente] = useState<Cliente>();
    const [pacientes, setPacientes] = useState<Cliente[]>([]);
    const [profissional, setProfissional] = useState<Profissional | any>();
    const [profissionais, setProfissionais] = useState<Profissional[]>([]);
    const [especialidade, setEspecialidade] = useState<string>();
    const [dataConsulta, setDataConsulta] = useState<string | Date | Date[]>([]);
    const [dataFiltroConsulta, setDataFiltroConsulta] = useState<string | Date | Date[]>([]);

    const toast = useRef<Toast>(null);
    const [time, setTime] = useState<Date | any>();

    // mostra todos os pacientes
    async function listarPacientes(): Promise<Cliente[]> {
        try {
            const response = await api.get("/cliente/listar")
            const pacientes = response.data
            return pacientes;
        } catch (e) {
            toast?.current?.show({ severity: 'error', summary: 'Erro', detail: 'Pacientes não encontrados', life: 3000 });
            return [];
        }
    }

    // mostra todos os funcionarios
    async function listarProfissionais(): Promise<Profissional[]> {
        try {
            const response = await api.get("/profissional/listar")
            const profissionais = response.data

            return profissionais;
        } catch (e) {
            toast?.current?.show({ severity: 'error', summary: 'Erro', detail: 'Profissionais não encontrados', life: 3000 });
            return [];
        }
    }

    useEffect(() => {
        listarPacientes().then((pacientes) => {
            setPacientes(pacientes)
        })
        listarProfissionais().then((profissionais) => {
            setProfissionais(profissionais)
        })
    }, [])

    return (
        <>
            <Toast ref={toast} />
            <Head nomePagina="Consultas" />
            <main className='container'>
                <Sidebar />
                <div className="main">
                    <Header titulo="Consultas" />
                    <div className="cadastro-consulta flex flex-column gap-2 mt-2 py-3 px-4">
                        <header className="p-2">
                            Nova Consulta
                        </header>
                        <div className="flex justify-content-around gap-4">
                            <div className="flex flex-column gap-4">
                                <ListBox
                                    filter
                                    value={paciente}
                                    onChange={(e: ListBoxChangeEvent) => { setPaciente(e.value) }}
                                    options={pacientes.length > 0 ? pacientes : []}
                                    optionLabel="nome"
                                    emptyFilterMessage="Paciente não encontrado"
                                    emptyMessage="Nenhum paciente encontrado"
                                    listStyle={{ height: "250px" }}
                                    filterPlaceholder="Paciente"
                                />
                                <Button label="Novo" iconPos="right" icon="pi pi-user-plus" />
                            </div>
                            <div className="flex flex-column gap-4 text-gray-500">
                                <div>
                                    <div className="text-gray-500 font-bold mb-2">
                                        Profissional
                                    </div>
                                    <Dropdown
                                        value={profissional}
                                        onChange={(e: DropdownChangeEvent) => setProfissional(e.value)}
                                        options={profissionais.length > 0 ? profissionais : []}
                                        optionLabel="nome"
                                        emptyFilterMessage="Profissional não encontrado"
                                        emptyMessage="Nenhum profissional encontrado"
                                        placeholder="Profissional"
                                        className="w-full"
                                    />
                                </div>
                                <div>
                                    <div className="text-gray-500 font-bold mb-2">
                                        Especialidade
                                    </div>
                                    <Dropdown
                                        value={especialidade}
                                        onChange={(e: DropdownChangeEvent) => setEspecialidade(e.value)}
                                        options={especialidades}
                                        placeholder="Especialidade"
                                        className="w-full"
                                    />
                                </div>
                                <div>
                                    <div className="text-gray-500 font-bold mb-2">
                                        Marcar Data
                                    </div>
                                    <Calendar
                                        value={dataConsulta}
                                        onChange={(e: CalendarChangeEvent) => setDataConsulta(e.value as any)}
                                        dateFormat="dd/mm/yy"
                                        placeholder="Data"
                                        className="w-full"
                                        showIcon
                                    />
                                </div>
                                <div>
                                    <div className="text-gray-500 font-bold mb-2">
                                        Marcar Horário
                                    </div>
                                    <Calendar
                                        value={time}
                                        onChange={(e) => setTime(e.value)}
                                        className="w-full"
                                        icon="pi pi-clock"
                                        timeOnly
                                        showIcon
                                    />
                                </div>
                            </div>
                            <div className="flex flex-column gap-4 justify-content-between">
                                <div>
                                    <div className="text-gray-500 font-bold mb-4">
                                        Horários Indisponíveis
                                    </div>
                                    <div className="grid gap-2 horarios mb-4">
                                        {horarios.map((horario, index) => (
                                            <Button
                                                className="col-6 bg-red-500 text-white"
                                                disabled
                                                key={index}
                                                label={horario}
                                            />
                                        ))}
                                    </div>
                                </div>
                                <div className="flex justify-content-end align-items-end">
                                    <span className="text-gray-500 underline mr-2">Limpar</span>
                                    <Button
                                        className="confirm-form"
                                        label="Marcar"
                                        icon="pi pi-check"
                                        iconPos="right"
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
                    <AgendamentosBox
                        pacientes={pacientesAgendamentos}
                        funcionarios={profissionais}
                        onChangeDateFilter={(e: CalendarChangeEvent) => { setDataFiltroConsulta(e.target as any); }}
                        modoEditor
                    />
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
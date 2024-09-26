import { Profissional } from "@/types/profissional";
import { useEffect } from "react";

interface AgendamentoProps {
    funcionario: Profissional;
    pacientes: AgendamentoPaciente[];
    modoEditor?: boolean;
}

export type AgendamentoPaciente = {
    nomePaciente: string;
    horario: Date;
    status: string;
}

export function Agendamento({ funcionario, pacientes, modoEditor }: AgendamentoProps) {
    return (
        <div className='agendamento'>
            <div className="text-blue-800 font-bold text-center">{funcionario?.nome}</div>
            <div className="flex flex-column mt-2">
                {pacientes.map(({ nomePaciente, horario, status }) => (
                    <div key={nomePaciente} className="flex align-items-center justify-content-between my-2">
                        <div className="flex flex-column gap-1">
                            <span className='text-blue-800 font-bold'>{`20:00`}</span>
                            <span className='text-gray-500'>{nomePaciente}</span>
                        </div>
                        {
                            modoEditor ?
                                (
                                    <div className="flex gap-2">
                                        <i className="pi pi-file-edit text-yellow-500"></i>
                                        <i className = "pi pi-calendar-times text-red-500"></i>
                                    </div>
                                )
                                :
                                (
                                    <i className="pi pi-calendar text-gray-500"></i>
                                )
                        }
                    </div>
                ))}
            </div>
        </div >
    )
}
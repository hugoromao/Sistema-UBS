import { Calendar, CalendarChangeEvent } from "primereact/calendar";
import { Agendamento, AgendamentoPaciente } from "./Agendamento";
import { Profissional } from "@/types/profissional";
import { useEffect } from "react";

export type AgendamentosBox = {
    funcionarios: Profissional[];
    pacientes: AgendamentoPaciente[];

    // utilizados na página de consultas e com o modo editor ativo
    modoEditor?: boolean;
    // função a ser executada quando a data de filtro for alterada
    onChangeDateFilter?: ((e: CalendarChangeEvent) => void);
}

export function AgendamentosBox({ funcionarios, pacientes, modoEditor, onChangeDateFilter }: AgendamentosBox) {
    return (
        <div className="agendamentos-box mt-4">
            {
                modoEditor ?
                    (
                        <div className="text-gray-500">
                            <span className="mr-4">Data:</span>
                            <Calendar
                                onChange={onChangeDateFilter}
                                dateFormat="dd/mm/yy"
                                showIcon
                            />
                        </div>
                    )
                    :
                    (
                        <div className='text-gray-500'>Pacientes do dia</div>
                    )
            }
            <div className="agendamentos flex justify-content-around">
                {funcionarios.length > 0? funcionarios.map((funcionario, index) => (
                    <Agendamento key={index} funcionario={funcionario} pacientes={pacientes} modoEditor={modoEditor} />
                )): (<></>)}
            </div>
            {
                modoEditor ? (
                    <div className="flex gap-4 ml-4">
                        <div className="flex gap-2 text-yellow-500">
                            <i className="pi pi-file-edit"></i>
                            <div>Editar Consulta</div>
                        </div>
                        <div className="flex gap-2 text-red-500">
                            <i className="pi pi-calendar-times"></i>
                            <div>Cancelar Consulta</div>
                        </div>
                    </div>
                ) : (
                    <div className="flex gap-4 ml-4">
                        <div className="flex gap-2 text-gray-500">
                            <i className="pi pi-calendar"></i>
                            <div>Consultas Pendentes</div>
                        </div>
                        <div className="flex gap-2 text-green-500">
                            <i className="pi pi-check"></i>
                            <div>Consultas Realizadas</div>
                        </div>
                        <div className="flex gap-2 text-red-500">
                            <i className="pi pi-times"></i>
                            <div>Consultas Canceladas</div>
                        </div>
                    </div>
                )
            }
        </div>
    )
}
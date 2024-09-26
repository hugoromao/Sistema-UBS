import { InputText } from "primereact/inputtext";
import { DataView } from "primereact/dataview"
import { useEffect, useState } from "react";
import { Cliente } from "@/types/paciente";

type ListaPacientesProps = {
    pacientes: Cliente[];
    mostraCard: Function;
    mostrandoCard: boolean;
    setMostrandoCard: Function;
    pacienteCard: Cliente | any;
}

export function ListaPacientes({ pacientes, mostraCard, mostrandoCard, setMostrandoCard, pacienteCard }: ListaPacientesProps) {

    function PacienteItem({ nome, id }: Cliente) {
        const [botaoCard, setBotaoCard] = useState<boolean>(false);

        useEffect(() => {
            if(mostrandoCard && pacienteCard?.id != id) {
                setBotaoCard(false)
            };
            
            if(mostrandoCard && pacienteCard?.id == id) {
                setBotaoCard(true)
            };
        }, [id])

        return (
            <div className="flex w-full justify-content-between align-items-center p-2" >
                <span>{nome}</span>
                <div className="flex gap-2">
                    <i className="pi pi-user-edit text-yellow-500"></i>
                    <i className="pi pi-file text-blue-500"></i>
                    <i
                        onClick={() => {
                            if(!botaoCard){
                                mostraCard(id);
                                setMostrandoCard(true)
                                setBotaoCard(true)
                            } else {
                                setMostrandoCard(false)
                                setBotaoCard(false)
                            }
                        }}
                        className={botaoCard? "pi pi-eye-slash text-green-500": "pi pi-eye text-green-500"}></i>
                </div>
            </div>
        )
    }

    return (
        <div className="lista-pacientes p-2 gap-4">
            <header>Buscar</header>
            <span className="p-input-icon-right">
                <i className="pi pi-search" />
                <InputText placeholder="Nome" />
            </span>
            <div>
                <div className="flex justify-content-around px-2">
                    <span>Nome</span>
                    <span>Opções</span>
                </div>
                <DataView value={pacientes} itemTemplate={PacienteItem} style={{overflowY: "scroll", height: "250px"}} />
            </div>
            <div className="flex gap-4 ml-2">
                <div className="flex gap-2 text-yellow-500">
                    <i className="pi pi-user-edit"></i>
                    <div>Editar Paciente</div>
                </div>
                <div className="flex gap-2 text-blue-500">
                    <i className="pi pi-file"></i>
                    <div>Emitir prontuário em branco</div>
                </div>
                <div className="flex gap-2 text-green-500">
                    <i className="pi pi-eye"></i>
                    <div>Ver paciente</div>
                </div>
            </div>
        </div>
    )
}
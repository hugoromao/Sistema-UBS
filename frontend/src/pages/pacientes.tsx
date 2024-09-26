import Head from "@/components/Head";
import Header from "@/components/Header";
import { ListaPacientes } from "@/components/ListaPacientes";
import Sidebar from "@/components/Sidebar";
import { InputText } from 'primereact/inputtext';
import { Dropdown, DropdownChangeEvent } from 'primereact/dropdown';
import { Button } from "primereact/button";
import { CardPaciente } from "@/components/CardPaciente";
import { NextPageContext } from "next";
import { parseCookies } from "nookies";
import api from "@/services/nextApi";
import { Cliente } from "@/types/paciente";
import { useEffect, useRef, useState } from "react";
import { useForm, Controller } from "react-hook-form";
import { Toast } from "primereact/toast";
import { ClienteTipo } from "@/types/cliente-tipo";

const racas: string[] = ["Preto", "Pardo", "Amarelo", "Branco"]
const sexos: string[] = ["Masculino", "Feminino"]
const estadosCivis: string[] = ["Solteiro", "Casado", "Divorciado"]
const niveisEnsino: string[] = ["Ensino Fundamental", "Ensino Médio", "Ensino Superior"]

export default function Pacientes() {
    const [pacientes, setPacientes] = useState<Cliente[]>([])
    const [pacienteInfo, setPacienteInfo] = useState<Cliente>();
    const { register, handleSubmit, control, formState, reset } = useForm()
    const [raca, setRaca] = useState();
    const [sexo, setSexo] = useState();
    const [estadoCivil, setEstadoCivil] = useState();
    const [escolaridade, setEscolaridade] = useState();
    const [tiposPaciente, setTiposPaciente] = useState<string[]>([]);
    const [tipoPaciente, setTipoPaciente] = useState<string>();

    const toast = useRef<Toast>(null)
    const [mostrandoCard, setMostrandoCard] = useState<boolean>(false)

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

    async function listarTiposPaciente(): Promise<ClienteTipo[]> {
        try {
            const response = await api.get("/cliente/tipo/listar")
            const tiposCliente = response.data.tiposCliente

            return tiposCliente;
        } catch (e) {
            toast?.current?.show({ severity: 'error', summary: 'Erro', detail: 'Tipos de paciente não encontrados', life: 3000 });
            return [];
        }
    }

    // mostra ou oculta card de paciente
    // Obs: função chamada dentro do componente de listar pacientes
    async function mostraCard(id: number) {
        try {
            const response = await api.get("/cliente/" + id)
            const paciente = response.data;

            setPacienteInfo(paciente)
        } catch (e) {
            toast.current?.show({ severity: 'error', summary: 'Erro', detail: 'Paciente não encontrado', life: 3000 });
        }
    }

    useEffect(() => {
        listarPacientes().then(pacientes => setPacientes(pacientes))
        listarTiposPaciente()
            .then((tiposCliente) => setTiposPaciente(tiposCliente.reduce((prev, curr) => prev.concat(curr.nome), [] as string[])))
    }, [])

    const onSubmit = (data: any) => {
        console.log(data)
    }

    return (
        <>
            <Toast ref={toast} />
            <Head nomePagina="Pacientes" />
            <main className='container'>
                <Sidebar />
                <div className="main">
                    <Header titulo="Pacientes" />
                    <div className="w-full flex justify-content-between mt-2 mb-4">
                        <ListaPacientes
                            pacientes={pacientes.length > 0? pacientes: []}
                            mostraCard={mostraCard}
                            pacienteCard={pacienteInfo}
                            mostrandoCard={mostrandoCard}
                            setMostrandoCard={setMostrandoCard}
                        />
                        <form className="cadastro-paciente p-2 gap-2" onSubmit={handleSubmit(onSubmit)}>
                            <header className="mb-2">Novo Paciente</header>
                            <div className="flex gap-2">
                                <InputText
                                    type="text"
                                    placeholder="Nome Completo*"
                                    className="w-full"
                                    {...register("nome")}
                                />
                                <Dropdown
                                    value={sexo}
                                    placeholder="Genero"
                                    options={sexos}
                                    {...register("genero")}
                                    onChange={(e: DropdownChangeEvent) => setSexo(e.target.value)}
                                />
                            </div>
                            <div className="flex gap-2">
                                <Dropdown
                                    placeholder="Raça"
                                    value={raca}
                                    options={racas}
                                    {...register("corPeleOuRaca")}
                                    onChange={(e: DropdownChangeEvent) => setRaca(e.target.value)}
                                />
                                <InputText
                                    type="text"
                                    placeholder="Naturalidade"
                                    {...register("naturalidade")} />
                                <InputText
                                    type="text"
                                    placeholder="Nascimento"
                                    {...register("dataAniversario")} />
                            </div>
                            <div className="flex gap-2">
                                <InputText
                                    type="text"
                                    placeholder="Profissão"
                                    {...register("profissao")} />
                                <Dropdown
                                    placeholder="Estado Civil"
                                    options={estadosCivis}
                                    value={estadoCivil}
                                    {...register("estadoCivil")}
                                    onChange={(e: DropdownChangeEvent) => setEstadoCivil(e.target.value)}
                                />
                                <Dropdown
                                    placeholder="Escolaridade"
                                    value={escolaridade}
                                    options={niveisEnsino}
                                    {...register("escolaridade")}
                                    onChange={(e: DropdownChangeEvent) => setEscolaridade(e.target.value)}
                                />
                            </div>
                            <div className="flex gap-2 border-bottom-1 pb-2 border-gray-400">
                                <InputText
                                    type="text"
                                    placeholder="Telefone 1"
                                    {...register("telefone")} />
                                <InputText
                                    type="text"
                                    placeholder="Telefone 2"
                                    {...register("telefone2")}
                                />
                            </div>
                            <div className="flex gap-2">
                                <InputText
                                    type="text"
                                    placeholder="Endereço"
                                    className="w-full"
                                    {...register("endereco")} />
                                <InputText
                                    type="text"
                                    placeholder="UF"
                                    {...register("uf")} />
                            </div>
                            <div className="flex gap-2">
                                <InputText
                                    type="text"
                                    placeholder="Bairro"
                                    {...register("bairro")} />
                                <InputText
                                    type="text"
                                    placeholder="Município"
                                    {...register("municipio")} />
                            </div>
                            <Dropdown
                                placeholder="Vínculo"
                                options={tiposPaciente.length > 0? tiposPaciente: []}
                                value={tipoPaciente}
                                {...register("tipoCliente")}
                                onChange={(e: DropdownChangeEvent) => setTipoPaciente(e.target.value)}
                            />
                            <div className="flex justify-content-end align-items-end mt-2">
                                <span className="text-gray-500 underline mr-2">Limpar</span>
                                <Button
                                    type="submit"
                                    className="confirm-form"
                                    label="Adicionar"
                                    icon="pi pi-check"
                                    iconPos="right"
                                />
                            </div>
                        </form>
                    </div>
                    {mostrandoCard ? (<CardPaciente paciente={pacienteInfo} />) : ""}
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
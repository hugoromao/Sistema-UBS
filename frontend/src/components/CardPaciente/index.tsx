import { Cliente } from "@/types/paciente"


type CardPacienteProps = {
    paciente?: Cliente
}

export function CardPaciente({ paciente }: CardPacienteProps) {
    const diaNascimento = paciente?.dataNascimento.split("-")[2]
    const mesNascimento = paciente?.dataNascimento.split("-")[1]
    const anoNascimento = paciente?.dataNascimento.split("-")[0]

    return (
        <div className="card-paciente flex flex-column gap-4 py-4 px-2">
            <header>{paciente?.nome}</header>
            <div className="flex gap-4 w-full">
                <div className="card-paciente-lado flex gap-8 pr-4 border-right-1 border-gray-400">
                    <div className="flex flex-column gap-4">
                        <span><strong>Raça: </strong>{paciente?.corRaca.toString().toLowerCase()}</span>
                        <span><strong>Naturalidade: </strong>{mostraCamposNaoDefinidos(paciente?.naturalidade)}</span>
                        <span><strong>Nascimento: </strong>{`${diaNascimento}/${mesNascimento}/${anoNascimento}`}</span>
                        <span><strong>Escolaridade: </strong>{mostraCamposNaoDefinidos(paciente?.escolaridade)}</span>
                        <span><strong>Profissão: </strong>{mostraCamposNaoDefinidos(paciente?.profissao)}</span>
                        <span><strong>Vínculo: </strong>{mostraCamposNaoDefinidos(paciente?.tipoId)}</span>
                    </div>
                    <div className="flex flex-column gap-4">
                        <span><strong>Sexo: </strong>{paciente?.genero.toString().toLowerCase()}</span>
                        <span><strong>Estado Civil: </strong>{mostraCamposNaoDefinidos(paciente?.estadoCivil)}</span>
                        <span><strong>Contato: </strong>{mostraCamposNaoDefinidos(paciente?.telefone)}</span>
                        <span><strong>UF: </strong>{mostraCamposNaoDefinidos(paciente?.naturalidade)}</span>
                        <span><strong>Cidade: </strong>{mostraCamposNaoDefinidos(paciente?.naturalidade)}</span>
                        <span><strong>Endereço: </strong>{mostraCamposNaoDefinidos(paciente?.naturalidade)}</span>
                    </div>
                </div>
                <div className="card-paciente-lado">
                    <strong>Últimas Consultorias</strong>
                </div>
            </div>
        </div>
    )
}

function mostraCamposNaoDefinidos(campo: string | any){
    if(campo) return campo;
    else return "Não definido"
}
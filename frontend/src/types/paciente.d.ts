export interface Cliente {
    id: number;
    tipoId: number;
    ativo: boolean;
    nome: string;
    cpf: string;
    naturalidade: string;
    nomeMae: string;
    nomePai: string;
    nomeSocial: string;
    profissao: string;
    telefone: string;
    corRaca: number;
    escolaridade: number;
    estadoCivil: number;
    genero: number;
    dataNascimento: string;
    createdAt: Date;
    updatedAt: Date;
}
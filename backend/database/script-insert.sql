select * from public.usuario;

select * from public.permissao;

select * from public.usuario_permissao;

select p.*
from public.usuario_permissao
         inner join public.permissao p
                    on p.id = usuario_permissao.permissao_id
         inner join public.usuario u
                    on u.id = usuario_permissao.usuario_id
where u.id = 1;


delete from public.usuario where id = 1;

-- Inserções Padrões

INSERT INTO public.cliente_tipo (id, nome)
VALUES
    (1, 'Servidor'),
    (2, 'Dependente / Servidor'),
    (3, 'Tercerizado'),
    (4, 'Dependente / Tercerizado'),
    (5, 'Bolsista SIAPE'),
    (6, 'Comunidade'),
    (7, 'Discente');

INSERT INTO public.conta_status (id, nome)
VALUES
    (1, 'Ativo'),
    (2, 'Inativo'),
    (3, 'Pendente');

INSERT INTO public.conselho (id, nome)
VALUES
    (1, 'CRM'),
    (2, 'CRP'),
    (3, 'CRO'),
    (4, 'CRN'),
    (5, 'COREN'),
    (6, 'CRESS');

INSERT INTO public.especialidades (id, nome)
VALUES
    (1, 'Pediatria'),
    (2, 'Clínico Geral'),
    (3, 'Serviço Social'),
    (4, 'Nutrição'),
    (5, 'Psicologia'),
    (6, 'Odotonlogia'),
    (7, 'Enfermagem');

INSERT INTO public.consulta_status (id, nome)
VALUES
    (1, 'Agendado'),
    (2, 'Concluído'),
    (3, 'Cancelado');

INSERT INTO public.permissao (id, nome)
VALUES
    (1, 'USER'),
    (2, 'ADMIN');

insert into public.usuario (dtype, id, created_at, foto, login, nome, senha, updated_at, conselho_estado,
                     numero_registro)
values ('', 1, now(), null, 'llbessa', 'Lucas Bessa Façanha Pereira', '12345', null, null, null);

insert into public.usuario (dtype, id, created_at, foto, login, nome, senha, updated_at, conselho_estado,
                     numero_registro)
values ('', 2, now(), null, 'osouza', 'Ozéias Silva Souza', '12345', null, null, null);

insert into public.usuario_permissao (usuario_id, permissao_id)
values (2, 2);

insert into public.usuario_permissao (usuario_id, permissao_id)
values (1, 1);

insert into public.usuario_permissao (usuario_id, permissao_id)
values (1, 2);
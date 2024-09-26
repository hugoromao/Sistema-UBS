-- noinspection SqlNoDataSourceInspectionForFile

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

insert into public.usuario ( id, created_at, foto, login, nome, senha, updated_at, status_id)
values (1, now(), null, 'admin', 'Administrador', 'adminubs123', now(), 1);

insert into public.usuario_permissao (usuario_id, permissao_id)
values (1, 2);
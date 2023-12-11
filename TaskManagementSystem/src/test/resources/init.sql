delete from user_task_roles;
delete from task;
delete from role;
delete from user_task;
INSERT INTO role (id, name) VALUES
    (4, 'test role');

INSERT INTO user_task (id, name, password, username) VALUES
    (4, 'test user', '444444', 'ina@mail.ru');

INSERT INTO user_task_roles (users_id, roles_id) VALUES
    (4, 4);

INSERT INTO task (id, `description`, `heading`, `priority`, `status`, `author_id`, `performer_id`) VALUES (100, 'test', 'test head', 'LOW', 'INPROGRESS', '4', '4');

INSERT INTO task (id, `description`, `heading`, `priority`, `status`, `author_id`, `performer_id`) VALUES (101, 'test 1', 'test head', 'LOW', 'INPROGRESS', '4', '4');



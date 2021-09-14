INSERT INTO authority (`id`, `name`) VALUES ('1', 'USER');
INSERT INTO authority (`id`, `name`) VALUES ('2', 'AGENT');
INSERT INTO authority (`id`, `name`) VALUES ('3', 'ADMIN');
INSERT INTO user (id, password, username) VALUES ('1001', '$2a$10$O7d79O.z0PffdbTP6NU/oOJiJnhuI7cLQYQWVs6YU8xG0AEFwf3bC', 'Admin');
INSERT INTO user_authority_list (user_id, authority_list_id) VALUES ('1001', '3');

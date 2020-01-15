insert into user (email, password)
values ('user1@example.com', '$2a$10$aQmAAVVEOw3Z/YFB92YwD.XkYnl2KBnzU86jaM7Qc/zayKjGz4BbK'),
       ('user2@example.com', '$2a$10$aQmAAVVEOw3Z/YFB92YwD.XkYnl2KBnzU86jaM7Qc/zayKjGz4BbK'),
       ('user3@example.com', '$2a$10$aQmAAVVEOw3Z/YFB92YwD.XkYnl2KBnzU86jaM7Qc/zayKjGz4BbK');

insert into refresh_token (token, user_id)
values ('abc', 1),
       ('def', 2),
       ('ghi', 3);

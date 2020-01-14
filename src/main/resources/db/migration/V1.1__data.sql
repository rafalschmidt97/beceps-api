insert into account (email, password)
values ('account1@example.com', '$2a$10$aQmAAVVEOw3Z/YFB92YwD.XkYnl2KBnzU86jaM7Qc/zayKjGz4BbK'),
       ('account2@example.com', '$2a$10$aQmAAVVEOw3Z/YFB92YwD.XkYnl2KBnzU86jaM7Qc/zayKjGz4BbK'),
       ('account3@example.com', '$2a$10$aQmAAVVEOw3Z/YFB92YwD.XkYnl2KBnzU86jaM7Qc/zayKjGz4BbK');

insert into refresh_token (expired_at, token, account_id)
values (current_timestamp(6) + interval 2 year, 'abc', 1),
       (current_timestamp(6) + interval 2 year, 'def', 2),
       (current_timestamp(6) + interval 2 year, 'ghi', 3);

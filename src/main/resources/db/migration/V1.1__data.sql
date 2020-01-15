insert into account (email, password)
values ('account1@example.com', '$2a$10$aQmAAVVEOw3Z/YFB92YwD.XkYnl2KBnzU86jaM7Qc/zayKjGz4BbK'),
       ('account2@example.com', '$2a$10$aQmAAVVEOw3Z/YFB92YwD.XkYnl2KBnzU86jaM7Qc/zayKjGz4BbK'),
       ('account3@example.com', '$2a$10$aQmAAVVEOw3Z/YFB92YwD.XkYnl2KBnzU86jaM7Qc/zayKjGz4BbK');

insert into refresh_token (token, account_id)
values ('abc', 1),
       ('def', 2),
       ('ghi', 3);

insert into user (email, password)
values ('user1@example.com', '$2a$10$aQmAAVVEOw3Z/YFB92YwD.XkYnl2KBnzU86jaM7Qc/zayKjGz4BbK'),
       ('user2@example.com', '$2a$10$aQmAAVVEOw3Z/YFB92YwD.XkYnl2KBnzU86jaM7Qc/zayKjGz4BbK'),
       ('user3@example.com', '$2a$10$aQmAAVVEOw3Z/YFB92YwD.XkYnl2KBnzU86jaM7Qc/zayKjGz4BbK');

insert into refresh_token (token, user_id)
values ('abc', 1),
       ('def', 2),
       ('ghi', 3);

insert into workout (`name`, user_id) values ('Workout 1', 1);

insert into routine (`name`, week_day, workout_id)
values ('FBW1', 1, 1),
       ('FBW2', 3, 1),
       ('FBW3', 5, 1);

insert into `set` (`name`, sets_amount, reps_amount, routine_id)
values ('Deadlift', 4, 10, 1),
       ('Dumbbell row', 4, 12, 1),
       ('Dumbbell press', 4, 10, 1),
       ('Military press', 4, 10, 1),
       ('Barbell bench', 3, 12, 1),
       ('Dumbbell lunge', 4, 10, 2),
       ('Pull-ups on a bar', 4, 10, 2),
       ('Barbell bench press', 4, 10, 2),
       ('Lateral dumbbell shrug', 4, 15, 2),
       ('Barbell biceps curl', 3, 12, 2),
       ('Barbell squats', 4, 10, 3),
       ('Bent over barbell row', 4, 10, 3),
       ('Incline barbell press', 4, 10, 3),
       ('Dumbbell overhead press', 4, 12, 3),
       ('Bench dips', 3, 10, 3);

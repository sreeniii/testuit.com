INSERT INTO users( username, 
PASSWORD , active, email ) 
VALUES (
'sreenvas',  '$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', 
TRUE ,  'sreenivaskanthamneni@gmail.com'
);

INSERT INTO user_role (user_id, ROLE)
VALUES (1, 'ROLE_USER');

INSERT INTO user_role (user_id, ROLE)
VALUES (1, 'ROLE_ADMIN');
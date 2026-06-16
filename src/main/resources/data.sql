INSERT INTO PEOPLE(id, name, email, dni)
VALUES ('1','Juan', 'juan@juan.com', '00000000T');

INSERT INTO PEOPLE(id, name, email, dni)
VALUES ('2','Ana', 'Ana@juan.com', '00000001R');

INSERT INTO PEOPLE(id, name, email, dni)
VALUES ('3','Pepito', 'pepito@juan.com', '000000013');

INSERT INTO PARENT_RELATIONS(CHILD_ID, PARENT_ID, TYPE) VALUES (3, 1, 'FATHER');

INSERT INTO PARENT_RELATIONS(CHILD_ID, PARENT_ID, TYPE) VALUES (3, 2, 'MOTHER');
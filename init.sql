CREATE TABLE my_table
(
    id         SERIAL PRIMARY KEY,
    plain      VARCHAR(1024),
    translated VARCHAR(1024)
);

-- Optional: ad smple data
INSERT INTO my_table (plain, translated)
VALUES ('John Doe', 'eoD nhoJ'),
       ('Jane Doe', 'eoD enaJ');

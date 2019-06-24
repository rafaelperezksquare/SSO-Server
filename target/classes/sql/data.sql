-- CLIENT DETAILS
-- secret = crmSuperSecret
INSERT INTO oauth_client_details 
   (client_id, client_secret, scope, authorized_grant_types, 
   authorities, access_token_validity, refresh_token_validity)
VALUES
   ('crmClient1', '$2a$10$MkZvo0p6zTdHzVu208arVex4MhRdVtaNEmHQGOjVnnfz1G6KBZqrq', 'read,write,trust', 'password,refresh_token', 
   'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 600, 2592000);

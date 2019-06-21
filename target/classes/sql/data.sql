-- CLIENT DETAILS
INSERT INTO oauth_client_details 
   (client_id, client_secret, scope, authorized_grant_types, 
   authorities, access_token_validity, refresh_token_validity)
VALUES
   ('crmClient1', 'crmSuperSecret', 'read,write,trust', 'password,refresh_token', 
   'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 7776000, 2592000),

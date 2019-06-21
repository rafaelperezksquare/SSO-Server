DROP DATABASE IF EXISTS sso_server_db;
CREATE DATABASE sso_server_db;
\c sso_server_db

DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
  ID SERIAL
, USERNAME VARCHAR(20) NOT NULL
, PASSWORD VARCHAR(200) NOT NULL
, CONSTRAINT PK_USERS_ID PRIMARY KEY (ID)
, CONSTRAINT UQ_USERNAME UNIQUE(USERNAME)
);

DROP TABLE IF EXISTS ROLE;
CREATE TABLE ROLE (
  ID SERIAL
, NAME VARCHAR(50)
, CONSTRAINT PK_ROLE_ID PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS USERS_ROLE;
CREATE TABLE USERS_ROLE(
  USER_ID INTEGER NOT NULL
, ROLE_ID INTEGER NOT NULL
, CONSTRAINT PK_USERS_ROLES PRIMARY KEY (USER_ID, ROLE_ID)
, CONSTRAINT FK_USERS_ID FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
, CONSTRAINT FK_ROLE_ID FOREIGN KEY (ROLE_ID) REFERENCES ROLE(ID)
);

-- OAuth2 users
DROP TABLE IF EXISTS oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

-- OAuth2 tokens
	
drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);
 
drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BYTEA,
  refresh_token VARCHAR(256)
);
 
drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication BYTEA
);
 
drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(256), authentication BYTEA
);
 
drop table if exists oauth_approvals;
create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);

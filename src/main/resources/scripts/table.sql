CREATE TABLE "LOANREFERRAL_DB"."public".MFI (
  ID 						VARCHAR(50) 		PRIMARY KEY,
	NAME 					VARCHAR (50) 		UNIQUE NOT NULL,
	PIC           VARCHAR(100),
	KEY           VARCHAR(50),
	TABORDER      VARCHAR(10),
	ENABLE 				BOOLEAN 				NOT NULL,
	CREATEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 		VARCHAR (50) 		NOT NULL,
	MODIFIEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE,
	MODIFIEDBY 		VARCHAR (50)
);

CREATE TABLE "LOANREFERRAL_DB"."public".LOANTYPE (
  ID 						VARCHAR(50) 		PRIMARY KEY,
	NAME 					VARCHAR (50) 		UNIQUE NOT NULL,
	NAMEKH        VARCHAR(50),
	DESCRIPTION   VARCHAR(200),
	MFIID         VARCHAR(200),
	TABORDER      VARCHAR(10),
	ENABLE 				BOOLEAN 				NOT NULL,
	CREATEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 		VARCHAR (50) 		NOT NULL,
	MODIFIEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE,
	MODIFIEDBY 		VARCHAR (50)
);

CREATE TABLE "LOANREFERRAL_DB"."public".ROLE (
	ID 						VARCHAR(50) 		PRIMARY KEY,
	NAME 					VARCHAR (50) 		UNIQUE NOT NULL,
	LEVEL         VARCHAR (50)    NOT NULL,
	OWNER         VARCHAR (50),
	MFIID         VARCHAR(50),
	BRANCHID      VARCHAR(50),
	RESPONSIBLE   VARCHAR(50),
	EFFECTIVEDATE TIMESTAMP       WITH TIME ZONE,
	EXPIRATIONDATE TIMESTAMP     WITH TIME ZONE,
	DESCRIPTION 	VARCHAR (1000),
	SETTINGID     VARCHAR(50),
	ENABLE 				BOOLEAN 				NOT NULL,
	CREATEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 		VARCHAR (50) 		NOT NULL,
	MODIFIEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE,
	MODIFIEDBY 		VARCHAR (50)
);

CREATE TABLE "LOANREFERRAL_DB"."public".SETTING (
  ID 						VARCHAR(50) 		PRIMARY KEY,
  ROLEID        VARCHAR(50)     NOT NULL,
	NAME 					VARCHAR (50) 		NOT NULL,
	VALUE         VARCHAR(100)    NOT NULL,
  ENABLE 				BOOLEAN 				NOT NULL,
	CREATEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 		VARCHAR (50) 		NOT NULL,
	MODIFIEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE,
	MODIFIEDBY 		VARCHAR (50)
);

-- DROP TABLE IF EXISTS "LOANREFERRAL_DB"."public"."PRIVILEGE";
CREATE TABLE "LOANREFERRAL_DB"."public".PRIVILEGE (
		ID 						VARCHAR(50) 		PRIMARY KEY,
	NAME 					VARCHAR (50) 		UNIQUE NOT NULL,
	DESCRIPTION 	VARCHAR (1000),
	ENABLE 				BOOLEAN 				NOT NULL,
	CREATEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 		VARCHAR (50) 		NOT NULL,
	MODIFIEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE,
	MODIFIEDBY 		VARCHAR (50)
);

-- DROP TABLE IF EXISTS "LOANREFERRAL_DB"."public"."ACCOUNT";
CREATE TABLE "LOANREFERRAL_DB"."public".ACCOUNT (
	ID 						VARCHAR(50) 		PRIMARY KEY,
	FIRSTNAME 		VARCHAR(100) 		NOT NULL,
	LASTNAME			VARCHAR(100) 		NOT NULL,
	USERNAME			VARCHAR(200) 		NOT NULL UNIQUE,
	EMAIL					VARCHAR(100),
	PASSWORD			VARCHAR(1000) 	NOT NULL,
	GENDER 				VARCHAR (10) 		NOT NULL,
	PHONE1				VARCHAR(20),
	PHONE2				VARCHAR(20),
	PHONE3				VARCHAR(20),
	VILLAGE							VARCHAR(50),
	COMMUNE							VARCHAR(50),
	DISTRICT						VARCHAR(50),
	PROVINCE						VARCHAR(50),
	DOB						TIMESTAMP				NOT NULL,
	NUMBEROFLOCK  INT            NOT NULL,
	ISLOCKED      BOOLEAN        NOT NULL,
	FIRSTLOGIN    BOOLEAN        NOT NULL,
	ENABLE 				BOOLEAN 				NOT NULL,
	CREATEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 		VARCHAR (50) 		NOT NULL,
	MODIFIEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE,
	MODIFIEDBY 		VARCHAR (50)
);

-- DROP TABLE IF EXISTS "LOANREFERRAL_DB"."public"."ACCOUNTROLE";
CREATE TABLE "LOANREFERRAL_DB"."public".ACCOUNTROLE (
  ID						VARCHAR(50) 		PRIMARY KEY,
	ACCOUNTID			VARCHAR(50)     REFERENCES	ACCOUNT(ID),
	ROLEID				VARCHAR(50)     REFERENCES	ROLE(ID),
	REPORTTO      VARCHAR(255)
);

-- DROP TABLE IF EXISTS "LOANREFERRAL_DB"."public"."ROLEPRIVILEGE";
CREATE TABLE "LOANREFERRAL_DB"."public".ROLEPRIVILEGE (
 ID						VARCHAR(50) 		PRIMARY KEY,
	ROLEID				VARCHAR(50)     REFERENCES	ROLE(ID),
	PRIVILEGEID		VARCHAR(50)     REFERENCES	PRIVILEGE(ID)
);

-- DROP TABLE IF EXISTS "LOANREFERRAL_DB"."public"."LOGMASTER";
CREATE TABLE "LOANREFERRAL_DB"."public".LOGMASTERS (
	ID 					 VARCHAR(50)		  PRIMARY KEY,
	RELATEDID    VARCHAR(50)     NOT NULL,
	FROMTABLE    VARCHAR(50)     NOT NULL,
	FROMDATAS    TEXT            NOT NULL,
	TODATAS      TEXT            NOT NULL,
	TYPE         VARCHAR(50)     NOT NULL,
	ENABLE 				BOOLEAN 				NOT NULL,
	CREATEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 		VARCHAR (50) 		NOT NULL,
	MODIFIEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE,
	MODIFIEDBY 		VARCHAR (50)
);

-- DROP TABLE IF EXISTS "LOANREFERRAL_DB"."public"."MAINADDRESS";
CREATE TABLE "LOANREFERRAL_DB"."public".MAINADDRESS (
	ID 					 VARCHAR(50)		  PRIMARY KEY,
	NAME          VARCHAR(100)    NOT NULL,
	NAMEKH        VARCHAR(200)    NOT NULL,
	TYPE          VARCHAR(50)     NOT NULL,
	ADDRESSCODE   VARCHAR(50)     NOT NULL,
	PARENTCODE    VARCHAR(50),
	REFERENCE     VARCHAR(100),
	ENABLE 				BOOLEAN 				NOT NULL,
	CREATEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 		VARCHAR (50) 		NOT NULL,
	MODIFIEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE,
	MODIFIEDBY 		VARCHAR (50)
);

-- DROP TABLE IF EXISTS "LOANREFERRAL_DB"."public"."CONFIGURE";
CREATE TABLE "LOANREFERRAL_DB"."public".CONFIGURE (
	ID						VARCHAR(50) 		PRIMARY KEY,
	NAME					VARCHAR(100)		NOT NULL UNIQUE,
	TYPE					VARCHAR(100)		NOT NULL,
	ITEM1         VARCHAR(100),
	ITEM2         VARCHAR(100),
	ENABLE 				BOOLEAN 				NOT NULL,
	CREATEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 		VARCHAR (50) 		NOT NULL,
	MODIFIEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE,
	MODIFIEDBY 		VARCHAR (50)
);

-- DROP TABLE IF EXISTS "LOANREFERRAL_DB"."public"."FRMLOAN";
CREATE TABLE "LOANREFERRAL_DB"."public".FRMLOAN (
  ID						      VARCHAR(50) 			PRIMARY KEY,
	CUSTNAME            VARCHAR(100)      NOT NULL,
	CUSTPHONE           VARCHAR(50)       NOT NULL,
	REQLOANAMOUNT       FLOAT             NOT NULL,
	REQCURRENCY         VARCHAR(50)       NOT NULL,
	LOANPERIOD          FLOAT             NOT NULL,
	REQMFI              VARCHAR(50)       NOT NULL,
	LOANTYPE						VARCHAR(50),
	OTHERLOANTYPE       VARCHAR(200),
	VILLAGE							VARCHAR(50)				NOT NULL,
	COMMUNE							VARCHAR(50)				NOT NULL,
	DISTRICT						VARCHAR(50)				NOT NULL,
	PROVINCE						VARCHAR(50)				NOT NULL,
	ASSIGNEDTOBRANCH    VARCHAR(50),
	ASSIGNEDTOPERSON    VARCHAR(50),
	PROCESSEDMFI        VARCHAR(50),
	LOANST         			VARCHAR(50),
	APPRAM  						FLOAT,
	ENABLE 							BOOLEAN 					NOT NULL,
	CREATEDDATE 				TIMESTAMP 				WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 					VARCHAR (50) 			NOT NULL,
	MODIFIEDDATE 				TIMESTAMP 				WITHOUT TIME ZONE,
	MODIFIEDBY 					VARCHAR (50)
);

-- DROP TABLE IF EXISTS "LOANREFERRAL_DB"."public"."BRANCH";
CREATE TABLE "LOANREFERRAL_DB"."public".BRANCH (
  ID						      VARCHAR(50) 			PRIMARY KEY,
	MFIID               VARCHAR(50)       NOT NULL,
	NAME                VARCHAR(100)      NOT NULL,
  CODE                VARCHAR(50),
	ENABLE 							BOOLEAN 					NOT NULL,
	CREATEDDATE 				TIMESTAMP 				WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 					VARCHAR (50) 			NOT NULL,
	MODIFIEDDATE 				TIMESTAMP 				WITHOUT TIME ZONE,
	MODIFIEDBY 					VARCHAR (50)
);

CREATE TABLE "LOANREFERRAL_DB"."public".TRANSTATUS (
  ID 						VARCHAR(50) 		PRIMARY KEY,
  FRMLOANID     VARCHAR(50)     NOT NULL,
	FROMST				VARCHAR(50)			NOT NULL,
	TOST					VARCHAR(50)			NOT NULL,
	COMMENT				TEXT,
  ENABLE 				BOOLEAN 				NOT NULL,
	CREATEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 		VARCHAR (50) 		NOT NULL,
	MODIFIEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE,
	MODIFIEDBY 		VARCHAR (50)
);

CREATE TABLE "LOANREFERRAL_DB"."public".NAVIGATION (
  ID 						VARCHAR(50) 		PRIMARY KEY,
  ROLEID        VARCHAR(50)     NOT NULL,
  KEY						VARCHAR(50),
	TITLE					VARCHAR(100),
	TRANSLATE			VARCHAR(100),
	TYPE					VARCHAR(50),
	ICON					VARCHAR(50),
	URL						VARCHAR(50),
	TABORDER      VARCHAR(20),
  ENABLE 				BOOLEAN 				NOT NULL,
	CREATEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 		VARCHAR (50) 		NOT NULL,
	MODIFIEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE,
	MODIFIEDBY 		VARCHAR (50)
);


CREATE TABLE "LOANREFERRAL_DB"."public".AUTOLOAN (
  ID 						VARCHAR(50) 		PRIMARY KEY,
  CUSTOMERNAME  VARCHAR(100)    NOT NULL,
	PHONE1				VARCHAR(20)			NOT NULL,
	PHONE2				VARCHAR(20),
	LOANAMOUNT		FLOAT						NOT NULL,
	LOANAMOUNTCURRENCY    VARCHAR(50)     NOT NULL,
	PRICE					FLOAT						NOT NULL,
	PRICECURRENCY VARCHAR(50)     NOT NULL,
	YEARMADE			VARCHAR(250)		NOT NULL,
	AVG_INCOME		FLOAT						NOT NULL,
	AVGINCOMECURRENCY     VARCHAR(50)     NOT NULL,
	PROVINCE			VARCHAR(50)			NOT NULL,
	DISTRICT			VARCHAR(50)			NOT NULL,
	COMMUNE				VARCHAR(50)			NOT NULL,
	VILLAGE				VARCHAR(50),
	STATUS				VARCHAR(50),
  ENABLE 				BOOLEAN 				NOT NULL,
	CREATEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 		VARCHAR (50) 		NOT NULL,
	MODIFIEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE,
	MODIFIEDBY 		VARCHAR (50)
);


CREATE TABLE "LOANREFERRAL_DB"."public".AUTOLOANDETAIL (
	ID						VARCHAR(50)			PRIMARY KEY,
	AUTOLOANID		VARCHAR(50)			NOT NULL,
	IMAGE_URL			VARCHAR(200)		NOT NULL,
	IMAGE_TYPE		VARCHAR(50)			NOT NULL,
	ENABLE 				BOOLEAN 				NOT NULL,
	CREATEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 		VARCHAR (50) 		NOT NULL,
	MODIFIEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE,
	MODIFIEDBY 		VARCHAR (50)
);

CREATE TABLE "LOANREFERRAL_DB"."public".ACCOUNTROLEAUTHORIZE (
	ID						VARCHAR(50)			PRIMARY KEY,
	ACCOUNTID		  VARCHAR(50)			NOT NULL,
	USERNAME			VARCHAR(100)		NOT NULL      UNIQUE ,
	ROLEID		    VARCHAR(50)			NOT NULL,
	ENABLE 				BOOLEAN 				NOT NULL,
	CREATEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE NOT NULL,
	CREATEDBY 		VARCHAR (50) 		NOT NULL,
	MODIFIEDDATE 	TIMESTAMP 			WITHOUT TIME ZONE,
	MODIFIEDBY 		VARCHAR (50)
);

drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token bytea,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token bytea,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication bytea,
  refresh_token VARCHAR(255)
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token bytea,
  authentication bytea
);

drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255), authentication bytea
);

drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);

drop table if exists ClientDetails;
create table ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);
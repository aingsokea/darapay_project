-- CREATE OAUTH CLIENT DETAILS
INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types,access_token_validity, refresh_token_validity,additional_information)
VALUES ('client','$2a$10$QnFqO4b0w2tRSZ5074MYA.8OqnoXnzbV1xtWDKm7qDt27nYTfXUdi','read,write','password,refresh_token',36000, 360000, '{}');

-- Create Role ADMIN
INSERT INTO role (id, name, level, owner, mfiid, responsible, effectivedate, expirationdate, description, usersetting, enable, createddate, createdby)
VALUES ('9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1', 'ROLE_ADMIN', 1, null, null, null, '2019-02-06', '2025-02-06', 'N/A', null ,true, NOW(), 'ADMIN');

-- CREATE USER ADMIN
INSERT INTO account (id, firstname, lastname, username, email, password,
gender, phone1, phone2, phone3, dob, numberoflock, islocked, firstlogin, enable, createddate, createdby)
VALUES ('49a2e3ae-4592-4e89-9458-605b03a8b0f5', 'Soueng', 'Kimmeng', 'KimmengSoueng', 'kimmeng.soueng@outlook.com', '$2a$10$MTFVrdqbHOi.CCUhkrkZnOBdrZEfk3gzIUyZBdQvLWvdF/0pnkEO2',
'MALE', '0966687666', null, null, NOW(), 0, false, true, true, NOW(), 'ADMIN');

-- CREATE ACCOUNT ROLE FOR ADMIN
INSERT INTO accountrole VALUES ('b60e3ec8-ead1-4933-964a-e1b810d20b1c', '49a2e3ae-4592-4e89-9458-605b03a8b0f5', '9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1');

-- CREATE PRIVILEGE FROM ACCOUNT
INSERT INTO privilege VALUES ('f826cbdd-660e-44b3-9f38-9ca6e73fb8c0', 'ACCOUNT_READ', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('47920423-9337-44f9-b71d-78302aeee459', 'ACCOUNT_CREATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('9a6a1ebf-50bf-49de-adb9-fe9153f4885b', 'ACCOUNT_UPDATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('5fe31b99-3925-4356-a97a-965d405503dc', 'ACCOUNT_ADVANCE_FILTER', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('ba99444b-4379-4499-975e-d826ce982ccc', 'ACCOUNT_ADVANCE_EXPORT', 'N/A', true, NOW(), 'ADMIN');

-- APPLY PRIVILEGE FROM ADMIN 9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1
INSERT INTO roleprivilege VALUES ('e844d1b3-d057-40e6-bea4-bd3ab22e96c4','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','f826cbdd-660e-44b3-9f38-9ca6e73fb8c0');
INSERT INTO roleprivilege VALUES ('2f3e3d00-00a1-49f7-933a-1acd9550d893','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','47920423-9337-44f9-b71d-78302aeee459');
INSERT INTO roleprivilege VALUES ('e32e296e-091b-4244-b8c7-2e3c7560d106','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','9a6a1ebf-50bf-49de-adb9-fe9153f4885b');
INSERT INTO roleprivilege VALUES ('9692e8e4-194c-4e49-a615-489c97e68e19','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','5fe31b99-3925-4356-a97a-965d405503dc');
INSERT INTO roleprivilege VALUES ('583c2875-fc61-4548-988c-9472c06c7309','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','ba99444b-4379-4499-975e-d826ce982ccc');

-- CREATE PRIVILEGE FROM PRIVILEGE
INSERT INTO privilege VALUES ('bbb4aeb1-1c4c-4afd-9b34-00e2a923a813', 'PRIVILEGE_READ', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('8d8a91bd-8984-4c08-8a92-b237f55bb72b', 'PRIVILEGE_CREATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('dc99d29d-537a-46b8-8e4e-329061f2561d', 'PRIVILEGE_UPDATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('5b824097-290f-4e35-a5b6-c58d2be216ae', 'PRIVILEGE_ADVANCE_FILTER', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('36a7986f-8ff9-44b2-9940-afc6872fd2cb', 'PRIVILEGE_ADVANCE_EXPORT', 'N/A', true, NOW(), 'ADMIN');

-- APPLY PRIVILEGE FROM ADMIN 9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1
INSERT INTO roleprivilege VALUES ('732fae52-7e53-4012-b9da-6d89d1a2dbfb','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','bbb4aeb1-1c4c-4afd-9b34-00e2a923a813');
INSERT INTO roleprivilege VALUES ('6e735793-f78e-48e2-a4a9-1696141eb2eb','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','8d8a91bd-8984-4c08-8a92-b237f55bb72b');
INSERT INTO roleprivilege VALUES ('90ba36d4-a883-40bf-a429-bb5155e521d5','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','dc99d29d-537a-46b8-8e4e-329061f2561d');
INSERT INTO roleprivilege VALUES ('406b6219-565a-4178-8af1-af554e3e2bb9','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','5b824097-290f-4e35-a5b6-c58d2be216ae');
INSERT INTO roleprivilege VALUES ('61a439ae-3117-4895-96b4-f125c177bdd0','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','36a7986f-8ff9-44b2-9940-afc6872fd2cb');

-- CREATE PRIVILEGE FROM ROLE
INSERT INTO privilege VALUES ('648388c5-b49f-459c-bbe6-455f103adc76', 'ROLE_READ', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('4dc222b0-b3c3-458d-bcc0-5815adc419f6', 'ROLE_CREATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('00ebd444-977a-465d-8c50-8f37cf5fc189', 'ROLE_UPDATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('2c1d629a-399e-4f2b-b4ba-0c792a00dff1', 'ROLE_ADVANCE_FILTER', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('87e7d2c1-9b96-4d27-a5e6-7cf87240ed4e', 'ROLE_ADVANCE_EXPORT', 'N/A', true, NOW(), 'ADMIN');

-- APPLY PRIVILEGE FROM ADMIN 9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1
INSERT INTO roleprivilege VALUES ('b456adfd-424c-4e13-9a16-81f0aaf6a052','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','648388c5-b49f-459c-bbe6-455f103adc76');
INSERT INTO roleprivilege VALUES ('bdda1449-a415-4f37-a887-f68717788248','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','4dc222b0-b3c3-458d-bcc0-5815adc419f6');
INSERT INTO roleprivilege VALUES ('c3f093d6-e7c3-4e1c-9f66-19432d0f75a1','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','00ebd444-977a-465d-8c50-8f37cf5fc189');
INSERT INTO roleprivilege VALUES ('59387fe5-2eec-4b3b-bb42-d3035c20186b','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','2c1d629a-399e-4f2b-b4ba-0c792a00dff1');
INSERT INTO roleprivilege VALUES ('5a09c322-ede1-4286-8c47-25341377c814','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','87e7d2c1-9b96-4d27-a5e6-7cf87240ed4e');

-- CREATE PRIVILEGE FROM ROLE PRIVILEGE
INSERT INTO privilege VALUES ('89ce8226-7894-4e5f-b8c2-cf6d803fd4e4', 'ROLEPRIVILEGE_READ', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('164a1bb9-74fa-4408-8660-78adc2723a90', 'ROLEPRIVILEGE_CREATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('1ae07945-32f2-48d9-985d-c00e11519c51', 'ROLEPRIVILEGE_UPDATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('994dc506-6c48-4541-b71f-89ff2c9545d3', 'ROLEPRIVILEGE_ADVANCE_FILTER', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('67159cbd-e873-4606-860c-b576ac0f4e13', 'ROLEPRIVILEGE_ADVANCE_EXPORT', 'N/A', true, NOW(), 'ADMIN');

-- APPLY ROLE PRIVILEGE FROM ADMIN 9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1
INSERT INTO roleprivilege VALUES ('515c8b5f-aabf-483e-850f-05596551c49c','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','89ce8226-7894-4e5f-b8c2-cf6d803fd4e4');
INSERT INTO roleprivilege VALUES ('9d28faf3-6086-487d-b13b-be3e8ba882ec','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','164a1bb9-74fa-4408-8660-78adc2723a90');
INSERT INTO roleprivilege VALUES ('06022b06-c302-46b5-ab24-c57177170022','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','1ae07945-32f2-48d9-985d-c00e11519c51');
INSERT INTO roleprivilege VALUES ('26b3c305-0a90-412b-a265-c788330aef46','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','994dc506-6c48-4541-b71f-89ff2c9545d3');
INSERT INTO roleprivilege VALUES ('ae766c6e-35d0-4716-9e69-eacca47a9cfc','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','67159cbd-e873-4606-860c-b576ac0f4e13');


-- CREATE PRIVILEGE FROM LOGMASTER
INSERT INTO privilege VALUES ('53b7822e-4f13-457c-8720-4aeb892ce42a', 'LOGMASTER_READ', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('7c7840c4-e844-4b61-aa77-68b02171124e', 'LOGMASTER_CREATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('93f1098f-10f7-41bf-b170-c29f453a1c4a', 'LOGMASTER_UPDATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('654a19a2-d0cf-4aba-b1ae-68680db12a4b', 'LOGMASTER_ADVANCE_FILTER', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('757dc1b1-02f7-4ae7-95d9-e1d9dbb66c39', 'LOGMASTER_ADVANCE_EXPORT', 'N/A', true, NOW(), 'ADMIN');

-- APPLY PRIVILEGE FROM ADMIN 9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1
INSERT INTO roleprivilege VALUES ('a9e8e329-86e5-44c2-9e8c-fb04e7222d86','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','53b7822e-4f13-457c-8720-4aeb892ce42a');
INSERT INTO roleprivilege VALUES ('223b9b13-0ab5-4367-a5b5-892a0852c126','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','7c7840c4-e844-4b61-aa77-68b02171124e');
INSERT INTO roleprivilege VALUES ('61a2ab91-50e0-4420-afdc-1cc4964c7d4b','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','93f1098f-10f7-41bf-b170-c29f453a1c4a');
INSERT INTO roleprivilege VALUES ('4ebe5a37-304b-40fe-9ec7-d424682ed697','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','654a19a2-d0cf-4aba-b1ae-68680db12a4b');
INSERT INTO roleprivilege VALUES ('43a2a9d3-cab5-4ada-b458-8f1fd5485121','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','757dc1b1-02f7-4ae7-95d9-e1d9dbb66c39');

-- CREATE PRIVILEGE FROM CONFIGURE
INSERT INTO privilege VALUES ('ec3e803c-2742-403d-a840-a4a1cbfc7bb9', 'CONFIGURE_READ', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('409b0dc4-2209-4eab-8445-ccf2d29dee9e', 'CONFIGURE_CREATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('10bdf661-e875-46ec-90ab-61eca69598f5', 'CONFIGURE_UPDATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('fbfd6b1d-4b82-4516-b83c-b86e502e5e6f', 'CONFIGURE_ADVANCE_FILTER', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('514cf4a8-6b61-42c3-9cc0-0c984cf6c2e7', 'CONFIGURE_ADVANCE_EXPORT', 'N/A', true, NOW(), 'ADMIN');

-- APPLY CONFIGURE FROM ADMIN 9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1
INSERT INTO roleprivilege VALUES ('29f37b34-8141-42be-a127-d28d066e3ef5','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','ec3e803c-2742-403d-a840-a4a1cbfc7bb9');
INSERT INTO roleprivilege VALUES ('ad1d182c-266e-4587-a803-19593e7f7deb','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','409b0dc4-2209-4eab-8445-ccf2d29dee9e');
INSERT INTO roleprivilege VALUES ('bd57bf45-7fd4-40af-a1ff-dd8b8bc839d0','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','10bdf661-e875-46ec-90ab-61eca69598f5');
INSERT INTO roleprivilege VALUES ('034ec05e-27c9-473e-8011-2811f51ee34c','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','fbfd6b1d-4b82-4516-b83c-b86e502e5e6f');
INSERT INTO roleprivilege VALUES ('b86bd58e-33df-438d-8c59-eb00e275b58d','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','514cf4a8-6b61-42c3-9cc0-0c984cf6c2e7');

-- CREATE PRIVILEGE FROM FRMLOAN
INSERT INTO privilege VALUES ('3b798b86-109c-415c-84b8-92633b661499', 'FRMLOAN_READ', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('4ffe7e8d-4443-4d32-a1ef-e5269eeadecf', 'FRMLOAN_CREATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('d7b06c35-bf58-4521-a9b6-e5c2a2bafed5', 'FRMLOAN_UPDATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('3db1e05f-6912-4bfe-9bb1-cca0fa813f03', 'FRMLOAN_ADVANCE_FILTER', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('186f41ba-91e5-432c-bfec-89ea5367d5e3', 'FRMLOAN_ADVANCE_EXPORT', 'N/A', true, NOW(), 'ADMIN');

-- APPLY FRMLOAN FROM ADMIN 9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1
INSERT INTO roleprivilege VALUES ('89d0a033-4b82-40fe-8efa-661931bbaf23','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','3b798b86-109c-415c-84b8-92633b661499');
INSERT INTO roleprivilege VALUES ('1607c1a1-2345-46e0-a498-2e875f947acb','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','4ffe7e8d-4443-4d32-a1ef-e5269eeadecf');
INSERT INTO roleprivilege VALUES ('7e4c64fc-d202-4c66-babc-b52003cd2341','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','d7b06c35-bf58-4521-a9b6-e5c2a2bafed5');
INSERT INTO roleprivilege VALUES ('a0488673-ea44-422b-a7a4-7c3d31070d56','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','3db1e05f-6912-4bfe-9bb1-cca0fa813f03');
INSERT INTO roleprivilege VALUES ('37daf829-e202-40a8-a717-0e280d1947a2','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','186f41ba-91e5-432c-bfec-89ea5367d5e3');


-- CREATE PRIVILEGE FROM LOANTYPE
INSERT INTO privilege VALUES ('b854fc98-ef7c-4f7b-b1d4-e859a9410ae8', 'LOANTYPE_READ', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('e3cd88d7-2cb4-409b-bf35-2358f4dcbe3e', 'LOANTYPE_CREATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('41ea56e0-bd3f-440d-81c7-5891fba068d3', 'LOANTYPE_UPDATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('8bf95882-6476-4fd0-ad66-4dee90aedfbf', 'LOANTYPE_ADVANCE_FILTER', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('b07a9ae5-325a-4d5f-b008-4a3cdc0dbb3a', 'LOANTYPE_ADVANCE_EXPORT', 'N/A', true, NOW(), 'ADMIN');

-- APPLY LOANTYPE FROM ADMIN 9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1
INSERT INTO roleprivilege VALUES ('73ee9def-29c9-4828-a6cc-b770b2ea5205','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','b854fc98-ef7c-4f7b-b1d4-e859a9410ae8');
INSERT INTO roleprivilege VALUES ('90be14f6-e7e1-4908-9e7f-fa5054e6a4a6','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','e3cd88d7-2cb4-409b-bf35-2358f4dcbe3e');
INSERT INTO roleprivilege VALUES ('e9f7b752-d96b-43d7-ba0d-222b927492ea','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','41ea56e0-bd3f-440d-81c7-5891fba068d3');
INSERT INTO roleprivilege VALUES ('17769db4-67a1-4bbe-9534-a5a2ae8b8be1','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','8bf95882-6476-4fd0-ad66-4dee90aedfbf');
INSERT INTO roleprivilege VALUES ('08e7e0f8-c348-41ce-a18d-1fbe7eed1d05','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','b07a9ae5-325a-4d5f-b008-4a3cdc0dbb3a');

-- CREATE PRIVILEGE FROM MFI
INSERT INTO privilege VALUES ('5a8317bb-91c3-4d88-99b5-5b6e506ee3c1', 'MFI_READ', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('5a370505-f7fd-4681-996e-967e45c6063d', 'MFI_CREATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('0eb2fa53-a54c-4882-8b1f-371f8e4c45fd', 'MFI_UPDATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('e22dfd7e-7a9f-4a8d-925b-005db9966800', 'MFI_ADVANCE_FILTER', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('9c5b9c6a-f8fa-476b-8370-c1df34bf3c55', 'MFI_ADVANCE_EXPORT', 'N/A', true, NOW(), 'ADMIN');

-- APPLY MFI FROM ADMIN 9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1
INSERT INTO roleprivilege VALUES ('6ec98ad7-6b78-4afd-a96a-bd41905dea57','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','5a8317bb-91c3-4d88-99b5-5b6e506ee3c1');
INSERT INTO roleprivilege VALUES ('4d556d70-45aa-4f8f-b151-8fa5eb92fde8','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','5a370505-f7fd-4681-996e-967e45c6063d');
INSERT INTO roleprivilege VALUES ('4b92111e-856a-4cb2-8393-36865013cbf3','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','0eb2fa53-a54c-4882-8b1f-371f8e4c45fd');
INSERT INTO roleprivilege VALUES ('563e74ec-988a-4638-af7e-a46eb1e1375e','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','e22dfd7e-7a9f-4a8d-925b-005db9966800');
INSERT INTO roleprivilege VALUES ('b957ba27-2eda-4960-9827-7ea8275a05b6','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','9c5b9c6a-f8fa-476b-8370-c1df34bf3c55');

-- CREATE PRIVILEGE FROM MAINADDRESS
INSERT INTO privilege VALUES ('bf7009dc-e7f1-46bc-93fb-97a6690e27af', 'MAINADDRESS_READ', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('ed5ceadd-fbad-4e84-a65a-ceb85440963a', 'MAINADDRESS_CREATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('e41427ea-0538-4541-a1cd-4044edfef1bf', 'MAINADDRESS_UPDATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('9307e339-be58-41ec-8b9b-1278b5759eac', 'MAINADDRESS_ADVANCE_FILTER', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('d3bb9d47-8b00-4778-9860-fab8952c53a0', 'MAINADDRESS_ADVANCE_EXPORT', 'N/A', true, NOW(), 'ADMIN');

-- APPLY MAINADDRESS FROM ADMIN 9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1
INSERT INTO roleprivilege VALUES ('3eed1545-6c2c-4a34-86af-3fe12be0cdd6','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','bf7009dc-e7f1-46bc-93fb-97a6690e27af');
INSERT INTO roleprivilege VALUES ('0d015477-8742-4b35-8112-19790e21b0b2','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','ed5ceadd-fbad-4e84-a65a-ceb85440963a');
INSERT INTO roleprivilege VALUES ('a0a525c6-9bdc-4ad4-9a6c-884b2f66c25f','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','e41427ea-0538-4541-a1cd-4044edfef1bf');
INSERT INTO roleprivilege VALUES ('28d382e9-1546-4b13-91dc-2d4edffd249f','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','9307e339-be58-41ec-8b9b-1278b5759eac');
INSERT INTO roleprivilege VALUES ('10256889-d99c-4419-aa55-69b65ae50bb5','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','d3bb9d47-8b00-4778-9860-fab8952c53a0');

 -- CREATE PRIVILEGE FROM TRANSTATUS
INSERT INTO privilege VALUES ('cb67a4da-04e3-4f9c-809d-bf557f85dd77', 'TRANSTATUS_READ', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('332876e8-cd0b-42af-9ec1-d69354220560', 'TRANSTATUS_CREATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('160b5610-bcc2-40b3-8a8d-7836b9b81654', 'TRANSTATUS_UPDATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('77674dec-0fa8-4242-87b3-f02d5ff4775b', 'TRANSTATUS_ADVANCE_FILTER', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('0f1d7d2a-6d12-42c4-a162-e06af5a51d20', 'TRANSTATUS_ADVANCE_EXPORT', 'N/A', true, NOW(), 'ADMIN');

-- APPLY TRANSTATUS FROM ADMIN 9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1
INSERT INTO roleprivilege VALUES ('d5c937c8-c2a7-45e1-9cff-2caf2d3caf85','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','cb67a4da-04e3-4f9c-809d-bf557f85dd77');
INSERT INTO roleprivilege VALUES ('2440b2e6-e3c8-45f3-97d7-773e0a7fc3ee','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','332876e8-cd0b-42af-9ec1-d69354220560');
INSERT INTO roleprivilege VALUES ('b7e21990-baaf-4040-9e26-1a390dc060c7','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','160b5610-bcc2-40b3-8a8d-7836b9b81654');
INSERT INTO roleprivilege VALUES ('6c2dd777-d2ea-476c-9fb5-c180928bf2e4','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','77674dec-0fa8-4242-87b3-f02d5ff4775b');
INSERT INTO roleprivilege VALUES ('72eeae39-d9a9-454e-aa3c-196fd96c6852','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','0f1d7d2a-6d12-42c4-a162-e06af5a51d20');

-- CREATE PRIVILEGE FROM BRANCH
INSERT INTO privilege VALUES ('fdf135c4-098e-46c4-afda-b551e50fb211', 'BRANCH_READ', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('c8954aa1-2f5c-4454-b0cd-34ff85577108', 'BRANCH_CREATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('3203ab82-ea46-4c83-a8b5-4141d1c1bf70', 'BRANCH_UPDATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('bd14214a-c5be-44e4-8fe3-46dcd1121f3e', 'BRANCH_ADVANCE_FILTER', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('b1a9c427-b5b8-4619-a35c-389fbd0327ad', 'BRANCH_ADVANCE_EXPORT', 'N/A', true, NOW(), 'ADMIN');

-- APPLY BRANCH FROM ADMIN 9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1
INSERT INTO roleprivilege VALUES ('80b34925-2697-49b1-92b7-dbf717905d41','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','fdf135c4-098e-46c4-afda-b551e50fb211');
INSERT INTO roleprivilege VALUES ('ce1cd3e4-ba0f-4c0a-b8e1-34d4c5913416','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','c8954aa1-2f5c-4454-b0cd-34ff85577108');
INSERT INTO roleprivilege VALUES ('b6f1aac9-f319-47b1-8a8f-f1c4df42c8bf','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','3203ab82-ea46-4c83-a8b5-4141d1c1bf70');
INSERT INTO roleprivilege VALUES ('359c40b8-8792-4634-b5f6-52099fdc49ed','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','bd14214a-c5be-44e4-8fe3-46dcd1121f3e');
INSERT INTO roleprivilege VALUES ('dffd5bb6-ba8a-45eb-acad-6667b2904e30','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','b1a9c427-b5b8-4619-a35c-389fbd0327ad');

-- CREATE PRIVILEGE FROM NAVIGATION
INSERT INTO privilege VALUES ('680ad486-e6ec-477c-9105-bcd905a9009f', 'NAVIGATION_READ', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('08c5bd20-16a7-4922-8a66-abb82c3098c3', 'NAVIGATION_CREATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('835aeb28-1efa-44f4-b8e8-d41d8e3531b4', 'NAVIGATION_UPDATE', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('f75b5c7b-8bf7-4b9f-ae3c-a3c082290e0f', 'NAVIGATION_ADVANCE_FILTER', 'N/A', true, NOW(), 'ADMIN');
INSERT INTO privilege VALUES ('3ecd168d-fa5b-4307-a299-8e8aaebba063', 'NAVIGATION_ADVANCE_EXPORT', 'N/A', true, NOW(), 'ADMIN');

-- APPLY NAVIGATION FROM ADMIN 9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1
INSERT INTO roleprivilege VALUES ('c3a37a2a-af32-43de-8971-0f6c22dbb55c','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','680ad486-e6ec-477c-9105-bcd905a9009f');
INSERT INTO roleprivilege VALUES ('cdc66d8c-3a73-4ad4-aa36-ebf63adfea78','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','08c5bd20-16a7-4922-8a66-abb82c3098c3');
INSERT INTO roleprivilege VALUES ('05fdb3c4-3011-44ca-bcd2-3bcadee0cf4a','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','835aeb28-1efa-44f4-b8e8-d41d8e3531b4');
INSERT INTO roleprivilege VALUES ('9c2f7419-bec5-432b-a027-4d36d9bc3e6c','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','f75b5c7b-8bf7-4b9f-ae3c-a3c082290e0f');
INSERT INTO roleprivilege VALUES ('140b5579-ced7-49f8-ab89-e4f48a798877','9f78da70-5c8c-4a9b-bea1-4cc9352dfbe1','3ecd168d-fa5b-4307-a299-8e8aaebba063');

--- ����canal�û�����dbaָ������
CREATE USER canal IDENTIFIED BY 'canal';    

--  �����Ǹ�canal ��ȨΪ���Ը���log���û�
GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';
GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';

FLUSH PRIVILEGES; 
--- 1 �����⣬���С��dba�ƶ�(���Ժ�С����2�ű�
create database dbus;

--- 2  �����û���������dba�ƶ�
CREATE USER dbus IDENTIFIED BY 'your_password';


--- 3 ��Ȩdbus�û�����dbus�Լ��Ŀ�, ��Ҫ��Ȩ��dbus�����Ӧ��ip��
GRANT ALL ON dbus.* TO dbus@'%'  IDENTIFIED BY 'your_password';
GRANT ALL ON dbus.* TO dbus@'%'  IDENTIFIED BY 'your_password';


flush privileges; 






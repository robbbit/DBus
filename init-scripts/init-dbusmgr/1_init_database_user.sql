---˵����dbusmgr�� dbus�Ĺ���⣬����Ԫ���ݣ�ֻ��Ҫ����һ�Σ�λ�����κ�mysql�ⶼ����


--- 1 �����⣬���С��dbaָ�� (��С�Ϳ��ԣ�������ʹ�ã�
create database dbusmgr;


--- 2 �����û���������dbaָ��
CREATE USER dbusmgr IDENTIFIED BY 'HxP31vevLw9PoiT/';


--- 3 ��Ȩ�û�����Ȩ�Լ��Ŀ�, ������dbaָ��
GRANT ALL ON dbusmgr.* TO dbusmgr@'%' IDENTIFIED BY 'HxP31vevLw9PoiT/';

flush privileges; 
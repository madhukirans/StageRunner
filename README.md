###Modify /etc/yum.conf
	
	sudo echo proxy=http://www-proxy.us.oracle.com:80 >> /etc/yum.conf

###modify /etc/wgetrc
	
	sudo echo http_proxy=http://www-proxy.us.oracle.com:80 >> /etc/wgetrc

###Make sure GIT is present 
	
	git config --global http.proxy http://www-proxy.us.oracle.com:80
	git config --global https.proxy http://www-proxy.us.oracle.com:80
	git config --global core.editor vi
	
	mkdir -p /scratch/sr/work
	mkdir -p /scratch/$USER/sr
	cd /scratch/$USER/sr
	git clone https://github.com/madhukirans/StageRunner

###MysqL install:

	[sudo sudo yum update]
	wget http://dev.mysql.com/get/mysql57-community-release-el6-7.noarch.rpm     
	# on OL 6.x – replace el6 with the corresponding linux version
	yum remove mysql-libs	
	yum localinstall mysql57-community-release-el6-7.noarch.rpm
	yum repolist enabled | grep "mysql.*-community.*"
	yum install mysql-community-common
	yum install mysql-community-server
	yum install mysql-workbench-community

#### Remove all databases
	rm -rf /var/lib/mysql
	service mysqld stop
	service mysqld start
	service mysqld status
	
#### Get the mysql root password and reset the password. 
	cat /var/log/mysqld.log | grep password
	sudo mysql_secure_installation (and follow resetting the root password. You get the root password from above command)
	#Reset the root password to Stage123# 

###Create database called "results"
	mysql -u root -p
	create database results;
	quit;

###Import database

	cd /scratch/$USER/sr/StageRunner/mysqldump
	cat *.sql | mysql -u root -p results

###These commands are optional
 	
	mysql logging /var/log/mysqld.log
 	mysqld --initialize

###Installing & configuring nodeJS & npm

	curl --silent --location https://rpm.nodesource.com/setup_7.x | bash -
	sudo yum install nodejs
	sudo yum install npm
	
###Set npm proxy

	npm config set proxy http://www-proxy.us.oracle.com:80
	npm config set https-proxy http://www-proxy.us.oracle.com:80
	
###Run the following commands

        sudo npm install --https-proxy=http://www-proxy.us.oracle.com:80 --proxy=http://www-proxy.us.oracle.com:80 -g bower
	sudo npm install --https-proxy=http://www-proxy.us.oracle.com:80 --proxy=http://www-proxy.us.oracle.com:80 -g gulp
	cd /scratch/$USER/sr/StageRunner	
	npm install --https-proxy=http://www-proxy.us.oracle.com:80 --proxy=http://www-proxy.us.oracle.com:80
	

###Run as normal user

	npm config set proxy http://www-proxy.us.oracle.com:80
	npm config set https-proxy http://www-proxy.us.oracle.com:80

###Setup project

	cd /scratch/$USER/sr/StageRunner
	npm install


###Copy glassfish4

	 cp -r /net/slc09iyd/scratch/mseelam/glassfish4 /scratch/$USER/

###Copy netbeans

	 cp -r /net/slc09iyd/scratch/mseelam/netbeans-8.1 /scratch/$USER/
	 edit ./etc/netbeans.conf
	 Modify jdkhome param

###copy java

	cp -r /net/slc09iyd/scratch/mseelam/jdk1.8.0_91 /scratch/mseelam/

###Modify env.sh with appropriate values

###Start glassfish server

	/scratch/mseelam/sr/StageRunner/startserver.sh

###Deploy applicaton

	/scratch/mseelam/sr/StageRunner/deploy.sh

###Start backend daemon

	/scratch/mseelam/sr/StageRunner/startserver.sh &

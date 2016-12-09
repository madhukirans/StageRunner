#!/bin/bash -x

source env.sh

cd $STAGE_RUNNER_HOME

ant 

echo 
echo "Deploying Application"
echo
#mv $STAGE_RUNNER_HOME/build/web/WEB-INF/classes/META-INF/persistence1.xml $STAGE_RUNNER_HOME/build/web/WEB-INF/classes/META-INF/persistence.xml

cmd="$SERVER_HOME/bin/asadmin undeploy StageRunner"
echo $cmd
$cmd

cmd="$SERVER_HOME/bin/asadmin  deploy --name StageRunner $STAGE_RUNNER_HOME/dist/StageRunner.war"
echo $cmd
$cmd

echo
echo "Deploy finished"
echo

#!/bin/bash -x

source env.sh

cd $STAGE_RUNNER_HOME

mkdir -p $BOOTSTRAP_HOME/avu_re
cp $STAGE_RUNNER_HOME/tools/retool/3.0.0/bin/avu.jar $BOOTSTRAP_HOME/avu_re

ant 

echo "Starting GlassFish server"
echo
$SERVER_HOME/bin/startserv domain1 > seerver.log &

echo "Server is starting please wait"
sleep 30

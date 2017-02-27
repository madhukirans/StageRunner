#!/bin/bash


export STAGE_RUNNER_HOME=/scratch/mseelam/sr/StageRunner
export SERVER_HOME=/scratch/mseelam/glassfish4/glassfish
export JAVA_HOME=/scratch/mseelam/jdk1.8.0_91
export JDK15HOME=$JAVA_HOME
export ANT_HOME=/scratch/mseelam/apache-ant-1.9.2
export PATH=$STAGE_RUNNER_HOME/tools/retool/3.0.0/bin:$PATH
export BOOTSTRAP_HOME=/scratch/sr/bootstrap

export PATH=$JAVA_HOME/bin:$PATH

echo "Enter ade okinit password"
ade okinit
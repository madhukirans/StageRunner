#!/bin/bash

source env.sh

cd $STAGE_RUNNER_HOME

ant build-jar 

echo "Trackeing for new stage"

$JAVA_HOME/bin/java -classpath $STAGE_RUNNER_HOME/dist/StageRunner.jar:$STAGE_RUNNER_HOME/lib/json-20160810.jar:$STAGE_RUNNER_HOME/lib/sapphire/gtlf-libs.jar:$STAGE_RUNNER_HOME/lib/sapphire/gtlf-uploader.jar:$STAGE_RUNNER_HOME/lib/sapphire/gtlfutils-core.jar:$STAGE_RUNNER_HOME/lib/sapphire/jsch-0.1.41.jar:$STAGE_RUNNER_HOME/lib/sapphire/mail.jar:$STAGE_RUNNER_HOME/lib/mysql-connector-java-6.0.2.jar:$STAGE_RUNNER_HOME/lib/org.eclipse.persistence.antlr-2.5.2.jar:$STAGE_RUNNER_HOME/lib/org.eclipse.persistence.asm-2.5.2.jar:$STAGE_RUNNER_HOME/lib/org.eclipse.persistence.core-2.5.2.jar:$STAGE_RUNNER_HOME/lib/org.eclipse.persistence.jpa-2.5.2.jar:$STAGE_RUNNER_HOME/lib/org.eclipse.persistence.jpa.jpql-2.5.2.jar:$SERVER_HOME/modules/endorsed/webservices-api-osgi.jar:$SERVER_HOME/modules/endorsed/jaxb-api-osgi.jar:$SERVER_HOME/modules/endorsed/javax.annotation-api.jar:$SERVER_HOME/modules/javax.management.j2ee-api.jar:$SERVER_HOME/modules/javax.servlet.jsp.jstl-api.jar:$SERVER_HOME/modules/javax.inject.jar:$SERVER_HOME/modules/javax.servlet.jsp.jstl.jar:$SERVER_HOME/modules/webservices-osgi.jar:$SERVER_HOME/modules/javax.enterprise.deploy-api.jar:$SERVER_HOME/modules/javax.transaction-api.jar:$SERVER_HOME/modules/javax.jms-api.jar:$SERVER_HOME/modules/javax.faces.jar:$SERVER_HOME/modules/bean-validator.jar:$SERVER_HOME/modules/javax.interceptor-api.jar:$SERVER_HOME/modules/javax.el.jar:$SERVER_HOME/modules/javax.xml.rpc-api.jar:$SERVER_HOME/modules/javax.servlet.jsp.jar:$SERVER_HOME/modules/jaxb-osgi.jar:$SERVER_HOME/modules/javax.resource-api.jar:$SERVER_HOME/modules/javax.security.jacc-api.jar:$SERVER_HOME/modules/javax.ejb-api.jar:$SERVER_HOME/modules/javax.enterprise.concurrent-api.jar:$SERVER_HOME/modules/javax.json.jar:$SERVER_HOME/modules/javax.ws.rs-api.jar:$SERVER_HOME/modules/javax.enterprise.concurrent.jar:$SERVER_HOME/modules/javax.batch-api.jar:$SERVER_HOME/modules/javax.mail.jar:$SERVER_HOME/modules/javax.servlet-api.jar:$SERVER_HOME/modules/javax.xml.registry-api.jar:$SERVER_HOME/modules/javax.servlet.jsp-api.jar:$SERVER_HOME/modules/javax.persistence.jar:$SERVER_HOME/modules/javax.websocket-api.jar:$SERVER_HOME/modules/weld-osgi-bundle.jar:$SERVER_HOME/modules/javax.security.auth.message-api.jar:$SERVER_HOME/../mq/lib/jaxm-api.jar:$SERVER_HOME/lib/embedded/glassfish-embedded-static-shell.jar com.oracle.stagerun.tool.StageRunDaemon StageTracker



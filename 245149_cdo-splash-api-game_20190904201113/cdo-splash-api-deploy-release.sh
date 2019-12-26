#!/bin/bash

APP_NAME="${parentArtifactId}-api"
DIR="/web/cdo"
LOG_DIR="/var/logs/cdo"
PROC_DIR="${DIR}/${APP_NAME}"
BACKUP_DIR="${PROC_DIR}/backup"
APP_LOG="${LOG_DIR}/${APP_NAME}"

cd ${DIR}

if [ ! -d  ${PROC_DIR} ];then
	mkdir -p ${APP_NAME}
fi

if [ ! -d  ${BACKUP_DIR} ];then
	mkdir -p ${BACKUP_DIR}
fi

ZIP_NAME=`ls -t|grep zip|grep "${APP_NAME}"|head -1`
echo "zip:$ZIP_NAME"

if [ "X$ZIP_NAME" != "X" ]; then
    cp ${ZIP_NAME} ${APP_NAME}
    cd ${PROC_DIR}
    rm -fr ./lib/*
    echo unzip zip file:${ZIP_NAME}
    unzip -od . ${ZIP_NAME}
    mv *.zip backup/
fi

cd ${PROC_DIR}

if [ ! -d  ${APP_LOG} ];then
	mkdir -p ${APP_LOG}
fi

if [ ! -d ${PROC_DIR}/logs ]; then
  ln -sf ${APP_LOG} logs
fi

cd ${PROC_DIR}/bin/
chmod +x ./launcher
./launcher -restart
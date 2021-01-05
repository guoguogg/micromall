#!/bin/bash
source /etc/profile;
nohup java  -Xms256m -Xmx512m -jar  micromall-all.jar  > catalina.out  2>&1 &

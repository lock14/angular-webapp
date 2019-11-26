@echo off
SETLOCAL

SET LOCAL_NODE=.\angular\node\node.exe
SET LOCAL_NPM=.\angular\node\node_modules\npm\bin\npm-cli.js

IF NOT EXIST %LOCAL_NODE% .\mvnw.cmd generate-resources

%LOCAL_NODE% "%LOCAL_NPM%" --scripts-prepend-node-path true --prefix angular %*

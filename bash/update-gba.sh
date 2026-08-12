#!/bin/bash
# SCRIPT UPDATE GIT-BASH-ALIASES
# updated: 2026-08-12 16:23
# execute 'source update-gba.sh'


### Regular Git-Bash-Aliases files

command cp -v ./bash-help-src.txt ${HOME}/.bash-help-src ;

command cp -v ./git-bash-aliases.sh ${HOME}/.git-bash-aliases  ;

command source ${HOME}/.git-bash-aliases ;

echo ; __bh_h_greeting ; echo ; __bh_h ;



### Kubectl helper

echo ;

command cp -v ./kh-zerohelper-v1.sh ${HOME}/Desktop/dm-git-aux/kubeconfig/ ;
# echo 'WARNING, REM: update on kh-zero-helper.sh disabled!'



### Java helper for DM-WM 

echo ;

command cp -v ./jh-wm-zerohelper-v2.sh ${HOME}/Desktop/dm-git-java/ ;
# echo 'WARNING, REM: update on jh-wm-zero-helper-v2.sh disabled!'

command cp -v ./_jh-wm-start-platform-list-v2.sh ${HOME}/Desktop/dm-git-java/ ;
# echo 'WARNING, REM: update on _jh-wm-start-platform-list-v2.sh disabled!'

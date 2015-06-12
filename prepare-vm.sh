#!/bin/bash
readonly LOG_NAME="EL-BOMBILLO"
echo "[${LOG_NAME}] Provisioning started"

# Install ansible
if ! [ -e /opt/ansible ]; then
    cd /opt
    git clone git://github.com/ansible/ansible.git --recursive
    cd /opt/ansible
    source /opt/ansible/hacking/env-setup
    sudo easy_install pip
    sudo pip install paramiko PyYAML Jinja2 httplib2
else
    cd /opt/ansible
    git pull --rebase
    git submodule update --init --recursive
fi

echo "[${LOG_NAME}] Provisioning successfully finished"

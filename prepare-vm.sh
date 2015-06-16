#!/bin/bash
readonly LOG_NAME="EL-BOMBILLO"
echo "[${LOG_NAME}] Provisioning started"

# Install ansible
if ! [ -e /opt/ansible ]; then
    echo "[${LOG_NAME}] About to install from scratch"
    sudo -s
    yum -y install git gcc python-devel
    cd /opt
    git clone git://github.com/ansible/ansible.git --recursive
    cd /opt/ansible
    source /opt/ansible/hacking/env-setup
    easy_install pip
    pip install paramiko PyYAML Jinja2 httplib2
else
    echo "[${LOG_NAME}] About to update ansible installation"
    sudo -s
    cd /opt/ansible
    git pull --rebase
    git submodule update --init --recursive
fi

echo "[${LOG_NAME}] Provisioning successfully finished"

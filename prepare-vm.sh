#!/bin/bash
readonly LOG_NAME="EL-BOMBILLO"
echo "[${LOG_NAME}] Provisioning started"

sudo -s

# Install tools for general availability
yum -y install git gcc python2-devel rpm-build asciidoc epel-release device-mapper-event-libs docker

# Install ansible
if ! [ -e /opt/ansible ]; then
    echo "[${LOG_NAME}] About to install ansible from scratch"
    cd /opt
    git clone git://github.com/ansible/ansible.git --recursive
    cd /opt/ansible
    make rpm
    yum localinstall -y ./rpm-build/ansible-*.noarch.rpm
else
    cd /opt/ansible
    if ! git diff-index --quiet HEAD --; then
        echo "[${LOG_NAME}] No ansible update available"
    else
        echo "[${LOG_NAME}] About to update ansible installation"
        git pull --rebase
        git submodule update --init --recursive
        make rpm
        yum localinstall -y ./rpm-build/ansible-*.noarch.rpm
    fi
fi

# Start docker docker
if [ "$(systemctl is-active docker)" != "active" ] ; then
    echo "[${LOG_NAME}] About to start docker"
    systemctl start docker
    chkconfig docker on
else
    echo "[${LOG_NAME}] Docker already running"
fi

echo "[${LOG_NAME}] Provisioning successfully finished"

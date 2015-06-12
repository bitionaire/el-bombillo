# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.box = "chef/centos-7.0"
  config.ssh.insert_key = false

  config.vm.provider :virtualbox do |v|
    v.name = "el-bombillo/centos-7.0"
    v.memory = 2048
    v.cpus = 2
    v.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
    v.customize ["modifyvm", :id, "--ioapic", "on"]
  end

  config.vm.hostname = "el-bombillo"
  config.vm.network :private_network, ip: "192.168.33.55"

  config.vm.provision "file", source: "./prepare-vm.sh", destination: "/tmp/prepare-vm.sh"
  config.vm.provision "shell", inline: "/bin/bash /tmp/prepare-vm.sh"
end

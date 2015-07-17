# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.box = "chef/ubuntu-14.04"
  config.ssh.insert_key = false

  config.vm.provider :virtualbox do |v|
    v.name = "elbombillo"
    v.memory = 2048
    v.cpus = 2
    v.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
    v.customize ["modifyvm", :id, "--ioapic", "on"]
  end

  config.vm.hostname = "el-bombillo"
  config.vm.network :private_network, ip: "192.168.33.55"
  
  config.vm.provision "file", source: "el-bombillo-user-service/provisioning/database", destination: "/tmp/el-bombillo-user-service/provisioning/database"
  config.vm.provision "docker" do |d|
    d.build_image "/tmp/el-bombillo-user-service/provisioning/database", args: "-t el-bombillo-user-service/database"
  end

  config.vm.define "service1" do |service1|
    config.vm.provision "docker" do |d|
      d.run "el-bombillo-user-service/database", args: "-P"
    end
  end

end


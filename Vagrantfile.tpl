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
  <%
      println ""
      services.each { service ->
          println "  config.vm.provision \"file\", source: \"" + service.getServicePath() + "\", destination: \"/tmp/" + service.getServicePath() + "\""
      }

      println "  config.vm.provision \"docker\" do |d|"
      services.each { service ->
          println "    d.build_image \"/tmp/" + service.getServicePath() + "\", args: \"-t " + service.getServiceName() + "\""
      }
      println "  end"

      def serviceNumber = 0;
      services.each { service ->
          serviceNumber++
          println ""
          println "  config.vm.define \"service" + serviceNumber + "\" do |service" + serviceNumber + "|"
          println "    config.vm.provision \"docker\" do |d|"
          println "      d.run \"" + service.getServiceName() + "\""
          println "    end"
          println "  end"
      }
  %>
end


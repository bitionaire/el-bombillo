FROM ansible/centos7-ansible:stable

# Add playbooks to the Docker image
ADD ansible /tmp/provisioning
WORKDIR /tmp/provisioning

# Run ansible to configure the Docker image
RUN ansible-playbook site.yml -c local

# Expose ports
EXPOSE 5432

# Start server
CMD [ "ls", "/sbin", "/usr/sbin" ]
# Install `docker-compose`
Do not install with `apt`, this will pick the Version from the Ubuntu repositories which is too old.

If you already installed via `apt`, do a `sudo apt remove docker-compose` first.

Execute this in WSL to install the 2.10.0 release from Github:
```bash
sudo curl -L "https://github.com/docker/compose/releases/download/v2.10.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/bin/docker-compose
sudo chmod +x /usr/bin/docker-compose
```

To make `docker-compose` also available in Git bash, amend  `~/.bashrc` with the following:
```bash
# make docker-compose command available
alias docker-compose='wsl docker-compose'
```
and execute `source ~/.bashrc`.

# Start/stop 'mysql'
Go into the directory of the `docker-compose.yaml` and run either:
```bash
docker-compose up -d
```
or
```bash
docker-compose stop
```

# Execute a bash shell in the Docker container
```bash
docker exec -it mysql bash
```
This can be used to open MySQL console to inspect the database:
```
mysql -u root -p
```

# Docker/Kubernetes unter WSL2 installieren

## WSL2 einrichten
Falls bereits eine ältere Version von WSL installiert war, sind zusätzliche Schritte notwendig (https://docs.microsoft.com/de-de/windows/wsl/install-manual)

### WSL-Windows-Feature installieren 
PowerShell **als Administrator** öffnen:
```powershell
Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Windows-Subsystem-Linux
```

### Ubuntu in WSL2 installieren
PowerShell öffnen:
```powershell
wsl --set-default-version 2
wsl --update
wsl --install -d Ubuntu
```
Der Ubuntu-Installation folgen und einen Benutzeraccount anlegen.

Prüfen, ob die Version korrekt unter WSL2 ausgeführt wird: `wsl -l -v`  
Erwartete Ausgabe:
```
  NAME      STATE           VERSION
* Ubuntu    Running         2
```

## Docker in Ubuntu installieren
Dazu die Ubuntu Shell öffnen.

### Erforderliche Dependencies
System aktualisieren und für die Installation erforderliche Abhängigkeiten einrichten.

```bash
sudo apt-get update 
sudo apt-get -y install apt-transport-https ca-certificates curl gnupg lsb-release 
```

### Docker GPG-Key einrichten
Um das Docker-Repository verwenden zu können, müssen wir den Schlüssel in Ubuntu einrichten.

```bash
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg 
```

### Stable Docker-Repository auswählen und Docker installieren
Stabiles Docker-Repo den APT Sources hinzufügen

```bash
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null 
```

Docker installieren
```bash
sudo apt-get update 
sudo apt-get -y install docker-ce docker-ce-cli containerd.io 
```

Unseren Benutzer der `docker`-Gruppe hinzufügen
```
sudo groupadd docker 
sudo usermod -aG docker $USER 
```

### Docker-Installation testen
```
sudo service docker start
sudo service docker status
sudo docker run hello-world
```
Erwartete Ausgabe:
```
Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/
```

### Docker aus PowerShell und GIT Bash ansprechen - @TODO: Did not complete this part

**PowerShell**

PowerShell **als Administrator** öffnen und die Ausführungsrichtlinie anpassen, damit wir ein PowerShell-Profil anlegen können:
```
Set-ExecutionPolicy RemoteSigned
```

Neue PowerShell **ohne Administratorrechte** öffnen und den Pfad zum Profil bestimmen:
```
echo $PROFILE
```
Ausgabe sollte in etwas dem nachfolgenden entsprechen:
```
C:\Users\tweber\Documents\WindowsPowerShell\Microsoft.PowerShell_profile.ps1
```
Wenn die Datei nicht existiert, an dieser Stelle erstellen.

Die Datei im Editor der Wahl öffnen und Alias für `docker` hinzufügen (das kann später analog für die Kubernetes-Werkzeuge erfolgen):
```powershell
Function Start-WslDocker {
    wsl docker $args
}

Set-Alias -Name docker -Value Start-WslDocker
```

PowerShell erneut öffnen und testen:
```
docker ps
```

**GIT Bash**
Die Datei `~/.bashrc` um folgende Zeilen erweitern bzw neu erstellen:
```bash
# make docker command available
alias docker='wsl docker'
```
und `source ~/.bashrc` ausführen.


### Docker automatisch starten, wenn WSL-Shell geöffnet wird
**Ermöglichen `sudo` ohne Passwort zu verwenden**
In Ubuntu:
```bash
sudo visudo /etc/sudoers
```
Dort folgende Zeile hinzufügen:
```bash
benutzer ALL=(ALL) NOPASSWD: /usr/bin/dockerd
```
Dabei `benutzer` durch euren Ubuntu-User ersetzen.

Jetzt sollte sich `docker` ohne `sudo` ausführen lassen:
```bash
docker ps
```

**Bash-Profil in Ubuntu erweitern**

Am Ende des Bash-Profils (`~/.profile`) folgende Zeilen einfügen:
```bash
# Start Docker daemon automatically when logging in if not running.
# start docker
if service docker status 2>&1 | grep -q "is not running"; then
    wsl.exe -d "${WSL_DISTRO_NAME}" -u root -e /usr/sbin/service docker start >/dev/null 2>&1
fi
```

## Kubernetes (k3d) installieren

### `kubectl` installieren
```bash
cd /usr/bin
sudo curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo chmod 755 /usr/bin/kubectl
```
Weitere Informationen: https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/

### K3D als Kubernetes Distribution installieren
*Dokumentation:* 
> https://k3d.io/v5.3.0/usage/configfile/

*Blog mit vielen Tipps zu Dev-Setups mit k3d:*
> https://en.sokube.ch/post/k3s-k3d-k8s-a-new-perfect-match-for-dev-and-test-1


**Die aktuelle Version von K3D aus GitHub in Ubuntu installieren**

```bash
curl -s https://raw.githubusercontent.com/k3d-io/k3d/main/install.sh | bash
```

**Testen der Installation**

* Single-Node-Cluster `testcluster` erstellen:
   ```
   k3d cluster create testcluster
   ```
* Cluster-Info mit `kubectl` ausgeben lassen:
   ```
   kubectl cluster-info
   ```
* Test-Cluster wieder löschen:
   ```
   k3d cluster delete testcluster
   ```

**Autovervollständigung für k3d in Bash einrichten**

```bash
k3d completion bash > ~/k3d
sudo mv ~/k3d /usr/share/bash-completion/completions/k3d
source .bashrc
```

### Kubernetes-Cluster für Entwicklung erstellen
Cluster `dev` mit einem Server, einem Agent und einem Load-Balancer erstellen. Der lokale Port 80 wird auf Port 80 des Loadbalancers gemapt. Der API-Server wird auf Port 6443 verfügbar gemacht.

```
k3d cluster create dev --servers 1 --agents 1 --port 80:80@loadbalancer --api-port 6443
```

### Kubernetes Dashboard in einem Cluster installieren:
```bash
GITHUB_URL=https://github.com/kubernetes/dashboard/releases
VERSION_KUBE_DASHBOARD=$(curl -w '%{url_effective}' -I -L -s -S ${GITHUB_URL}/latest -o /dev/null | sed -e 's|.*/||')
kubectl create -f https://raw.githubusercontent.com/kubernetes/dashboard/${VERSION_KUBE_DASHBOARD}/aio/deploy/recommended.yaml
```

`dashboard-admin.yaml` erzeugen:
```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: admin-user
  namespace: kubernetes-dashboard
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: admin-user
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
- kind: ServiceAccount
  name: admin-user
  namespace: kubernetes-dashboard
```

Admin-Account erzeugen und Token beziehen:
```bash
kubectl create -f dashboard-admin.yaml
kubectl -n kubernetes-dashboard describe secret admin-user-token | grep '^token'
```

Dashboard verfügbar machen:
```bash
kubectl proxy
```
Das Dashboard befindet sich unter http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/#/login.

Hier mit dem Token anmelden.

## Lokale Docker-Registry einrichten
Die lokale Registry (`registry.local:5000`) wird zum Pushen unserer Anwendungs-Images verwendet und dient in Kubernetes als Pull-Registry für unsere Deployments.

**Registry in Docker deployen**

```bash
docker run -d -p 5000:5000 --restart=always --name registry registry:2
```
Die Registry startet automatisch sobald Docker gestartet wird.

Docker `daemon.json` in `/etc/docker` erstellen (`sudo vi daemon.json`):
```json
{
  "insecure-registries": [
    "registry.local:5000"
  ]
}
```

Und die `registry.local` in der Windows-Hosts-Datei eintragen (`C:\Windows\System32\Drivers\etc\hosts`):
```
127.0.0.1 registry.local
```

## Stern und Helm in WSL installieren

### Stern
_Stern ist ein Kommandozeilen-Tool zum Zugriff auf Kubernetes Pod Logs im Stil von `tail`_  
https://github.com/wercker/stern

Auf der Release-Seite nach der aktuellsten Version suchen und das nachfolgende Skript entsprechend anpassen:
```bash
STERN_VERSION=1.11.0
curl -Ls https://github.com/wercker/stern/releases/download/${STERN_VERSION}/stern_linux_amd64 > stern
chmod 755 stern
sudo mv stern /usr/bin/
```

### Helm
_The package manager for Kubernetes_  
https://helm.sh/

Auf der [Release-Seite](https://github.com/helm/helm/releases) nach der aktuellsten Version suchen und das nachfolgende Skript entsprechend anpassen:

```bash
HELM_VERSION=3.8.0
curl -Ls https://get.helm.sh/helm-v${HELM_VERSION}-linux-amd64.tar.gz | tar xzf - linux-amd64/helm  --strip-components 1
sudo mv helm /usr/bin
```

**Bash-Completion einrichten:**
```bash
helm completion bash > ~/helm
sudo mv ~/helm /usr/share/bash-completion/completions/helm
source .bashrc
```

# Bekannte Schwierigkeiten

## Pull-Registry
Um die lokale Registry im k3d Cluster zu verweden, muss der Cluster mit einer `registry-config <yaml-file>` erzeugt werden.  
_Beispiel:_
```bash
k3d cluster create dev --servers 1 --agents 1 --port 80:80@loadbalancer --api-port 6443 --registry-config "registry.yaml"
```  
`registry.yaml`:  
```yaml
mirrors:
  "host.k3d.internal:5000":
    endpoint:
      - http://host.k3d.internal:5000
```
Als Hostnamen für die Registry ist dabei `host.k3d.internal` zu verwenden. Das gilt auch für die Image angaben in den Kubernetes Yamls, z.B. `host.k3d.internal:5000/my/image:tag`.

## Cluster nach Reboot nicht verfügbar
Nach einem Reboot des Rechners ist das Cluster in einem nicht verfügbaren/defekten Zustand. Hier hilft es das Cluster einmal zu stoppen und wieder zu starten.  
_Beispiel: (Cluster-Name ist `dev-cluster`)_
```bash
k3d cluster stop dev-cluster
k3d cluster start dev-cluster
```
Danach steht das Cluster wie gewohnt zur Verfügung.


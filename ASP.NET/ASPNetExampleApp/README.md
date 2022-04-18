# Simple ASP.Net App in Docker

Create Hello World App ([tutorial](https://dotnet.microsoft.com/en-us/learn/aspnet/hello-world-tutorial/create)): 

```
$ dotnet new sln -n MyWebApp
$ dotnet new webapp -o MyWebApp --no-https -f net5.0
$ dotnet sln MyWebApp.sln add MyWebApp/MyWebApp.csproj
```

Build & Run

```
$ docker compose build
```

Go to http://127.0.0.1:9099/
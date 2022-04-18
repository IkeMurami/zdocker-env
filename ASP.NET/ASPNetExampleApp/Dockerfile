ARG ASPNET_REPO=mcr.microsoft.com/dotnet/aspnet
ARG SDK_REPO=mcr.microsoft.com/dotnet/sdk
ARG VERSION_ENV=5.0

FROM $SDK_REPO:$VERSION_ENV as builder

WORKDIR /source

# copy csproj and restore as distinct layers
COPY app/*.sln .
COPY app/MyWebApp/*.csproj ./MyWebApp/
RUN dotnet restore

# copy everything else and build app
COPY app/MyWebApp/. ./MyWebApp/
WORKDIR /source/MyWebApp
RUN dotnet publish -c release -o /app --no-restore

# final stage/image
FROM $ASPNET_REPO:$VERSION_ENV AS runtime
WORKDIR /app
COPY --from=builder /app ./
ENTRYPOINT [ "dotnet", "MyWebApp.dll" ]
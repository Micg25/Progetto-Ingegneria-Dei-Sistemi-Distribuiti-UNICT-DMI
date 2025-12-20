# Remote Facade Example

A simple app showing the use of Design Patterns Remote Facade and Data Transfer Object, and using RMI for remote communication.

## Deployment

The client-side has to have packages api, client, dto.

The server-side has to have packages api, dto, domain, remote.

## Configuration

In AlbumProxy edit field serviceName to provide the proper IP address of the server.

## Run

Firstly, run rmiregistry in the root folder of classes, from the shell. 

Secondly, run AlbServer

Finally, run the Client.
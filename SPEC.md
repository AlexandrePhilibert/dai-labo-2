# Chat protocol for texting

This document outline the chat protocol for Texting (CPT).

## Commands

### CONNECT

Connects a client with a given username, returns ACCEPT if the username is not already used, otherwise, return DECLINE.

### ACCEPT

Accepts the user CONNECT with the given username.

### DECLINE

Decline the previous command (e.g. CONNECT).

### SEND

Sends a message to either a user or a group.

### ACK

Acknowledge the previous command (e.g. a SEND).

### SYNC

Requests the server to sync the messages from a given timestamp.

### MSG

Tells a client that he received a message.

### ENDSYNC

Ends the sync request, the client is now up-to-date.


## Example diagram


```mermaid
sequenceDiagram

%% Handle connection
Client 1->>Server: CONNECT alexandre
%% EIther accept or decline the connection request
Server->>Client 1: ACCEPT
Server->>Client 1: DECLINE username_taken

%% Send a message in a group
Client 1->>Server: SEND group1 Hello ceci est un test
%% Acknowledge that the server received the message
Server->>Client 1: ACK

%% 
Client 2->>Server: SYNC group1 timestamp
Server->>Client 2: MSG group1 red timestamp Before joined
Server->>Client 2: MSG group1 alexandre timestamp Hello
Server->>Client 2: ENDSYNC

%% Send a message to a user
Client 2->>Server: SEND alexandre Hello
Server->>Client 2: ACK

%% Get all messages sent to the current username
Client 2->>Server: SYNC
Server->>Client 2: MSG red timestamp Private message
Server->>Client 2: ENDSYNC
```

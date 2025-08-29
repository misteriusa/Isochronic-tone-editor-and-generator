import { WebSocketServer } from 'ws';

// Simple signaling server for WebRTC peer discovery.
// Tracks peers by ID and relays SDP/candidate messages.
export function createServer(port = 8080) {
  const peers = new Map();
  const wss = new WebSocketServer({ port });

  wss.on('connection', ws => {
    ws.on('message', msg => {
      try {
        const data = JSON.parse(String(msg));
        if (data.type === 'join') {
          peers.set(data.id, ws);
          // notify existing peers of new join
          broadcast({ type: 'peer-joined', id: data.id }, data.id);
          // send list of peers to the new client
          ws.send(JSON.stringify({ type: 'peers', peers: [...peers.keys()].filter(p => p !== data.id) }));
        } else if (data.type === 'signal' && peers.has(data.target)) {
          // relay signaling data to target peer
          peers.get(data.target)?.send(JSON.stringify({ type: 'signal', from: data.id, signal: data.signal }));
        }
      } catch (err) {
        // ignore malformed messages
      }
    });
    ws.on('close', () => {
      const id = [...peers.entries()].find(([, socket]) => socket === ws)?.[0];
      if (id) {
        peers.delete(id);
        broadcast({ type: 'peer-left', id });
      }
    });
  });

  function broadcast(message, exclude) {
    const data = JSON.stringify(message);
    for (const [id, socket] of peers.entries()) {
      if (id !== exclude) socket.send(data);
    }
  }

  console.log(`Signaling server running on ws://localhost:${port}`);
  return wss;
}

// Auto start if executed directly
if (process.argv[1] === new URL(import.meta.url).pathname) {
  createServer();
}

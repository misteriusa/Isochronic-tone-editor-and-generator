import assert from 'node:assert';
import WebSocket from 'ws';
import SimplePeer from 'simple-peer';
import wrtc from 'wrtc';
import { createServer } from '../server/signaling-server.js';
import { apply, decode, encode } from '../collab/ot.js';

const server = createServer(8090);

function makePeer(id, target, initiator) {
  const ws = new WebSocket('ws://localhost:8090');
  const peer = new SimplePeer({ initiator, wrtc });
  const queue = [];
  ws.on('open', () => { ws.send(JSON.stringify({ type: 'join', id })); queue.splice(0).forEach(m => ws.send(m)); });
  ws.on('error', () => {});
  ws.on('message', data => {
    const msg = JSON.parse(data);
    if (msg.type === 'signal' && msg.from === target) peer.signal(msg.signal);
  });
  peer.on('signal', s => {
    const m = JSON.stringify({ type: 'signal', id, target, signal: s });
    ws.readyState === 1 ? ws.send(m) : queue.push(m);
  });
  peer.on('error', () => {});
  return { ws, peer };
}

async function run() {
  let docB = '';
  const b = makePeer('B', 'A', false);
  await new Promise(r => setTimeout(r, 200));
  const a = makePeer('A', 'B', true);
  b.peer.on('data', d => { docB = apply(docB, decode(d)); });
  await new Promise(r => a.peer.once('connect', r));
  a.peer.send(encode({ type: 'insert', index: 0, text: 'hi' }));
  await new Promise(r => setTimeout(r, 500));
  assert.strictEqual(docB, 'hi');
  a.peer.destroy();
  b.peer.destroy();
  a.ws.close();
  b.ws.close();
  await new Promise(r => setTimeout(r, 100));
  await new Promise(r => server.close(r));
}

run().then(() => { console.log('E2E test passed'); process.exit(0); })
    .catch(err => { console.error(err); process.exit(1); });

// Simple operational transform for string documents.
// Supported ops: {type:'insert', index, text} and {type:'delete', index, count}
// Functions remain <=40 LoC for clarity.

/** Apply an operation to a document string. */
export function apply(doc, op) {
  if (op.type === 'insert') {
    return doc.slice(0, op.index) + op.text + doc.slice(op.index);
  }
  if (op.type === 'delete') {
    return doc.slice(0, op.index) + doc.slice(op.index + op.count);
  }
  return doc;
}

/** Serialize operation for transport over the wire. */
export function encode(op) {
  return JSON.stringify(op);
}

/** Decode operation from serialized form. */
export function decode(data) {
  return JSON.parse(data);
}

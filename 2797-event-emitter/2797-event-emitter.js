class EventEmitter {
  constructor() {
    this.events = new Map();
  }

  subscribe(event, cb) {
    if (!this.events.has(event)) {
      this.events.set(event, []);
    }
    
    const callbacks = this.events.get(event);
    const subscription = {
      callback: cb,
      unsubscribe: () => {
        const index = callbacks.indexOf(cb);
        if (index !== -1) {
          callbacks.splice(index, 1);
        }
      }
    };

    callbacks.push(cb);
    return subscription;
  }

  emit(event, args = []) {
    const callbacks = this.events.get(event);
    if (!callbacks || callbacks.length === 0) {
      return [];
    }
    
    return callbacks.map(cb => cb(...args));
  }
}

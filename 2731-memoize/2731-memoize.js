function memoize(fn) {
  const cache = {};
  const count = {
    callCount: 0,
    getCallCount: 0,
  };

  return function(...args) {
    const key = JSON.stringify(args);

    if (key in cache) {
      count.getCallCount++;
      return cache[key];
    }

    count.callCount++;
    const result = fn(...args);
    cache[key] = result;
    return result;
  };
}

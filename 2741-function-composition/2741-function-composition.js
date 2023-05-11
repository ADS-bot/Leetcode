var compose = function(functions) {
  return function(x) {
    const composition = functions.reduceRight((acc, fn) => (x) => fn(acc(x)), (x) => x);
    return composition(x);
  }
};

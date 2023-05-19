var debounce = function(fn, t) {
    let timerId = null;
  
    return function(...args) {
        clearTimeout(timerId);
      
        timerId = setTimeout(() => {
            fn.apply(this, args);
        }, t);
    };
};

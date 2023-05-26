var flat = function(arr, n) {
  var result = [];

  var flatten = function(arr, depth) {
    for (var i = 0; i < arr.length; i++) {
      if (Array.isArray(arr[i]) && depth < n) {
        flatten(arr[i], depth + 1);
      } else {
        result.push(arr[i]);
      }
    }
  };

  flatten(arr, 0);
  return result;
};

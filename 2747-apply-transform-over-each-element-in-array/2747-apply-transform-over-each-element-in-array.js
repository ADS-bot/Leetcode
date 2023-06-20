var map = function(arr, fn) {
  var resultArray = [];
  for (var i = 0; i < arr.length; i++) {
    resultArray.push(fn(arr[i], i));
  }
  return resultArray;
};

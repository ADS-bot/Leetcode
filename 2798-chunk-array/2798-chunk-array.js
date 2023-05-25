var chunk = function(arr, size) {
  // Check for edge cases
  if (arr.length === 0) {
    return [];
  }

  // Calculate the number of chunks
  var numChunks = Math.ceil(arr.length / size);

  // Create an empty array to store the chunked subarrays
  var chunkedArr = [];

  // Iterate through the array and create subarrays
  for (var i = 0; i < numChunks; i++) {
    // Calculate the starting and ending indices for the current chunk
    var start = i * size;
    var end = start + size;

    // Push the subarray into the chunked array
    chunkedArr.push(arr.slice(start, end));
  }

  return chunkedArr;
};

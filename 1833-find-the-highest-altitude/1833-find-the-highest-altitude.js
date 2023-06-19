var largestAltitude = function(gain) {
    let highest = 0;
    let path = 0

    for (let i = 0; i < gain.length; i++) {
        path += gain[i]
      
        if (path > highest) {
          highest = path   
        }
    }
    return highest
};
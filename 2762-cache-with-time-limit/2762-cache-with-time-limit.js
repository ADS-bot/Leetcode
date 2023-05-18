var TimeLimitedCache = function() {
    this.cache = new Map(); // Use Map to store key-value pairs
};

TimeLimitedCache.prototype.set = function(key, value, duration) {
    var currentTime = Date.now();
    var expirationTime = currentTime + duration;
    
    if (this.cache.has(key) && this.cache.get(key).expirationTime > currentTime) {
        // Key already exists and has not expired yet
        this.cache.get(key).value = value; // Update the value
        this.cache.get(key).expirationTime = expirationTime; // Update the expiration time
        return true;
    } else {
        // Key does not exist or has expired
        this.cache.set(key, { value: value, expirationTime: expirationTime });
        return false;
    }
};

TimeLimitedCache.prototype.get = function(key) {
    var currentTime = Date.now();
    
    if (this.cache.has(key) && this.cache.get(key).expirationTime > currentTime) {
        // Key exists and has not expired
        return this.cache.get(key).value;
    } else {
        // Key does not exist or has expired
        return -1;
    }
};

TimeLimitedCache.prototype.count = function() {
    var currentTime = Date.now();
    var count = 0;
    
    this.cache.forEach(function(entry) {
        if (entry.expirationTime > currentTime) {
            count++;
        }
    });
    
    return count;
};

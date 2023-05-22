var jsonStringify = function(object) {
  if (typeof object === "string") {
    return '"' + object.replace(/"/g, '\\"') + '"';
  } else if (typeof object === "number" || typeof object === "boolean" || object === null) {
    return String(object);
  } else if (Array.isArray(object)) {
    var arrayElements = object.map(function(element) {
      return jsonStringify(element);
    });
    return "[" + arrayElements.join(",") + "]";
  } else if (typeof object === "object") {
    var objectProperties = Object.keys(object).map(function(key) {
      return '"' + key + '":' + jsonStringify(object[key]);
    });
    return "{" + objectProperties.join(",") + "}";
  }
};

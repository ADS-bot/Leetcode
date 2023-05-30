Function.prototype.callPolyfill = function(context, ...args) {
  const key = Symbol(); // Create a unique key to avoid naming collisions

  // Assign the function to the context object with the unique key as the property name
  context[key] = this;

  // Call the function using the unique key as the property name to set the "this" context
  const result = context[key](...args);

  // Remove the function from the context object
  delete context[key];

  // Return the result of the function call
  return result;
};

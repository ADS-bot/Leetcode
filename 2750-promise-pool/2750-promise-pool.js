const promisePool = async function(functions, n) {
  const results = [];
  const inProgress = [];
  let i = 0;

  while (i < functions.length || inProgress.length > 0) {
    while (inProgress.length < n && i < functions.length) {
      const index = i;
      const promise = functions[i]().then((result) => {
        results[index] = result;
        inProgress.splice(inProgress.indexOf(promise), 1);
      });
      inProgress.push(promise);
      i++;
    }

    await Promise.race(inProgress);
  }

  return results;
};

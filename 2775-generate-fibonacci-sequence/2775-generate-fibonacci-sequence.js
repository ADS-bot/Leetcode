var fibGenerator = function*() {

    let prev1 = 0;
    let prev2 = 1;

    while (true) {
      yield prev1;
      [prev1, prev2] = [prev2, prev1+prev2];
    }

};

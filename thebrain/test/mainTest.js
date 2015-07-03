var result = require('../src/js/main');

describe('A suite', function() {
  it('contains spec with an expectation', function() {
    expect(result).toEqual('Hello Bob, how are you today?');
  });
});
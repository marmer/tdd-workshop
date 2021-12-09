import {helloWorld} from "../../main/scripts/helloWorld";

describe("application tests", () => {
  it("should translate", async () => {
    helloWorld([1, 2, 3])
  });
});

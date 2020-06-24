import {helloWorld} from "../../main/scripts/helloWorld";

describe("application tests", () => {
    it("should run", async () => {
        helloWorld([1, 2, 3])
    });
});

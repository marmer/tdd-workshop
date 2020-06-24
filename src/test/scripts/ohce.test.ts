import {InputProvider, Ohce, OutputProcessor} from "../../main/scripts/ohce";

describe("ohce", () => {
    let inputs: string[]
    let outputs: string[]
    let inputProviderMock: InputProvider = jest.fn().mockImplementation(() => inputs.length === 0 ? "Stop!" : inputs.shift())
    let outputProcessorMock: OutputProcessor = jest.fn().mockImplementation((output: string) => outputs.push(output))

    let underTest: Ohce = new Ohce(inputProviderMock, outputProcessorMock)

    beforeEach(() => {
        outputs = []
    });

});

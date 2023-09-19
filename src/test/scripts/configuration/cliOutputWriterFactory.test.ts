import cliOutputWriterFactory from "../../../main/scripts/configuration/cliOutputWriterFactory";
import os from "os";
import fs from "fs"

describe("output file", () => {
    const testOutputDir = `${os.tmpdir()}/cliOutputWriterFactory.test.ts`

    function cleanupTestOutputDir() {
        fs.rmSync(testOutputDir, {recursive: true, force: true})
        fs.mkdirSync(testOutputDir, {recursive: true})
    }

    beforeEach(() => {
        cleanupTestOutputDir()
    });

    it("should create a file writer when the output parameter is set", () => {
        // Arrange
        const outFile = `${testOutputDir}/${expect.getState().currentTestName}.tmp`;

        // Act
        const writer = cliOutputWriterFactory([`-o=${outFile}`]);

        // Assert
        writer("some fun")
        const output = fs.readFileSync(outFile, "utf-8")
        expect(output).toBe("some fun")
    });

    // TODO: marmer 12 Sep 2023 silly arguments in between
    // TODO: marmer 12 Sep 2023 No output file argument
});


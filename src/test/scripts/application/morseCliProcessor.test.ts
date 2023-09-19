import {processCli} from "../../../main/scripts/application/morseCliProcessor";
import * as os from "os"
import * as fs from "fs"
import cliOutputWriterFactory from "../../../main/scripts/configuration/cliOutputWriterFactory";


describe("argument processing", () => {
    let outputs: string[] = []

    const testOutputDir = `${os.tmpdir()}/morseCliProcessor.test.ts`
    function cleanupTestOutputDir() {
        fs.rmSync(testOutputDir, {recursive: true, force: true})
        fs.mkdirSync(testOutputDir, {recursive: true})
    }

    const outputWriter = (input: string) => {
        outputs.push(input)
    }
    const outputWriterFactory = (params: string[]) => {
        return  outputWriter
    }
    beforeEach(() => {
        outputs = []
        cleanupTestOutputDir()
    });

    it("should encode morse codes if String is given", () => {
        // Arrange
        let args = ["The pug"];

        // Act
        processCli({args, outputWriterFactory});

        // Assert
        expect(outputs).toEqual(expect.arrayContaining(["- .... .   .--. ..- --."]))
    });

    it("should decode morse codes if morse is given", () => {
        // Arrange
        let args = ["- .... .   .--. ..- --."];

        // Act
        processCli({args, outputWriterFactory});

        // Assert
        expect(outputs).toEqual(expect.arrayContaining(["THE PUG"]))
    });

    it("should ignore too many parameter", () => {
        // Arrange
        let args = [ "Obelix", "The pug"];

        // Act
        processCli({args, outputWriterFactory});

        // Assert
        expect(outputs).toEqual(expect.arrayContaining(["- .... .   .--. ..- --."]))
    });

    it("should write output into file if file parameter is set",  () => {
        // Arrange
        let outputFile = `${testOutputDir}/morseCliProcessor.test.ts.output`;
        let args = [`-o=${outputFile}`, "The pug"];

        // Act
        processCli({args, outputWriterFactory: cliOutputWriterFactory});

        // Assert
        let fileContent = fs.readFileSync(outputFile, "utf-8");
        expect(fileContent).toBe("- .... .   .--. ..- --.")
    });

    // TODO: marmer 12 Sep 2023 No arguments at all given

    // TODO: marmer 12 Sep 2023 typical file writer errors (file exists allready)
    // TODO: marmer 12 Sep 2023 typical file writer errors (cannot write)

    // TODO: marmer 12 Sep 2023 Strange parameters
    // TODO: marmer 12 Sep 2023 input file and input string given
    // TODO: marmer 12 Sep 2023 multiline input file
    // TODO: marmer 12 Sep 2023 too many parameters given
    // TODO: marmer 12 Sep 2023 Multiple output files
    // TODO: marmer 12 Sep 2023 multiple input files
    // TODO: marmer 12 Sep 2023 Escape strings in case it shall encode a commandline call?


});

import {convert, decode, encode} from "../../../main/scripts/domain/MorseProcessor";

describe("Encoding of Morse", () => {
    [
        {symbol: "A", expected: ".-", note: "Upper case symbol"},
        {symbol: "B", expected: "-...", note: "Upper case symbol"},
        {symbol: "k", expected: "-.-", note: "Lower case symbol"},
        {symbol: "#", expected: "..--..", note: "Unknown Symbol"},
        {symbol: "As", expected: ".- ...", note: "Single word"},
        {symbol: "The pug", expected: "- .... .   .--. ..- --.", note: "Multiple words"},
        {symbol: "", expected: "", note: "empty string"}
    ].forEach(({symbol, expected, note}) => {
        it(`(${note}): should be able to encode from "${symbol}" to "${expected}"`, () => {
            // Arrange

            // Act
            const result = encode(symbol);

            // Assert
            expect(result).toBe(expected);
        })
    })
});

describe("Decoding of morse", () => {
    [
        { symbol: ".-", expected: "A", note: "Upper case symbol"},
        { symbol: "-...", expected: "B", note: "Upper case symbol"},
        { symbol: ".-.-.-.-.-.-.", expected: "?", note: "Unknown Symbol"},
        { symbol: "ukhzft4ei8", expected: "?", note: "Unknown Symbol"},
        { symbol: ".- ...", expected: "AS", note: "Single word"},
        { symbol: "- .... .   .--. ..- --.", expected: "THE PUG", note: "Multiple words"},
        {symbol: "", expected: "", note: "empty string"}
    ].forEach(({symbol, expected, note}) => {
        it(`(${note}): should be able to encode from "${symbol}" to "${expected}"`, () => {
            // Arrange

            // Act
            const result = decode(symbol);

            // Assert
            expect(result).toBe(expected);
        })
    })

});

describe("input recognition and processing", () => {

    it("should recognize simple morse text",  () => {
        // Arrange

        // Act
        const result = convert("- .... .   .--. ..- --.");

        // Assert
        expect(result).toBe("THE PUG")
    });
    it("should recognize simple plain text",  () => {
        // Arrange

        // Act
        const result = convert("THE PUG");

        // Assert
        expect(result).toBe("- .... .   .--. ..- --.")
    });

    it("should recognite mixed input as plain text", async () => {
        // Arrange

        // Act
        const result = convert(". THE PUG");

        // Assert
        expect(result).toBe(".-.-.-   - .... .   .--. ..- --.")
    });
});

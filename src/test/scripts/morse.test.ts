import {morse, translateStringToMorseCode} from "../../main/scripts/morse";

describe("morse Translation", () => {
    describe("translating String to morse", () => {
        it.each([
            {givenString: '9', expectedResult: '----.'},
            {givenString: 'I', expectedResult: '..'},
            {givenString: 'WORD', expectedResult: '.-- --- .-. -..'},
            {givenString: 'TWO WORDS', expectedResult: '- .-- ---   .-- --- .-. -.. ...'},
            {givenString: 'a', expectedResult: '.-'},
            {givenString: '#', expectedResult: '..--..'},
            {givenString: '   \t   ', expectedResult: undefined},
            {givenString: ' ', expectedResult: undefined},
            {givenString: '', expectedResult: undefined},
        ])(`should translate a ($givenString) to ($expectedResult)`, ({givenString, expectedResult}) => {
            // Arrange

            // Act
            const result = translateStringToMorseCode(givenString)

            // Assert
            expect(result).toEqual(expectedResult)
        });
    });
});


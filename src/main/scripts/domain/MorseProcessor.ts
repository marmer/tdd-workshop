const plainToMorseDict = new Map(Object.entries({
    "A": ".-",
    "B": "-...",
    "C": "-.-.",
    "D": "-..",
    "E": ".",
    "F": "..-.",
    "G": "--.",
    "H": "....",
    "I": "..",
    "J": ".---",
    "K": "-.-",
    "L": ".-..",
    "M": "--",
    "N": "-.",
    "O": "---",
    "P": ".--.",
    "Q": "--.-",
    "R": ".-.",
    "S": "...",
    "T": "-",
    "U": "..-",
    "V": "...-",
    "W": ".--",
    "X": "-..-",
    "Y": "-.--",
    "Z": "--..",
    "0": "-----",
    "1": ".----",
    "2": "..---",
    "3": "...--",
    "4": "....-",
    "5": ".....",
    "6": "-....",
    "7": "--...",
    "8": "---..",
    "9": "----.",
    ".": ".-.-.-",
    ",": "--..--",
    "?": "..--..",
    "'": ".----.",
    "!": "-.-.--",
    "/": "-..-.",
    "(": "-.--.",
    ")": "-.--.-",
    "&": ".-...",
    ":": "---...",
    ";": "-.-.-.",
    "=": "-...-",
    "+": ".-.-.",
    "-": "-....-",
    "_": "..--.-",
    "$": "...-..-",
    "@": ".--.-."
}))

const morseToPlainDict = new Map(Object.entries({
    ".-": "A",
    "-...": "B",
    "-.-.": "C",
    "-..": "D",
    ".": "E",
    "..-.": "F",
    "--.": "G",
    "....": "H",
    "..": "I",
    ".---": "J",
    "-.-": "K",
    ".-..": "L",
    "--": "M",
    "-.": "N",
    "---": "O",
    ".--.": "P",
    "--.-": "Q",
    ".-.": "R",
    "...": "S",
    "-": "T",
    "..-": "U",
    "...-": "V",
    ".--": "W",
    "-..-": "X",
    "-.--": "Y",
    "--..": "Z",
    "-----": "0",
    ".----": "1",
    "..---": "2",
    "...--": "3",
    "....-": "4",
    ".....": "5",
    "-....": "6",
    "--...": "7",
    "---..": "8",
    "----.": "9",
    ".-.-.-": ".",
    "--..--": ",",
    "..--..": "?",
    ".----.": "'",
    "-.-.--": "!",
    "-..-.": "/",
    "-.--.": "(",
    "-.--.-": ")",
    ".-...": "&",
    "---...": ":",
    "-.-.-.": ";",
    "-...-": "=",
    ".-.-.": "+",
    "-....-": "-",
    "..--.-": "_",
    "...-..-": "$",
    ".--.-.": "@"
}))

function toMorseSymbol(plainSymbol: string) {
    return plainToMorseDict.get(plainSymbol.toUpperCase()) || "..--..";
}

function toMorseWord(plainTextWord: string) {
    return plainTextWord.split("")
        .map(toMorseSymbol)
        .join(" ");
}

function encode(plainText: string) {
    return plainText.split(" ")
        .map(toMorseWord)
        .join("   ")
}

function toPlainSymbol(morseSymbol: string) {
    return morseToPlainDict.get(morseSymbol) || "?";
}

function toPlainWord(morseText: string) {
    return morseText.split(" ")
        .map(toPlainSymbol)
        .join("");
}

function decode(morseText: string) {
    if (morseText.length == 0) {
        return ""
    }

    return morseText.split("   ")
        .map(toPlainWord)
        .join(" ")
}


function isMorse(input: string) {
    return input.match(/^[\s.-]+$/);
}

function convert(input: any) {
    return isMorse(input) ?
        decode(input) :
        encode(input);
}

export {decode, encode, convert}

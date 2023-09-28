export function morse(args: any[]) {
    args.forEach(value => console.log("Hello World " + value))
}

const charToMorseDict: Record<string, string> = {
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
    "@": ".--.-.",
}

// Business Morse
const LONG_PAUSE = "   ";
const SHORT_PAUSE = " ";
const UNDEFINED_CHAR = charToMorseDict["?"]

//Business Plain
const PLAIN_SPACE = " ";

const toMorseChar = (char: string) =>
    charToMorseDict[char.toUpperCase()] ??
    UNDEFINED_CHAR;

const splitToChars = (word: string) => word.split("");

const toMorseWord = (word: string) =>
    splitToChars(word)
        .map(toMorseChar)
        .join(SHORT_PAUSE);

const splitToWords = (plainText: string) => plainText.split(PLAIN_SPACE);

export const translateStringToMorseCode = (plainText: string): string | undefined => {
    if (plainText.trim().length === 0) {
        return undefined
    }

    return splitToWords(plainText)
        .map(toMorseWord)
        .join(LONG_PAUSE);
};

// * Given: a message-string has to be translated into a morse-string
//   * Basic assumption: No one will pass messages with a length smaller than two characters
// * (incomplete) Dictionary: see branch "morse" /src/main/resources
// * Unknown Symbols are translated into the question-mark-morse-code (..--..)
// * A Morse code is structured as follows:
//   * A Symbol is represented by dots and dashes
//   * There is a space between dots and dashes(short pause)
//   * There are three spaces between words (long pause)
//   * Example: "The pug" = "- .... .   .--. ..- --."

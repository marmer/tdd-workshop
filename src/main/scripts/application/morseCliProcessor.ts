import {convert} from "../domain/MorseProcessor";

export function processCli({args, outputWriterFactory}: {
    args: string[],
    outputWriterFactory: (input: string[]) => (input: string) => void
}) {
    let outputWriter = outputWriterFactory(args );
    outputWriter(convert(args[args.length-1]))
}


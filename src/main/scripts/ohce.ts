export type OutputProcessor = (input: string) => void
export type InputProvider = () => string

export class Ohce {
  private inputProvider: InputProvider;
  private outputProcessor: OutputProcessor;

  constructor(inputProvider: InputProvider, outputProcessor: OutputProcessor) {
    this.inputProvider = inputProvider;
    this.outputProcessor = outputProcessor;
  }
}

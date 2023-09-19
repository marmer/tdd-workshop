#!/usr/bin/env node
import {processCli} from "./application/morseCliProcessor";
import  "./configuration/cliOutputWriterFactory";
import outputWriterFactory from "./configuration/cliOutputWriterFactory";

processCli({args: process.argv.slice(2), outputWriterFactory})

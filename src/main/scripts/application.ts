#!/usr/bin/env node

import readline from "readline-sync"
import {Ohce} from "./ohce";

new Ohce(readline.question, console.log)

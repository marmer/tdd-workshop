import fs from "fs"

function write(fileName: string, content: string) {
    fs.writeFileSync(fileName, content, {
        encoding: "utf-8",
        flag: "w"
    })
}


export default (params: string[]) => {
    let fileName = params[0].substring(3);

    return (input : string) => write(fileName, input)
}

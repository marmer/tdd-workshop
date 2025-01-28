import {Client} from "pg";


// ####
// One QUESTION: Can I push this to main without annoying anyone?
// One PHILOSOPHY: "for each desired change, make the change easy (warning: this may be hard), then make the easy change" (Kent Beck)
// ####


export async function allmightyEverything(args: any[]) {
    const userName = "myUserName";
    const password = "myPW";
    const dbName = "myDB";
    const host = 'localhost';
    const port = 5432

    let allmightyEverything = new AllmightyEverything(userName, password, dbName, host, port);

    console.log("Current fun: " + await allmightyEverything.getLastTranslation());

    //for each argument
    for (const element of args) {
        console.log("New fun: " + await allmightyEverything.doTheMagic(element));
    }
}

export class AllmightyEverything {
    private readonly client
    private isConnected: boolean = false;

    constructor(userName: string, password: string, dbName: string, host: string, port: number) {
        this.client = new Client({
            user: userName,
            host: host,
            database: dbName,
            password: password,
            port: port
        });
    }

    async getLastTranslation(): Promise<string | null> {
        try {
            if (!this.isConnected) {
                await this.client.connect();
                this.isConnected = true;
            }

            const query = `
                SELECT translated
                FROM my_table
                ORDER BY id DESC LIMIT 1;
            `;

            const result = await this.client.query(query);

            if (result.rows.length > 0) {
                return result.rows[0].translated;
            } else {
                return null;
            }
        } catch (err) {
            console.log("Error reading Data", err)
            return null;
        }
    }

    async doTheMagic(inputString: string) {
        try {
            if (!this.isConnected) {
                await this.client.connect();
                this.isConnected = true;
            }

            const query = 'INSERT INTO my_table (plain, translated) VALUES ($1, $2)';
            let translated = inputString.split('').reverse().join('');
            const values = [inputString, translated];

            await this.client.query(query, values);
            return translated

        } catch (err) {
            console.error('Error: Cannot insert Data:', err);
        }
    }

}

const {Client} = require('pg');

// Verbindungskonfiguration

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

    constructor(userName: string, password: string, dbName: string, host: string = 'localhost', port: number = 5432) {
        this.client = new Client({
            user: userName, // Dein PostgreSQL-Benutzername
            host: host,        // Hostname der DB (z. B. localhost)
            database: dbName, // Name der Datenbank
            password: password,   // Dein Passwort
            port: port                // Standard-Port von PostgreSQL
        });
    }

    async getLastTranslation(): Promise<string | null> {
        try {
            // Verbindung herstellen
            if (!this.isConnected) {
                await this.client.connect();
                this.isConnected = true;
            }

            // SQL-Abfrage
            const query = `
                SELECT translated
                FROM my_table
                ORDER BY id DESC LIMIT 1;
            `;

            // Abfrage ausführen
            const result = await this.client.query(query);

            // Ergebnis prüfen und ausgeben
            if (result.rows.length > 0) {
                return result.rows[0].translated;
            } else {
                return null;
            }
        } catch (err) {
            console.log(err)
            return null;
        }
    }

    async doTheMagic(inputString: string) {
        let translated = inputString.split('').reverse().join('');
        try {
            // Verbindung herstellen
            if (!this.isConnected) {
                await this.client.connect();
                this.isConnected = true;
            }

            // SQL-Befehl zum Einfügen
            const query = 'INSERT INTO my_table (plain, translated) VALUES ($1, $2)';
            const values = [inputString, translated];

            // Abfrage ausführen
            await this.client.query(query, values);

        } catch (err) {
            console.error('Fehler beim Einfügen der Daten:', err);
        }
        return translated
    }

}

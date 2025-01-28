import {AllmightyEverything} from "../../main/scripts/AllmightyEverything";

describe("AllmightyEverything", () => {
    it("should serve last persisted value when on a fresh initialization", async () => {
        let userName = "myUserName";
        let password = "myPW";
        let dbName = "myDB";

        const underTest = new AllmightyEverything(userName, password, dbName);
        await underTest.doTheMagic("John Doe");
        expect(await underTest.doTheMagic("Jane Doe")).toBe("eoD enaJ");

        expect(await underTest.getLastTranslation()).toBe("eoD enaJ");

        const underTest2 = new AllmightyEverything(userName, password, dbName);
        expect(await underTest.getLastTranslation()).toBe("eoD enaJ");
    });
});


// TODO: marmer 2025-01-28 parameters change
// TODO: marmer 2025-01-28 return value change
// TODO: marmer 2025-01-28 implementation change . mehrere Implementierungen
// TODO: marmer 2025-01-28 feature toggle
// TODO: marmer 2025-01-28 decoupling

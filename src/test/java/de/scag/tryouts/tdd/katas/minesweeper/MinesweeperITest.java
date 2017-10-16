package de.scag.tryouts.tdd.katas.minesweeper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.TemporaryFolder;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import java.net.URISyntaxException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;


// Spring 1.3 Weg
// @RunWith(SpringJUnit4ClassRunner.class)
// @SpringApplicationConfiguration(
// { Minesweeper.class, FieldProcessor.class, MiningFileService.class }
// )

// Spring 1.5 Weg
@RunWith(SpringRunner.class)
@SpringBootTest( /*properties = {"job.autorun.enabled=false"} wenn Bedingung an Config ist*/)

// 1. Möglichkeit zusammen mit @Profile("!test") (siehe Klasse Config) den Commandlinerunner zu
// unterdrücken. In Spring 1.3 nicht nötig
@ActiveProfiles("test")
// Der Annotation SpringBootTest können auch ausgewählte Klassen mitgegeben werden. In dem Fall
// werden ausschließlich diese verwendet
public class MinesweeperITest {
    @Autowired
    private Minesweeper classUnderTest;

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    @Test
    public void testRun_DateiMitGueltigemMinenfeldGegeben_SollteDateiMitAufgeloestemMinenfeldErzeugen()
        throws Exception {
        // Vorbereitung
        final Path inputFile = getResourcePathOf("examples/komplex_setup.mines");
        final File outputFile = tmp.newFile("outputFile.mines");

        // Ausführung
        classUnderTest.run(inputFile.toString(), outputFile.toString());

        // Prüfung
        final List<String> expectedOutputBytes = Files.readAllLines(
                getResourcePathOf("examples/komplex_solution.mines"));
        final List<String> outputBytes = Files.readAllLines(outputFile.toPath());

        assertThat(outputBytes, is(equalTo(expectedOutputBytes)));
    }

    private Path getResourcePathOf(final String resourcePath) throws URISyntaxException {
        return Paths.get(getClass().getResource(resourcePath).toURI());
    }
}

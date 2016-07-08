package de.scag.tryouts.tdd.katas.minesweeper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.TemporaryFolder;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.SpringApplicationConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import java.net.URISyntaxException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(
    { Minesweeper.class, FieldProcessor.class, MiningFileService.class }
)
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

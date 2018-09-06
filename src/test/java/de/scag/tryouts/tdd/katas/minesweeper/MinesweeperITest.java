package de.scag.tryouts.tdd.katas.minesweeper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MinesweeperITest {
    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();
    @Autowired
    private Minesweeper classUnderTest;

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

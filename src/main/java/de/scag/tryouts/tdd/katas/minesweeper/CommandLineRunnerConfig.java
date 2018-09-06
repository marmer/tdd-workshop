package de.scag.tryouts.tdd.katas.minesweeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("!test")
public class CommandLineRunnerConfig {
    @Autowired
    private Minesweeper minesweeper;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
                @Override
                public void run(final String... args) throws Exception {
                    minesweeper.run(args);
                }
            };
    }
}

package de.scag.tryouts.tdd.katas.minesweeper;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("!test")
@RequiredArgsConstructor
public class CommandLineRunnerConfig {
    private final Minesweeper minesweeper;

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

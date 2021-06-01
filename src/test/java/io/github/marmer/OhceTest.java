package io.github.marmer;

import java.util.function.Consumer;
import java.util.function.Supplier;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OhceTest {

    @InjectMocks
    private Ohce underTest;

    @Mock
    private Supplier<String> inputSupplier;

    @Mock
    private Consumer<String> outputSupplier;
    
}

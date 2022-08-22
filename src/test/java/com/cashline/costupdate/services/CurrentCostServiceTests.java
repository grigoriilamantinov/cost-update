package com.cashline.costupdate.services;

import com.cashline.costupdate.dto.CostDTO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class CurrentCostServiceTests {

    @Autowired  CostService costService;

    @ParameterizedTest
    @MethodSource("methodDataProvider")
    void shouldGetListOfMergedCosts(
        final List<CostDTO> currentCosts,
        final List<CostDTO> newCosts,
        final List<CostDTO> exceptedResult
    ) {
        final var actualResult= costService.getCostsForSave(newCosts, currentCosts);

        Assertions.assertIterableEquals(exceptedResult, actualResult);
        Assertions.assertEquals(exceptedResult, actualResult);
    }

    static Stream<Arguments> methodDataProvider() {
        final var currentCostsDataFromTestCase = List.of(
            (new CostDTO(1, "122856", 1, 1, new Date(1_356_984_000_000L), new Date(1_359_662_399_000L), 11000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_357_761_600_000L), new Date(1_358_711_999_000L), 99000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_356_984_000_000L), new Date(1_359_576_000_000L), 5000))
        );
        final var newCostsDataFromTestCase = List.of(
            (new CostDTO(1, "122856", 1, 1, new Date(1_358_625_600_000L),new Date(1_361_390_399_000L),11000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_358_193_600_000L), new Date(1_359_143_999_000L), 92000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_357_934_400_000L), new Date(1_358_020_800_000L), 4000))
        );
        final var exceptedResultDataFromTestCase = List.of(
            (new CostDTO(1, "122856", 1, 1, new Date(1_356_984_000_000L), new Date(1_361_390_399_000L), 11000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_357_761_600_000L), new Date(1_358_193_600_000L), 99000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_356_984_000_000L), new Date(1_357_934_400_000L), 5000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_358_193_600_000L), new Date(1_359_143_999_000L), 92000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_358_020_800_000L), new Date(1_359_576_000_000L), 5000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_357_934_400_000L), new Date(1_358_020_800_000L), 4000))
        );

        final var currentCostsSeveralNumber = List.of(
            (new CostDTO(1, "122856", 1, 1, new Date(1_356_984_000_000L), new Date(1_359_662_399_000L), 11000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_357_761_600_000L), new Date(1_358_711_999_000L), 99000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_356_984_000_000L), new Date(1_359_576_000_000L), 5000))
        );
        final var newCostsSeveralNumber = List.of(
            (new CostDTO(1, "122856", 1, 1, new Date(1_358_625_600_000L),new Date(1_361_390_399_000L),11000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_358_193_600_000L), new Date(1_359_143_999_000L), 92000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_357_934_400_000L), new Date(1_358_020_800_000L), 4000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_359_662_400_000L), new Date(1_360_094_399_000L), 80000))
        );
        final var exceptedResultSeveralNumber = List.of(
            (new CostDTO(1, "122856", 1, 1, new Date(1_356_984_000_000L), new Date(1_361_390_399_000L), 11000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_357_761_600_000L), new Date(1_358_193_600_000L), 99000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_356_984_000_000L), new Date(1_357_934_400_000L), 5000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_358_193_600_000L), new Date(1_359_143_999_000L), 92000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_358_020_800_000L), new Date(1_359_576_000_000L), 5000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_357_934_400_000L), new Date(1_358_020_800_000L), 4000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_359_662_400_000L), new Date(1_360_094_399_000L), 80000))
        );

        final var currentCostsNewProductCode = List.of(
            (new CostDTO(1, "122856", 1, 1, new Date(1_356_984_000_000L), new Date(1_359_662_399_000L), 11000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_357_761_600_000L), new Date(1_358_711_999_000L), 99000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_356_984_000_000L), new Date(1_359_576_000_000L), 5000))
        );
        final var newCostsNewProductCode = List.of(
            (new CostDTO(1, "122856", 1, 1, new Date(1_358_625_600_000L),new Date(1_361_390_399_000L),11000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_358_193_600_000L), new Date(1_359_143_999_000L), 92000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_357_934_400_000L), new Date(1_358_020_800_000L), 4000)),
            (new CostDTO(1, "88889", 1, 1, new Date(1_359_662_400_000L), new Date(1_360_094_399_000L), 80000))
        );
        final var exceptedResultNewProductCode = List.of(
            (new CostDTO(1, "122856", 1, 1, new Date(1_356_984_000_000L), new Date(1_361_390_399_000L), 11000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_357_761_600_000L), new Date(1_358_193_600_000L), 99000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_356_984_000_000L), new Date(1_357_934_400_000L), 5000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_358_193_600_000L), new Date(1_359_143_999_000L), 92000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_358_020_800_000L), new Date(1_359_576_000_000L), 5000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_357_934_400_000L), new Date(1_358_020_800_000L), 4000)),
            (new CostDTO(1, "88889", 1, 1, new Date(1_359_662_400_000L), new Date(1_360_094_399_000L), 80000))
        );

        final var currentCostsNewProductCodeSeveralNumber = List.of(
            (new CostDTO(1, "122856", 1, 1, new Date(1_356_984_000_000L), new Date(1_359_662_399_000L), 11000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_357_761_600_000L), new Date(1_358_711_999_000L), 99000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_356_984_000_000L), new Date(1_359_576_000_000L), 5000))
        );
        final var newCostsNewProductCodeSeveralNumber = List.of(
            (new CostDTO(1, "122856", 1, 1, new Date(1_358_625_600_000L),new Date(1_361_390_399_000L),11000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_358_193_600_000L), new Date(1_359_143_999_000L), 92000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_357_934_400_000L), new Date(1_358_020_800_000L), 4000)),
            (new CostDTO(1, "88889", 1, 1, new Date(1_359_662_400_000L), new Date(1_360_094_399_000L), 80000)),
            (new CostDTO(1, "88889", 2, 1, new Date(1_359_748_800_000L), new Date(1_359_835_199_000L), 72000))
        );
        final var exceptedResultNewProductCodeSeveralNumber = List.of(
            (new CostDTO(1, "122856", 1, 1, new Date(1_356_984_000_000L), new Date(1_361_390_399_000L), 11000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_357_761_600_000L), new Date(1_358_193_600_000L), 99000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_356_984_000_000L), new Date(1_357_934_400_000L), 5000)),
            (new CostDTO(1, "122856", 2, 1, new Date(1_358_193_600_000L), new Date(1_359_143_999_000L), 92000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_358_020_800_000L), new Date(1_359_576_000_000L), 5000)),
            (new CostDTO(1, "6654", 1, 2, new Date(1_357_934_400_000L), new Date(1_358_020_800_000L), 4000)),
            (new CostDTO(1, "88889", 1, 1, new Date(1_359_662_400_000L), new Date(1_360_094_399_000L), 80000)),
            (new CostDTO(1, "88889", 2, 1, new Date(1_359_748_800_000L), new Date(1_359_835_199_000L), 72000))
        );

        return Stream.of(
            arguments(currentCostsDataFromTestCase, newCostsDataFromTestCase, exceptedResultDataFromTestCase),
            arguments(currentCostsSeveralNumber, newCostsSeveralNumber, exceptedResultSeveralNumber),
            arguments(currentCostsNewProductCode, newCostsNewProductCode, exceptedResultNewProductCode),
            arguments(currentCostsNewProductCodeSeveralNumber, newCostsNewProductCodeSeveralNumber, exceptedResultNewProductCodeSeveralNumber)
        );
    }
}
package pt.ipp.isep.dei.esoft.project.domain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class QualityChecksTest {

    private QualityChecks qualityChecks;

    @BeforeEach
    void setUp() {
        qualityChecks = new QualityChecks();
    }

    @Test
    void testFillOperationsPriorityQueue() {
        PriorityQueue<Map<Integer, List<ID>>> priorityQueue = qualityChecks.fillOperationsPriorityQueue();

        assertNotNull(priorityQueue);
        assertFalse(priorityQueue.isEmpty());

        List<Integer> heights = new ArrayList<>();
        for (Map<Integer, List<ID>> map : priorityQueue) {
            heights.add(map.keySet().iterator().next());
        }
        List<Integer> sortedHeights = new ArrayList<>(heights);
        Collections.sort(sortedHeights);
        assertEquals(sortedHeights, heights);
    }

    @Test
    void testPrepareQualityChecks() {
        Map<Map<Integer, List<ID>>, Boolean> checksMap = qualityChecks.prepareQualityChecks();
        assertNotNull(checksMap);
        assertFalse(checksMap.isEmpty());

        for (Boolean value : checksMap.values()) {
            assertFalse(value);
        }
    }

    @Test
    void testPerformQualityChecks_Successful() {
        boolean simulatorActivated = true;
        String confirmation = "y";

        //qualityChecks.performQualityChecks(confirmation, simulatorActivated);

        Map<Map<Integer, List<ID>>, Boolean> checkedOperations = qualityChecks.checkedOperations;
        for (Boolean value : checkedOperations.values()) {
            assertTrue(value);
        }
    }

    @Test
    void testPerformQualityChecks_NotActivated() {
        boolean simulatorActivated = false;
        String confirmation = "y";

        qualityChecks.performQualityChecks(confirmation, simulatorActivated);

        assertTrue(qualityChecks.checkedOperations.isEmpty());
    }

    @Test
    void testPerformQualityChecks_NoConfirmation() {
        boolean simulatorActivated = true;
        String confirmation = "n";

        qualityChecks.performQualityChecks(confirmation, simulatorActivated);

        assertTrue(qualityChecks.checkedOperations.isEmpty());
    }
}

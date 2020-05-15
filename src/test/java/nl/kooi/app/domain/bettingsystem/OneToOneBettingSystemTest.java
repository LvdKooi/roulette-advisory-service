package nl.kooi.app.domain.bettingsystem;

import lombok.var;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OneToOneBettingSystemTest {

    private OneToOneBettingSystem bettingSystem;

    @Before
    public void initTestDependencies() {
        bettingSystem = new OneToOneBettingSystem(2, 4);
    }

    @Test
    public void firstAdviceTest() {
        var hitArray = new boolean[]{true, false};

        setHitsMultipleTimes(hitArray, 3);

        bettingSystem.setHits(hitArray);

        assertThat("Advice doesn't match expectation", bettingSystem.getAdviceArray(), equalTo(new int[]{0, 1}));
    }

    @Test
    public void firstAdviceThenLoseTest() {
        var hitArray = new boolean[]{true, false};

        setHitsMultipleTimes(hitArray, 4);

        assertThat("Advice doesn't match expectation", bettingSystem.getAdviceArray(), equalTo(new int[]{0, 1}));

        bettingSystem.setHits(hitArray);

        assertThat("Advice doesn't match expectation", bettingSystem.getAdviceArray(), equalTo(new int[]{0, 2}));
    }

    @Test
    public void firstAdviceThenLoseTwoTimesZeroTest() {
        var hitArray = new boolean[]{true, false};

        setHitsMultipleTimes(hitArray, 4);

        assertThat("Advice doesn't match expectation", bettingSystem.getAdviceArray(), equalTo(new int[]{0, 1}));

        hitArray = new boolean[]{false, false};

        bettingSystem.setHits(hitArray);

        assertThat("Advice doesn't match expectation", bettingSystem.getAdviceArray(), equalTo(new int[]{0, 2}));

        bettingSystem.setHits(hitArray);

        assertThat("Advice doesn't match expectation", bettingSystem.getAdviceArray(), equalTo(new int[]{0, 4}));
    }


    @Test
    public void firstAdviceThenWinAndAnotherChanceTest() {
        var hitArray = new boolean[]{true, false};

        setHitsMultipleTimes(hitArray, 4);

        assertThat("Advice doesn't match expectation", bettingSystem.getAdviceArray(), equalTo(new int[]{0, 1}));

        hitArray = new boolean[]{false, true};

        bettingSystem.setHits(hitArray);

        assertThat("Advice doesn't match expectation", bettingSystem.getAdviceArray(), equalTo(new int[]{0, 1}));
    }

    @Test
    public void firstAdviceThreeOutOfFour() {
        var hitArray = new boolean[]{true, false};

        setHitsMultipleTimes(hitArray, 3);

        hitArray = new boolean[]{false, true};

        bettingSystem.setHits(hitArray);

        assertThat("Advice doesn't match expectation", bettingSystem.getAdviceArray(), equalTo(new int[]{0, 1}));
    }

    @Test
    public void noAdviceTwoOutOfFour() {
        var hitArray = new boolean[]{true, false};

        setHitsMultipleTimes(hitArray, 2);

        hitArray = new boolean[]{false, true};

        setHitsMultipleTimes(hitArray, 2);

        assertThat("Advice doesn't match expectation", bettingSystem.getAdviceArray(), equalTo(new int[]{0, 0}));
    }

    private void setHitsMultipleTimes(boolean[] hitArray, int times) {
        for (int i = 0; i < times; i++) {
            bettingSystem.setHits(hitArray);
        }
    }

}

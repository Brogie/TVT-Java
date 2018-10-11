import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.company.Vote2TestingBoogaloo;


public class VoteTests {

    @Test
    public void sendNullVote(){
        //final Vote2TestingBoogaloo vote2TestingBoogaloo;

        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            final Vote2TestingBoogaloo vote2TestingBoogaloo = new Vote2TestingBoogaloo(null);
        });

        assertEquals("Null votes are not accepted", exception.getMessage());
    }

    @Test
    public void getFirstVote(){
        //Arrange
        final String expected = "First";

        //Act
        String[] votes = new String[]{"First", "Second", "Third", "Fourth", "Fifth"};
        Vote2TestingBoogaloo vote2TestingBoogaloo = new Vote2TestingBoogaloo(votes);
        String actual = vote2TestingBoogaloo.getFirst();

        //Assert
        assertEquals(expected, actual);
    }
}

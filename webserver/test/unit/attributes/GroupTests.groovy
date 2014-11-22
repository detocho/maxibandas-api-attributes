package attributes



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Group)
class GroupTests {

    def registeredGroup
    def sampleGroup

    @Before
    void setUp(){

        registeredGroup = new Group(
                name:"features"
        )

        mockForConstraintsTests(Group, [registeredGroup])

        sampleGroup = new  Group(
                name:"schedule"
        )
    }

    void testGroupIsInvalidWithNameRepeat(){
        sampleGroup.name = "features"
        assertFalse(sampleGroup.validate())
        assertEquals('unique', sampleGroup.errors['name'])
    }

    void testGroupIsValid(){
        assertTrue(sampleGroup.validate())
        assertEquals('schedule', sampleGroup.name)
    }
}

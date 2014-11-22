package attributes



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Attribute)
class AttributeTests {

    def registeredAttribute
    def sampleAttribute
    def registeredGroup

    @Before
    void setUp(){

        def value01 = 'AGS'
        def value02 = 'BCS'
        def value03 = 'HDG'

        def value04 = 'Lunes'
        def value05 = 'Sabado'

        def value06 = 'Tarde'
        def value07 = 'Noche'


        registeredGroup = new Group(
                name:'feature'
        )

        mockForConstraintsTests(Group, [registeredGroup])

        registeredAttribute = new Attribute(

                attributeId: 'attr-locationService',
                categoryId: 'MX2MB2',
                attributeName: 'Localidades de servicio',
                valueType: 'multiple_list',
                valueId: '01',
                group: registeredGroup

        )

        registeredAttribute.value.add(value01)
        registeredAttribute.value.add(value02)

        mockForConstraintsTests(Attribute,[registeredAttribute])

        sampleAttribute = new Attribute(

                attributeId: 'attr-scheduleServiceDay',
                categoryId: 'MX2MB2',
                attributeName: 'Horario de servicio',
                valueType: 'multiple_list',
                valueId: '02',
                group: registeredGroup

        )

        registeredAttribute.value.add(value04)
        registeredAttribute.value.add(value05)


    }

    void test_AttributeIsNotValidWithAttrIdRepeat(){
        sampleAttribute.attributeId = 'attr-locationService'
        assertFalse(sampleAttribute.validate())
        assertEquals('unique', sampleAttribute.errors['attributeId'])
    }

    void test_AttributeIsValid(){
        assertTrue(sampleAttribute.validate())
        sampleAttribute.save()
        assertEquals('feature', sampleAttribute.group.name)
    }
}

import attributes.Group
import attributes.Attribute

class BootStrap {
    def init = { servletContext ->
        test{

        }
        development{



            if (Group.count() == 0){

                def registeredGroup = new Group(
                        name:"features"
                )

                registeredGroup.save()

            }

            if (Attribute.count() == 0){

                def group = Group.findById(1)

                def registeredAttribute = new Attribute(

                        attributeId: 'attr-locationService',
                        categoryId: 'MX1',
                        attributeName: 'Localidades de servicio',
                        valueType: 'multiple_list',
                        valueId: '01',
                        group: group

                )

                registeredAttribute.save()
            }

        }
        production{

        }
    }
    def destroy = {
    }
}

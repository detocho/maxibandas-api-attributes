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

        }
        production{

        }
    }
    def destroy = {
    }
}

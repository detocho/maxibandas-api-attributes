package attributes

import java.text.MessageFormat
import org.apache.ivy.plugins.conflict.ConflictManager
import grails.converters.*
import attributes.exceptions.NotFoundException
import attributes.exceptions.ConflictException
import attributes.exceptions.BadRequestException
import javax.servlet.http.HttpServletResponse

class AttributeService {

    static transactional = "mongo"
    def restService

    def getAttributes(def params){

        Map jsonResult      = [:]
        def jsonAttributes  = []


        if (!params.categoryId){
            throw new NotFoundException("You must provider the category_id")
        }


        def attributes = Attribute.findAllByCategoryId(params.categoryId)


        attributes.each{
            jsonAttributes.add(
                    attribute_id    : it.attributeId,
                    attribute_name  : it.attributeName,
                    value_type      : it.valueType,
                    value_id        : it.valueId,
                    value           : it.value,
                    group_name      : it.group.name

            )
        }

        jsonResult.category_id  = params.categoryId
        jsonResult.attributes   = jsonAttributes

        jsonResult

    }



    def createAttribute(def jsonAttribute){

        Map jsonResult = [:]
        def responseMenssage = ''

        if (!jsonAttribute?.attribute_id){
            throw new BadRequestException("You must provider attribute_id")
        }
        if (!jsonAttribute?.attribute_name){
            throw new BadRequestException("You must provider attribute_name")
        }
        if (!jsonAttribute?.value_id){
            throw new BadRequestException("You must provider value_id")
        }
        if (!jsonAttribute?.group_name){
            throw new BadRequestException("You must provider group_name")
        }
        if (!jsonAttribute?.category_id){
            throw new BadRequestException("You must provider category_id")
        }

        // hacemos el validate del value

        def category = validCategory(jsonAttribute?.category_id)

        def group = Group.findByName(jsonAttribute?.group_name)

        if(!group){
            throw  new BadRequestException("The group with name = "+jsonAttribute?.group_name+" not found")
        }

        def newAttribute = new Attribute(

                attributeId     : jsonAttribute?.attribute_id,
                categoryId      : jsonAttribute?.category_id,
                attributeName   : jsonAttribute?.attribute_name,
                valueType       : jsonAttribute?.value_type,
                valueId         : jsonAttribute?.value_id,
                value           : jsonAttribute?.value ? jsonAttribute?.value : [],
                group           : group
        )



        if(!newAttribute.validate()){
            newAttribute.errors.allErrors.each{
                responseMenssage+=MessageFormat.format(it.defaultMessage,it.arguments) + " "
            }
            throw new BadRequestException(responseMenssage)
        }

        newAttribute.save()

        jsonResult.attribute_id    = newAttribute.attributeId
        jsonResult.attribute_name  = newAttribute.attributeName
        jsonResult.value_type      = newAttribute.valueType
        jsonResult.value_id        = newAttribute.valueId
        jsonResult.value           = newAttribute.value
        jsonResult.group_name      = newAttribute.group.name
        jsonResult.category        = category

        jsonResult


    }


    def modifyAttribute(def params, def jsonAttribute){

        Map jsonResult = [:]
        def responseMenssage = ''

        if (!params.attributeId){
            throw new BadRequestException("You must provider attribute_id")
        }
        if (!jsonAttribute?.attribute_name){
            throw new BadRequestException("You must provider attribute_name")
        }
        if (!jsonAttribute?.value_id){
            throw new BadRequestException("You must provider value_id")
        }
        if (!jsonAttribute?.group_name){
            throw new BadRequestException("You must provider group_name")
        }
        if (!jsonAttribute?.category_id){
            throw new BadRequestException("You must provider category_id")
        }

        // hacemos el validate del value

        def category = validCategory(jsonAttribute?.category_id)
        def group = Group.findByName(jsonAttribute?.group_name)

        if(!group){
            throw  new BadRequestException("The group with name = "+jsonAttribute?.group_name+" not found")
        }

        def obteinedAttribute = Attribute.findByAttributeId(params.attributeId)

        if(!obteinedAttribute){
            throw new NotFoundException("The attribute with attribute_id = "+params.attributeId+" not found")
        }


        obteinedAttribute.categoryId      = jsonAttribute?.category_id
        obteinedAttribute.attributeName   = jsonAttribute?.attribute_name
        obteinedAttribute.valueType       = jsonAttribute?.value_type
        obteinedAttribute.valueId         = jsonAttribute?.value_id
        obteinedAttribute.value           = jsonAttribute?.value ? jsonAttribute?.value : []
        obteinedAttribute.group           = group



        if(!obteinedAttribute.validate()){
            obteinedAttribute.errors.allErrors.each{
                responseMenssage+=MessageFormat.format(it.defaultMessage,it.arguments) + " "
            }
            throw new BadRequestException(responseMenssage)
        }

        obteinedAttribute.save()

        jsonResult.attribute_id    = obteinedAttribute.attributeId
        jsonResult.attribute_name  = obteinedAttribute.attributeName
        jsonResult.value_type      = obteinedAttribute.valueType
        jsonResult.value_id        = obteinedAttribute.valueId
        jsonResult.value           = obteinedAttribute.value
        jsonResult.group_name      = obteinedAttribute.group.name
        jsonResult.category        = category

        jsonResult


    }

    def validCategory(def categoryId){

        def result = restService.getResource("/categories/"+categoryId+"/")

        if(result.status != HttpServletResponse.SC_OK){
            throw new BadRequestException("The category_id = "+categoryId+" not found in categories api")
        }

        result.data

    }
}

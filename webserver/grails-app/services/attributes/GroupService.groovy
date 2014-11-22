package attributes

import java.text.MessageFormat
import org.apache.ivy.plugins.conflict.ConflictManager
import grails.converters.*
import attributes.exceptions.NotFoundException
import attributes.exceptions.ConflictException
import attributes.exceptions.BadRequestException

class GroupService {
    static transactional = "mongo"

    def getGroup(def params){

        Map jsonResult              = [:]
        def groups
        def jsonGroups              = []


        if (!params.groupId){

            groups = Group.findAll()
        }else{
            groups = Group.findById(params.groupId)
            if(!groups){
                throw new NotFoundException("The Group with id="+params.groupId+" notFound")
            }
        }


        groups.each{
            jsonGroups.add(
                    id      : it.id,
                    name    : it.name
            )
        }


        jsonResult.groups = jsonGroups

        jsonResult

    }



    def createGroup(def jsonGroup){

        Map jsonResult = [:]
        def responseMenssage = ''

        if (!jsonGroup?.name){
            throw new BadRequestException("You must provider name")
        }

        def newGroup = new Group(
                name        : jsonGroup?.name
        )

        if(!newGroup.validate()){
            newGroup.errors.allErrors.each{
                responseMenssage+=MessageFormat.format(it.defaultMessage,it.arguments) + " "
            }
            throw new BadRequestException(responseMenssage)
        }

        newGroup.save()

        jsonResult.id       = newGroup.id
        jsonResult.name     = newGroup.name

        jsonResult


    }


    def modifyGroup(def params, def jsonGroup){

        Map jsonResult = [:]
        def responseMessage = ''

        if (!params.groupId){
            throw  new NotFoundException("You must provider group_id")
        }

        if (!jsonGroup?.name){
            throw new BadRequestException("You must provider name")
        }

        def obteinedGroup = Group.findById(params.groupId)

        if (!obteinedGroup){
            throw new  NotFoundException("The Group with id="+params.groupId+" not found")
        }

        obteinedGroup.name                = jsonGroup?.name

        if(!obteinedGroup.validate()){

            obteinedGroup.errors.allErrors.each {
                responseMessage += MessageFormat.format(it.defaultMessage, it.arguments) + " "
            }
            throw new BadRequestException(responseMessage)

        }

        obteinedGroup.save()

        jsonResult.id       = obteinedGroup.id
        jsonResult.name     = obteinedGroup.name

        jsonResult


    }

}

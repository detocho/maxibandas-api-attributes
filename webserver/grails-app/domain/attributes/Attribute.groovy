package attributes

class Attribute {

    static constraints = {
        attributeId     blank:false, nullable:false, unique:true
        categoryId      blank:false, nullable:false
        attributeName   blank:false
        valueType       inList: ["text", "list", "multiple_list"]
        valueId         nullable:false, blank: false
    }

    String  attributeId
    String  categoryId
    String  attributeName
    String  valueType
    String  valueId
    List    value =[]
    static belongsTo = [group: Group]
}

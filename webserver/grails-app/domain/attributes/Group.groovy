package attributes

class Group {

    static constraints = {
        name nullable: false, blank:false, unique:true
    }

    String name
    static hasMany = [attribute: Attribute]
}

class UrlMappings {

	static mappings = {

        "/groups/$groupId?" {
            controller = "Group"
            action = [GET: 'get', POST:'add',PUT:'put' ,DELETE: 'notAllowed']
        }

        "/$attributeId?"{
            controller = "Attribute"
            action = [GET: 'notAllowed', POST:'add',PUT:'put' ,DELETE: 'notAllowed']
        }

        "/search/$categoryId?"{
            controller = "Attribute"
            action = [GET: 'get', POST:'notAllowed',PUT:'notAllowed' ,DELETE: 'notAllowed']
        }
	}
}

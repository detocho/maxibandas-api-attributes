class UrlMappings {

	static mappings = {

        "/groups/$groupId?" {
            controller = "Group"
            action = [GET: 'get', POST:'add',PUT:'put' ,DELETE: 'notAllowed']
        }
	}
}

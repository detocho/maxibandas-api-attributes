environments {
    development {
        grails {
            mongo {
                host = "localhost"
                databaseName = "mb_attributes"
            }
        }
    }
    test {
        grails {
            mongo {
                host = "localhost"
                databaseName = "mb_attributes"
            }
        }
    }
    production {
        grails {
            mongo {

                // replicaSet = []
                host = "localhost"
                username = ""
                password = ""
                databaseName = "mb_attributes"
            }
        }
    }
}
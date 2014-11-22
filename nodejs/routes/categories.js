exports.get = function (request, response){

    var categoryId = request.params.categoryId
    var json

    if (categoryId == "MX3"){
        json ={
            "message": "The categoryId not found",
            "status": 404,
            "error": "not_found"
        };

        response.json(404,json)

    }else {

        json = {
            "category_id": "MX1",
            "name": "Géneros musicales",
            "parent_category": [
                {
                    "category_id": "MX",
                    "name": "México",
                    "status": "active"
                }
            ],
            "children_categories": [
                {
                    "categoryId": "MX1MB1",
                    "name": "Anglo",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB2",
                    "name": "Bachata",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB3",
                    "name": "Balada",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB4",
                    "name": "Banda",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB5",
                    "name": "Blues",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB6",
                    "name": "Boleros",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB7",
                    "name": "Clasica",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB8",
                    "name": "Contrie",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB9",
                    "name": "Cumbia",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB10",
                    "name": "Electronica",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB11",
                    "name": "Gospel",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB12",
                    "name": "Hip-hop",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB13",
                    "name": "Instrumental",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB14",
                    "name": "Jazz",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB15",
                    "name": "Mariachi",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB16",
                    "name": "Nortenio",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB17",
                    "name": "Pop",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB18",
                    "name": "Rap",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB19",
                    "name": "Reggae",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB20",
                    "name": "Regueton",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB21",
                    "name": "Rock",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB22",
                    "name": "Salsa",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB23",
                    "name": "Samba",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB24",
                    "name": "Tropical",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB25",
                    "name": "Vallenato",
                    "status": "active"
                },
                {
                    "categoryId": "MX1MB26",
                    "name": "Versatil",
                    "status": "active"
                }
            ]
        };

        response.json(200,json)
    }


}